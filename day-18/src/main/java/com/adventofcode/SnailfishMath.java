package com.adventofcode;

public class SnailfishMath {

    public static SnailfishNumber add(SnailfishNumber left, SnailfishNumber right) {

        SnailfishNumber result = PairSnailfishNumber.pairOf(left, right);
        reduce(result);
        return result;
    }

    private static void reduce(SnailfishNumber number) {

        while (number.explode() || number.split()) { }
    }

}
