package com.adventofcode;

public class SnailfishMath {

    public static SnailfishNumber add(SnailfishNumber left, SnailfishNumber right) {

        SnailfishNumber result = PairSnailfishNumber.pairOf(left.copy(), right.copy());
        reduce(result);
        return result;
    }

    private static void reduce(SnailfishNumber number) {

        while (true) {
            if (!number.explode() && !number.split()) break;
        }
    }

}
