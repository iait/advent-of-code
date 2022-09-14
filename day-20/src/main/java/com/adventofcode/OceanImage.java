package com.adventofcode;

import java.util.Arrays;
import java.util.List;

import static com.adventofcode.Utils.charToByte;

public class OceanImage {

    private final byte[][] imageMatrix;
    private final byte infValue;
    private final int size;

    private OceanImage(byte[][] imageMatrix, byte infValue) {
        this.imageMatrix = imageMatrix;
        this.infValue = infValue;
        this.size = imageMatrix.length;
    }

    public static OceanImage createOceanImage(String imageStr, char infChar) {
        String[] lines = imageStr.split("\n");
        return createOceanImage(Arrays.asList(lines), infChar);
    }

    public static OceanImage createOceanImage(List<String> lines, char infChar) {
        int size = lines.size();
        byte infValue = charToByte(infChar);
        byte[][] imageMatrix = new byte[size][size];
        int row = 0;
        for (String line : lines) {
            if (line.length() != size) {
                throw new IllegalArgumentException(
                        "Line " + row + " length should be " + size);
            }
            for (int col = 0; col < size; col++) {
                imageMatrix[row][col] = charToByte(line.charAt(col));
            }
            row++;
        }
        return new OceanImage(imageMatrix, infValue);
    }

    public OceanImage applyEnhancement(EnhancementAlgorithm enhancement) {
        int newSize = size + 2;
        byte[][] newImageMatrix = new byte[newSize][newSize];
        byte newInfValue = infValue == 0 ? enhancement.getFirst() : enhancement.getLast();
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                int index = getIndex(i-1, j-1);
                newImageMatrix[i][j] = enhancement.getAt(index);
            }
        }
        return new OceanImage(newImageMatrix, newInfValue);
    }

    private byte getAt(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return infValue;
        } else {
            return imageMatrix[row][col];
        }
    }

    private int getIndex(int row, int col) {
        return
                getAt(row-1, col-1) << 8 ^
                getAt(row-1, col  ) << 7 ^
                getAt(row-1, col+1) << 6 ^
                getAt(row  , col-1) << 5 ^
                getAt(row  , col  ) << 4 ^
                getAt(row  , col+1) << 3 ^
                getAt(row+1, col-1) << 2 ^
                getAt(row+1, col  ) << 1 ^
                getAt(row+1, col+1);
    }

    public int lightCount() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                count += imageMatrix[i][j];
            }
        }
        return count;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte[] row : imageMatrix) {
            for (byte pixel : row) {
                sb.append(pixel);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
