package com.adventofcode;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AppPartTwo {

    private int calls = 0;

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
        long[] wins = computeWins(4, 0, 8, 0);
        long end = System.nanoTime();
        System.out.println("Player 1 wins: " + wins[0]);
        System.out.println("Player 2 wins: " + wins[1]);
        System.out.println("Time: " + ((end - start) / 1_000_000) + "ms");

        System.out.println("calls: " + calls);
    }

    /**
     * Computes how many times each player wins from the current state of positions and scores.
     *
     * @param turnPosition Position in the board of the player that has the turn.
     * @param turnScore Score of the player that has the turn.
     * @param waitPosition Position in the board of the player that is waiting for its turn.
     * @param waitScore Score of the player that is waiting for its turn.
     * @return An array indicating how many times the player that has the turn and the player
     * that is waiting for its turn wins the game, in that order.
     */
    private long[] computeWins(int turnPosition, int turnScore, int waitPosition, int waitScore) {

        calls++;
        long[] wins = new long[2];

        for (Entry<Integer, Integer> entry : outcomes.entrySet()) {
            int outcome = entry.getKey();
            long freq = entry.getValue();
            int newPosition = move(turnPosition, outcome);
            int newScore = turnScore + newPosition;
            if (newScore >= WIN_SCORE) {
                wins[0] += freq;
            } else {
                long[] newWins = computeWins(waitPosition, waitScore, newPosition, newScore);
                wins[0] += newWins[1] * freq;
                wins[1] += newWins[0] * freq;
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
