package com.adventofcode;

import java.util.Optional;

public abstract class SnailfishNumber {

    protected static final int EXPLODE_DEPTH = 4;

    public abstract long magnitude();

    public abstract void addRightmostNumber(int number);

    public abstract void addLeftmostNumber(int number);

    public abstract boolean explode();

    public abstract boolean leftExplode(PairSnailfishNumber parent, Optional<SnailfishNumber> parentLeft, int depth);

    public abstract boolean rightExplode(PairSnailfishNumber parent, Optional<SnailfishNumber> parentRight, int depth);

    public abstract boolean split();

    public abstract SnailfishNumber copy();
}
