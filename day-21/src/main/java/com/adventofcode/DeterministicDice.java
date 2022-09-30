package com.adventofcode;

public class DeterministicDice {

    private final int sides;

    private Integer lastValue;
    private int rollCount = 0;

    public DeterministicDice(int sides) {
        this.sides = sides;
    }

    public int rollDice() {
        rollCount++;
        if (lastValue == null || lastValue == 100) {
            lastValue = 1;
        } else {
            lastValue++;
        }
        return lastValue;
    }

    public int getRollCount() {
        return rollCount;
    }
}
