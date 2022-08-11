package com.adventofcode;

import java.util.Optional;

import static com.adventofcode.RegularSnailfishNumber.regularOf;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class PairSnailfishNumber extends SnailfishNumber {

    public static PairSnailfishNumber pairOf(SnailfishNumber left, SnailfishNumber right) {
        return new PairSnailfishNumber(left, right);
    }

    private SnailfishNumber left;
    private SnailfishNumber right;

    private PairSnailfishNumber(SnailfishNumber left, SnailfishNumber right) {
        this.left = left;
        this.right = right;
    }

    public SnailfishNumber getLeft() {
        return left;
    }

    public SnailfishNumber getRight() {
        return right;
    }

    @Override
    public long magnitude() {
        return 3 * left.magnitude() + 2 * right.magnitude();
    }

    @Override
    public void addRightmostNumber(int number) {
        right.addRightmostNumber(number);
    }

    @Override
    public void addLeftmostNumber(int number) {
        left.addLeftmostNumber(number);
    }

    @Override
    public boolean explode() {
        return left.leftExplode(this, Optional.empty(), 1) || right.rightExplode(this, Optional.empty(), 1);
    }

    public boolean leftExplode(PairSnailfishNumber parent, Optional<SnailfishNumber> parentLeft, int depth) {

        /*
           parentLeft       parent
                            /    \
                        this     parent.right
         */

        if (depth < EXPLODE_DEPTH) {
            return left.leftExplode(this, parentLeft, depth + 1)
                    || right.rightExplode(this, Optional.of(parent.right), depth + 1);
        }
        if (left instanceof RegularSnailfishNumber leftNumber
                && right instanceof RegularSnailfishNumber rightNumber) {
            parentLeft.ifPresent(n -> n.addRightmostNumber(leftNumber.getValue()));
            parent.right.addLeftmostNumber(rightNumber.getValue());
            parent.left = regularOf(0);
            return true;
        }
        throw new RuntimeException("Exploding pairs will always consist of two regular numbers");
    }

    public boolean rightExplode(PairSnailfishNumber parent, Optional<SnailfishNumber> parentRight, int depth) {

        if (depth < EXPLODE_DEPTH) {
            return left.leftExplode(this, Optional.of(parent.left), depth + 1)
                    || right.rightExplode(this, parentRight, depth + 1);
        }
        if (left instanceof RegularSnailfishNumber leftNumber
                && right instanceof RegularSnailfishNumber rightNumber) {
            parent.left.addRightmostNumber(leftNumber.getValue());
            parentRight.ifPresent(n -> n.addLeftmostNumber(rightNumber.getValue()));
            parent.right = regularOf(0);
            return true;
        }
        throw new RuntimeException("Exploding pairs will always consist of two regular numbers");
    }

    @Override
    public boolean split() {

        if (left instanceof RegularSnailfishNumber regularNumber && regularNumber.getValue() > 9) {
            int value = regularNumber.getValue();
            left = pairOf(regularOf((int) floor(value / 2.0)), regularOf((int) ceil(value / 2.0)));
            return true;
        }
        if (left.split()) {
            return true;
        }
        if (right instanceof RegularSnailfishNumber regularNumber && regularNumber.getValue() > 9) {
            int value = regularNumber.getValue();
            right = pairOf(regularOf((int) floor(value / 2.0)), regularOf((int) ceil(value / 2.0)));
            return true;
        }
        return right.split();
    }

    @Override
    public String toString() {
        return "[" + left + "," + right + "]";
    }
}
