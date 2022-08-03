package com.adventofcode;

import java.util.Optional;

public class RegularSnailfishNumber extends SnailfishNumber {

    public static RegularSnailfishNumber regularOf(int value) {
        return new RegularSnailfishNumber(value);
    }

    private int value;

    private RegularSnailfishNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public long magnitude() {
        return value;
    }

    @Override
    public void addRightmostNumber(int number) {
        value += number;
    }

    @Override
    public void addLeftmostNumber(int number) {
        value += number;
    }

    @Override
    public boolean explode() {
        return false;
    }

    @Override
    public boolean leftExplode(PairSnailfishNumber parent, Optional<SnailfishNumber> parentLeft, int depth) {
        return false;
    }

    @Override
    public boolean rightExplode(PairSnailfishNumber parent, Optional<SnailfishNumber> parentRight, int depth) {
        return false;
    }

    @Override
    public boolean split() {
        return false;
//        throw new RuntimeException("Cannot split a regular snailfish number");
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
