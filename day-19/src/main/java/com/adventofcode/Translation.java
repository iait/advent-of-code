package com.adventofcode;

public class Translation extends Point {

    public Translation(int x, int y, int z) {
        super(x, y, z);
    }

    public Point applyTranslation(Point p) {
        return new Point(p.x() - x, p.y() - y, p.z() - z);
    }
}
