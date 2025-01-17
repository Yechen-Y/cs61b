package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> dq = new ArrayDeque<>();
        int x = s;
        marked[x] = true;
        announce();

        while (x != t) {
            for (int y : maze.adj(x)) {
                if (!marked[y] && !dq.contains(y)) {
                    dq.add(y);
                    edgeTo[y] = x;
                    distTo[y] = distTo[x] + 1;
                }
            }
            x = dq.remove();
            marked[x] = true;
            announce();
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

