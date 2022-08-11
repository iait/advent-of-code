package com.adventofcode;

import java.util.List;
import java.util.stream.Collectors;

import static com.adventofcode.InputParser.PREFIX;
import static com.adventofcode.InputParser.SUFFIX;
import static java.lang.System.lineSeparator;

public class Scanner {

    private int name;
    private List<Point> beacons;

    private Scanner parent;
    private Transformation transformation;

    public Scanner(int name, List<Point> beacons) {
        this.name = name;
        this.beacons = beacons;
    }

    public int name() {
        return name;
    }

    public List<Point> beacons() {
        return beacons;
    }

    public void setParent(Scanner parent) {
        this.parent = parent;
    }

    public void setTransformation(Transformation transformation) {
        this.transformation = transformation;
    }

    public Scanner getParent() {
        return parent;
    }

    public Transformation getTransformation() {
        return transformation;
    }

    @Override
    public String toString() {
        return PREFIX + name + SUFFIX + lineSeparator() +
                beacons.stream().map(Point::toString).collect(Collectors.joining(lineSeparator()));
    }
}
