package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moves;
        private SearchNode previousNode;
        private int toGoal;

        public SearchNode(WorldState s, int m, SearchNode p) {
            state = s;
            moves = m;
            previousNode = p;
            toGoal = state.estimatedDistanceToGoal();
        }

        public int getMoves() {
            return moves;
        }


        @Override
        public int compareTo(SearchNode o) {
            return moves + toGoal - o.moves - o.toGoal;
        }
    }

    private MinPQ<SearchNode> pq = new MinPQ<>();
    private SearchNode pre;
    private List<WorldState> tempArray;
    private int tomove;

    public Solver(WorldState initial) {
        pq.insert(new SearchNode(initial, 0, null));

        while (true) {
            pre = pq.delMin();
            if (pre.state.isGoal()) {
                break;
            }
            Iterable<WorldState> states = pre.state.neighbors();
            for (WorldState state : states) {
                if (pre.previousNode == null || !state.equals(pre.previousNode.state)) {
                    pq.insert(new SearchNode(state, pre.moves + 1, pre));
                }
            }
        }
    }

    public int moves() {
        return pre.moves;
    }

    public Iterable<WorldState> solution() {
        List<WorldState> sol = new ArrayList<>();
        tempArray = new ArrayList<>();
        tomove = moves();
        for (int i = tomove; i >= 0; i -= 1) {
            tempArray.add(pre.state);
            pre = pre.previousNode;
        }
        for (int i = tomove; i >= 0; i -= 1) {
            sol.add(tempArray.get(i));
        }
        return sol;
    }

}
