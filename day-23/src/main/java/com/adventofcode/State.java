package com.adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class State {

    private static final int[] POWERS_OF_TEN = IntStream.range(0, 10)
            .map(i -> (int) Math.pow(10, i)).toArray();

    private final Problem problem;
    private final byte[][] burrow;
    private final int goalCostEstimation;

    private void initialize() {
        for (int i = 0; i < problem.getBurrowHeight(); i++) {
            for (int j = 0; j < problem.getBurrowWidth(); j++) {
                burrow[i][j] = -1;
            }
        }
    }

    public State(String input, Problem problem) {
        this.problem = problem;
        this.burrow = new byte[problem.getBurrowHeight()][problem.getBurrowWidth()];
        initialize();
        String[] lines = input.split("\n");
        for (int i = 0; i < problem.getBurrowWidth(); i++) {
            burrow[0][i] = charToByte(lines[1].charAt(i + 1));
        }
        for (int i = problem.getRoomSize() - 1; i >= 0; i--) {
            String line = lines[2 + i];
            for (int j = 1; j <= problem.getRoomCount(); j++) {
                burrow[i + 1][j * 2] =
                        charToByte(line.charAt(j * 2 + 1));
            }
        }
        this.goalCostEstimation = computeGoalCostEstimation();
    }

    private State(byte[][] burrow, Problem problem) {
        this.problem = problem;
        this.burrow = burrow;
        this.goalCostEstimation = computeGoalCostEstimation();
    }

    public ApplyActionResult applyAction(Action action) {
        byte[][] newBurrow = new byte[problem.getBurrowHeight()][];
        for (int i = 0; i < problem.getBurrowHeight(); i++) {
            newBurrow[i] = Arrays.copyOf(burrow[i], problem.getBurrowWidth());
        }
        byte b = newBurrow[action.getStartY()][action.getStartX()];
        newBurrow[action.getStartY()][action.getStartX()] = -1;
        newBurrow[action.getEndY()][action.getEndX()] = b;
        State newState = new State(newBurrow, problem);
        int cost = action.getCost() * POWERS_OF_TEN[b];
        return new ApplyActionResult(newState, cost);
    }

    public byte at(int x, int y) {
        return burrow[y][x];
    }

    public int getGoalCostEstimation() {
        return goalCostEstimation;
    }

    private int computeGoalCostEstimation() {
        int cost = 0;
        byte[] ready = new byte[problem.getRoomCount()]; // how many pods are in place by room number
        for (int roomSlot = 0; roomSlot < problem.getRoomSize(); roomSlot++) {
            for (int roomNumber = 0; roomNumber < problem.getRoomCount(); roomNumber++) {
                byte b = burrow[slotToBurrowHeight(roomSlot)][roomToBurrowWidth(roomNumber)];
                if (b >= 0) {
                    int horizontalDist = abs(roomToBurrowWidth(b) - roomToBurrowWidth(roomNumber));
                    if (horizontalDist == 0) {
                        cost += (roomSlot - ready[b]) * POWERS_OF_TEN[b];
                    } else {
                        int outDist = problem.getRoomSize() - roomSlot;
                        int inDist = problem.getRoomSize() - ready[b];
                        cost += (horizontalDist + outDist + inDist) * POWERS_OF_TEN[b];
                    }
                    ready[b]++;
                }
            }
        }
        for (int burrowIndex = 0; burrowIndex < problem.getBurrowWidth(); burrowIndex++) {
            byte b = burrow[0][burrowIndex];
            if (b >= 0) {
                int horizontalDist = abs(roomToBurrowWidth(b) - burrowIndex);
                int inDist = problem.getRoomSize() - ready[b];
                cost += (horizontalDist + inDist) * POWERS_OF_TEN[b];
                ready[b]++;
            }
        }
        return cost;
    }

    int slotToBurrowHeight(int slot) {
        return problem.getBurrowHeight() - 1 - slot;
    }

    int roomToBurrowWidth(int room) {
        return (room + 1) * 2;
    }

    public boolean isGoal() {
        for (byte b : burrow[0]) {
            if (b >= 0) {
                return false;
            }
        }
        for (int roomSlot = 0; roomSlot < problem.getRoomSize(); roomSlot++) {
            for (int roomNumber = 0; roomNumber < problem.getRoomCount(); roomNumber++) {
                byte b = burrow[slotToBurrowHeight(roomSlot)][roomToBurrowWidth(roomNumber)];
                if (b != roomNumber) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Action> getActions() {
        List<Action> actions = new ArrayList<>();
        // hallway
        for (int startX = 0; startX < problem.getBurrowWidth(); startX++) {
            byte b = burrow[0][startX];
            if (b >= 0) {
                int endY = fromHallwayToRoom(startX, b);
                if (endY > 0) {
                    int startY = 0;
                    int endX = roomToBurrowWidth(b);
                    actions.add(new Action(startX, startY, endX, endY));
                }
            }
        }
        // rooms
        for (int room = 0; room < problem.getRoomCount(); room++) {
            int startX = roomToBurrowWidth(room);
            int i = 1;
            while (i < problem.getBurrowHeight() && burrow[i][startX] == -1) {
                i++;
            }
            int startY = i;
            while (i < problem.getBurrowHeight() && burrow[i][startX] == room) {
                i++;
            }
            if (i == problem.getBurrowHeight()) {
                continue; // every pod is in the correct place
            }
            int endY = 0; // hallway
            // to left
            for (int endX = startX - 1; endX >= 0 && burrow[0][endX] == -1; endX--) {
                if (isRoomIndex(endX)) {
                    continue;
                }
                actions.add(new Action(startX, startY, endX, endY));
            }
            // to right
            for (int endX = startX + 1; endX < problem.getBurrowWidth() && burrow[0][endX] == -1; endX++) {
                if (isRoomIndex(endX)) {
                    continue;
                }
                actions.add(new Action(startX, startY, endX, endY));
            }
        }
        return actions;
    }

    private boolean isRoomIndex(int pos) {
        for (int i = 0; i < problem.getRoomCount(); i++) {
            if (problem.getRoomIndex(i) == pos) {
                return true;
            }
        }
        return false;
    }

    private int fromHallwayToRoom(int startX, int room) {
        int endX = roomToBurrowWidth(room);
        int step = (startX < endX) ? 1 : -1;
        for (int i = startX + step; i != endX; i += step) {
            if (burrow[0][i] >= 0) {
                return -1;
            }
        }
        int expect = -1;
        int slot = problem.getBurrowHeight() - 1;
        for (int i = 0; i < problem.getBurrowHeight(); i++) {
            if (burrow[i][endX] == expect) {
                continue;
            }
            if (burrow[i][endX] == room) {
                slot = i - 1;
                expect = room;
            } else {
                return -1;
            }
        }
        return slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.deepEquals(burrow, state.burrow);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(burrow);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int i = 0; i < problem.getBurrowHeight(); i++) {
            for (int j = 0; j < problem.getBurrowWidth(); j++) {
                sb.append(byteToChar(burrow[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private char byteToChar(byte b) {
        if (b < 0) {
            return ' ';
        }
        return (char) ('A' + b);
    }

    private byte charToByte(char c) {
        if (c < 'A') {
            return -1;
        }
        return (byte) (c - 'A');
    }
}
