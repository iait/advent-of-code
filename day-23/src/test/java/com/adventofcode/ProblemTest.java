package com.adventofcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProblemTest {

    private final String partOneExample = """
            #############
            #...........#
            ###B#C#B#D###
              #A#D#C#A#
              #########
            """;

    private final String partOneInput = """
            #############
            #...........#
            ###D#A#C#C###
              #D#A#B#B#
              #########
            """;

    private final String partTwoExample = """
            #############
            #...........#
            ###B#C#B#D###
              #D#C#B#A#
              #D#B#A#C#
              #A#D#C#A#
              #########
            """;

    private final String partTwoInput = """
            #############
            #...........#
            ###D#A#C#C###
              #D#C#B#A#
              #D#B#A#C#
              #D#A#B#B#
              #########
            """;

    @Test
    void testBestFirstSearchPartOne() {
        var problem = new Problem(4, 2);

        Node exampleSolution = problem.bestFirstSearch(partOneExample);
        assertTrue(exampleSolution.getState().isGoal());
        assertEquals(12521, exampleSolution.getPathCost());

        Node inputSolution = problem.bestFirstSearch(partOneInput);
        assertTrue(inputSolution.getState().isGoal());
        assertEquals(19046, inputSolution.getPathCost());
    }

    @Test
    void testBestFirstSearchPartTwo() {
        var problem = new Problem(4, 4);

        Node exampleSolution = problem.bestFirstSearch(partTwoExample);
        assertTrue(exampleSolution.getState().isGoal());
        assertEquals(44169, exampleSolution.getPathCost());

        Node inputSolution = problem.bestFirstSearch(partTwoInput);
        assertTrue(inputSolution.getState().isGoal());
        assertEquals(47484, inputSolution.getPathCost());
    }

}
