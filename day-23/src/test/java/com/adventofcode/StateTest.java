package com.adventofcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {

    private final Problem problem = new Problem(4, 2);

    private final State example1 = new State("""
                #############
                #...........#
                ###B#C#B#D###
                  #A#D#C#A#
                  #########
                """, problem);

    private final State example2 = new State("""
                #############
                #...........#
                ###B#A#C#D###
                  #A#B#C#D#
                  #########
                """, problem);

    private final State example3 = new State("""
                #############
                #.B...C....D#
                ###.#.#B#D###
                  #A#.#C#A#
                  #########
                """, problem);

    private final State goal = new State("""
                #############
                #...........#
                ###A#B#C#D###
                  #A#B#C#D#
                  #########
                """, problem);

    @Test
    void testGoalCostEstimate() {
        assertEquals(8499, example1.getGoalCostEstimation());
        assertEquals(44, example2.getGoalCostEstimation());
        assertEquals(4299, example3.getGoalCostEstimation());
        assertEquals(0, goal.getGoalCostEstimation());
    }

    @Test
    void testIsGoal() {
        assertFalse(example1.isGoal());
        assertFalse(example2.isGoal());
        assertFalse(example3.isGoal());
        assertTrue(goal.isGoal());
    }

    @Test
    void testActions() {
        assertEquals(28, example1.getActions().size());
        assertEquals(14, example2.getActions().size());
        assertEquals(5, example3.getActions().size());
        assertEquals(0, goal.getActions().size());
    }
}
