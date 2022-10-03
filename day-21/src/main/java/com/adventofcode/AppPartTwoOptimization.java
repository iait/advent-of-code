package com.adventofcode;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AppPartTwoOptimization {

    private int calls = 0;
    private int hits = 0;

    private static final int DICE_SIDES = 3;
    private static final int BOARD_SPACES = 10;
    private static final int WIN_SCORE = 21;
    private static final int PLAYERS = 2;

    private final Map<Integer, Integer> outcomes;

    // 10*21*10*21*2*8 bytes = 705,600 bytes = 690 KB
    private final long[][][][][] cache =
            new long[BOARD_SPACES][WIN_SCORE][BOARD_SPACES][WIN_SCORE][PLAYERS];

    public static void main(String[] args) {

        new AppPartTwoOptimization().solve();
    }

    public AppPartTwoOptimization() {
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
        System.out.println(
                "calls: " + calls + ", hits: " + hits + ", misses: " + (calls - hits));
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
        long[] winsCache =
                cache[turnPosition - 1][turnScore][waitPosition - 1][waitScore];
        if (winsCache[0] != 0L || winsCache[1] != 0L) {
            hits++;
            return winsCache;
        }

        long[] wins = new long[]{0, 0};

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
        winsCache[0] = wins[0];
        winsCache[1] = wins[1];
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
