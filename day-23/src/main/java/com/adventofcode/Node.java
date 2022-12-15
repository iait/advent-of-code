package com.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {

    private final State state;
    private final Node parent;
    private final Action action;
    private final int pathCost;
    private final int totalCost;

    public Node(State state) {
        this(state, null, null, 0);
    }

    public Node(State state, Node parent, Action action, int pathCost) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.pathCost = pathCost;
        this.totalCost = pathCost + state.getGoalCostEstimation();
    }

    public State getState() {
        return state;
    }

    public int getPathCost() {
        return pathCost;
    }

    public List<Node> expand() {
        List<Node> children = new ArrayList<>();
        for (Action action : state.getActions()) {
            ApplyActionResult actionResult = state.applyAction(action);
            int newCost = pathCost + actionResult.cost();
            State newState = actionResult.state();
            children.add(new Node(newState, this, action, newCost));
        }
        return children;
    }

    @Override
    public int compareTo(Node o) {
        return totalCost - o.totalCost;
    }
//    @Override
//    public int compareTo(Node o) {
//        return pathCost - o.pathCost;
//    }
}
