package com.adventofcode;

import static java.lang.Math.min;

public class AppPartOne {

    private static final int DICE_SIDES = 100;
    private static final int BOARD_SPACES = 10;
    private static final int SCORE_WIN = 1_000;

    public static void main(String[] args) {

        var dice = new DeterministicDice(DICE_SIDES);

        int position1 = 1;
        int position2 = 2;

        int score1 = 0;
        int score2 = 0;

        while (true) {
            // player 1
            position1 = move(position1, dice.rollDice() + dice.rollDice() + dice.rollDice());
            score1 += position1;
            if (score1 >= SCORE_WIN) {
                break;
            }

            // player 2
            position2 = move(position2, dice.rollDice() + dice.rollDice() + dice.rollDice());
            score2 += position2;
            if (score2 >= SCORE_WIN) {
                break;
            }
        }

        int result = dice.getRollCount() * min(score1, score2);
        System.out.println("Result: " + result);
    }

    private static int move(int start, int moves) {
        // returns the space in the circular board where the pawn stops after a number of moves
        int temp = (start + moves) % BOARD_SPACES;
        return temp == 0 ? BOARD_SPACES : temp;
    }
}
