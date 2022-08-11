package com.adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.adventofcode.Rotation.Axis.*;

public class Rotation {

    enum Axis {
         X(" x", 0,  true),
         Y(" y", 1,  true),
         Z(" z", 2,  true),
        _X("-x", 0, false),
        _Y("-y", 1, false),
        _Z("-z", 2, false);

        private final String name;
        private final int position;
        private final boolean positive;

        Axis(String name, int position, boolean positive) {
            this.name = name;
            this.position = position;
            this.positive = positive;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static final List<Rotation> rotations;

    static {
        rotations = Arrays.asList(
                Rotation.of( X,  Y,  Z),
                Rotation.of( X, _Y, _Z),
                Rotation.of( X,  Z, _Y),
                Rotation.of( X, _Z,  Y),
                Rotation.of(_X,  Z,  Y),
                Rotation.of(_X, _Z, _Y),
                Rotation.of(_X,  Y, _Z),
                Rotation.of(_X, _Y,  Z),
                Rotation.of( Y,  Z,  X),
                Rotation.of( Y, _Z, _X),
                Rotation.of( Y,  X, _Z),
                Rotation.of( Y, _X,  Z),
                Rotation.of(_Y,  X,  Z),
                Rotation.of(_Y, _X, _Z),
                Rotation.of(_Y,  Z, _X),
                Rotation.of(_Y, _Z,  X),
                Rotation.of( Z,  X,  Y),
                Rotation.of( Z, _X, _Y),
                Rotation.of( Z,  Y, _X),
                Rotation.of( Z, _Y,  X),
                Rotation.of(_Z,  Y,  X),
                Rotation.of(_Z, _Y, _X),
                Rotation.of(_Z,  X, _Y),
                Rotation.of(_Z, _X,  Y));
    }

    public static Rotation of(Axis x, Axis y, Axis z) {
        int[] indexes = new int[3];
        int[] signs = new int[3];
        Axis[] axes = new Axis[]{x, y, z};
        for (int i = 0; i < 3; i++) {
            int index = axes[i].position;
            int sign = axes[i].positive ? 1 : -1;
            indexes[index] = i;
            signs[index] = sign;
        }
        return new Rotation(indexes, signs);
    }

    private final int[] indexes;
    private final int[] signs;

    private Rotation(int[] indexes, int[] signs) {
        this.indexes = indexes;
        this.signs = signs;
    }

    public Point applyRotation(Point p) {
        int[] components = new int[]{p.x(), p.y(), p.z()};
        return new Point(
                signs[0] * components[indexes[0]],
                signs[1] * components[indexes[1]],
                signs[2] * components[indexes[2]]);
    }

    public Rotation inverse() {
        int[] indexes = new int[3];
        int[] signs = new int[3];
        for (int i = 0; i < 3; i++) {
            int index = this.indexes[i];
            int sign = this.signs[i];
            indexes[index] = i;
            signs[index] = sign;
        }
        return new Rotation(indexes, signs);
    }

    public String toMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(String.format("%2d",
                        (indexes[j] == i) ? ((signs[j] > 0) ? 1 : -1) : 0));
                if (j < 2) {
                    sb.append(" ");
                }
            }
            if (i < 2) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        Axis[] axes = new Axis[3];
        for (int i = 0; i < 3; i++) {
            int index = indexes[i];
            int sign = signs[i];
            axes[index] = switch (i) {
                case 0 -> sign > 0 ? X : _X;
                case 1 -> sign > 0 ? Y : _Y;
                case 2 -> sign > 0 ? Z : _Z;
                default -> throw new IllegalStateException("Unexpected value: " + indexes[i]);
            };
        }
        return Arrays.stream(axes).map(Axis::toString).collect(Collectors.joining(","));
    }
}
