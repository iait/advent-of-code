package com.adventofcode;

import static java.lang.Math.abs;

public class Action {

    private final int startX, startY;
    private final int endX, endY;

    private final int cost;

    public Action(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.cost = abs(endX - startX) + abs(endY - startY);
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getCost() {
        return cost;
    }
}
