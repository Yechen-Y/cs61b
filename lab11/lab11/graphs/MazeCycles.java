package lab11.graphs;

/**
 *  @author Yechen
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int[] sol;
    private boolean flag = false;


    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        sol = new int[maze.N() * maze.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        marked[0] = true;
        sol[0] = 0;
        announce();
        dfs(0);
    }

    private void dfs(int x) {
        if (flag) {
            return;
        }
        for (int a : maze.adj(x)) {
            if (marked[a] && sol[a] != x && a != sol[x] && !flag) {
                flag = true;
                for (int q = x; q != a; q = sol[q]) {
                    edgeTo[q] = sol[q];
                    announce();
                }
                edgeTo[a] = x;
                announce();
                return;
            } else {
                if (!marked[a] && !flag) {
                    marked[a] = true;
                    sol[a] = x;
                    announce();
                    dfs(a);
                }
            }
        }
    }
    // Helper methods go here
}

