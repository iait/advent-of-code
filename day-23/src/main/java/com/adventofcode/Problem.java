package com.adventofcode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Problem {

    private final int roomCount;
    private final int roomSize;
    private final int burrowWidth;
    private final int burrowHeight;
    private final int[] roomIndex;

    Problem(int roomCount, int roomSize) {
        this.roomCount = roomCount;
        this.roomSize = roomSize;
        this.burrowWidth = roomCount * 2 + 3;
        this.burrowHeight = roomSize + 1;
        this.roomIndex = new int[roomCount];
        for (int i = 0; i < roomCount; i++) {
            roomIndex[i] = (i + 1) * 2;
        }
    }

    Node bestFirstSearch(String input)  {

        long start = System.nanoTime();

        State initial = new State(input, this);

        PriorityQueue<Node> frontier = new PriorityQueue<>();
        Map<State, Node> reached = new HashMap<>();

        Node node = new Node(initial);
        frontier.add(node);
        reached.put(initial, node);
        while (!frontier.isEmpty()) {
            node = frontier.poll();
            if (node.getState().isGoal()) {
                long end = System.nanoTime();
                System.out.println("Reached: " + reached.size());
                System.out.println("Frontier: " + frontier.size());
                System.out.println("Expanded: " + (reached.size() - frontier.size()));
                System.out.println("Time: " + ((end - start) / 1_000_000));
                return node;
            }
            for (Node child : node.expand()) {
                State state = child.getState();
                Node repeatedNode = reached.get(state);
                if (repeatedNode == null || child.getPathCost() < repeatedNode.getPathCost()) {
                    reached.put(state, child);
                    frontier.add(child);
                }
            }
        }
        return null;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public int getBurrowWidth() {
        return burrowWidth;
    }

    public int getBurrowHeight() {
        return burrowHeight;
    }

    public int getRoomIndex(int room) {
        return roomIndex[room];
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        Path path = Paths.get(ClassLoader.getSystemResource("part_two_example.txt").toURI());
        String input = Files.readString(path);
        Problem problem = new Problem(4, 4);
        Node solution = problem.bestFirstSearch(input);
        if (solution == null)  {
            System.out.println("No solution found!");
        } else {
            System.out.println(solution.getState());
            System.out.println(solution.getPathCost());
        }
    }
}
