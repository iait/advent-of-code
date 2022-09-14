package com.adventofcode;

import static com.adventofcode.Utils.charToByte;

public class EnhancementAlgorithm {

    private final byte[] enhancementArray;

    public EnhancementAlgorithm(String enhancementStr, int squareSize) {
        int strSize = 1 << squareSize * squareSize;
        enhancementArray = new byte[strSize];
        if (enhancementStr.length() != strSize) {
            throw new IllegalArgumentException(
                    "Enhancement string length should be " + strSize);
        }
        for (int i = 0; i < strSize; i++) {
            enhancementArray[i] = charToByte(enhancementStr.charAt(i));
        }
    }

    public byte getFirst() {
        return enhancementArray[0];
    }

    public byte getLast() {
        return enhancementArray[enhancementArray.length - 1];
    }

    public byte getAt(int index) {
        return enhancementArray[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : enhancementArray) {
            sb.append(b);
        }
        return sb.toString();
    }
}
