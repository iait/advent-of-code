package com.adventofcode;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AppPartTwo {

    private static final int DICE_SIDES = 3;
    private static final int BOARD_SPACES = 10;
    private static final int WIN_SCORE = 21;

    private final Map<Integer, Integer> outcomes;

    public static void main(String[] args) {

        new AppPartTwo().solve();
    }

    public AppPartTwo() {
        var outcomes = computePossibleOutcomes();
        System.out.println("Sum frequencies:");
        for (Entry<Integer, Integer> entry : outcomes.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        this.outcomes = Collections.unmodifiableMap(outcomes);
    }

    private void solve() {

        long start = System.nanoTime();
        long[] wins = computeWins(1, new int[]{4, 8}, new int[]{0, 0}, 0);
        long end = System.nanoTime();
        System.out.println("Player 1 wins: " + wins[0]);
        System.out.println("Player 2 wins: " + wins[1]);
        System.out.println("Time: " + (end - start) / 1_000_000);
    }

    /**
     * Computes how many times each player wins from the current state of positions, scores
     * and turn.
     *
     * @param freq how many times this state is obtained
     * @param positions the current positions of the two players
     * @param scores the current scores of the two players
     * @param turn whose turn it is to roll the dice
     * @return how many times each player wins
     */
    private long[] computeWins(long freq, int[] positions, int[] scores, int turn) {

        long[] wins = new long[2];

        int other = (turn + 1) % 2;
        int position = positions[turn];
        int score = scores[turn];

        for (Entry<Integer, Integer> entry : outcomes.entrySet()) {
            int outcome = entry.getKey();
            long newFreq = freq * entry.getValue();
            int newPosition = move(position, outcome);
            int newScore = score + newPosition;
            if (newScore >= WIN_SCORE) {
                wins[turn] += newFreq;
            } else {
                int[] newPositions = new int[2];
                newPositions[other] = positions[other];
                newPositions[turn] = newPosition;
                int[] newScores = new int[2];
                newScores[other] = scores[other];
                newScores[turn] = newScore;
                long[] newWins = computeWins(newFreq, newPositions, newScores, other);
                wins[turn] += newWins[turn];
                wins[other] += newWins[other];
            }
        }

        return wins;
    }

    /**
     * Returns the position in the circular board where the pawn stops after a number of moves.
     *
     * @param start Starting position
     * @param moves Number of moves
     * @return Final position
     */
    private static int move(int start, int moves) {
        int temp = (start + moves) % BOARD_SPACES;
        return temp == 0 ? BOARD_SPACES : temp;
    }

    /**
     * Computes the possible outcomes of rolling three times the dice.
     *
     * @return A map with all the possible sums of the dice outcomes associated with each frequency.
     */
    private Map<Integer, Integer> computePossibleOutcomes() {

        Map<Integer, Integer> outcomes = new TreeMap<>();
        for (int i = 1; i <= DICE_SIDES; i++) {
            for (int j = 1; j <= DICE_SIDES; j++) {
                for (int k = 1; k <= DICE_SIDES; k++) {
                    int sum = i + j + k;
                    Integer frequency = outcomes.get(sum);
                    if (frequency == null) {
                        frequency = 0;
                    }
                    outcomes.put(sum, frequency + 1);
                }
            }
        }
        return outcomes;
    }
}
