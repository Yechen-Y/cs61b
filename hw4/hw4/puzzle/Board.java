package hw4.puzzle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] tiles;
    private int N;
    private int BLANK = 0;

    public Board(int[][] tiles) {
        if (tiles == null || tiles[0] == null || tiles.length != tiles[0].length) {
            throw new IllegalArgumentException();
        }
        N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i > size() - 1 || j < 0 || j > size() - 1) {
            throw new IndexOutOfBoundsException("Out of index");
        }
        return tiles[i][j];
    }
    public int size() {
        return N;
    }

    public int hamming() {
        int sum = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                if (tileAt(i, j) != convertInt(i, j)) {
                   sum += 1;
                }
            }
        }
        return sum;
    }

    private int convertInt(int i, int j) {
        return i * N + j + 1;
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                    if (tiles[i][j] == BLANK) {
                        continue;
                    }
                    sum += (Math.abs(i - ((tiles[i][j] - 1) / N)) + Math.abs(j - ((tiles[i][j] - 1) % N)));
                }
            }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        if (this == y) {
            return true;
        }
        if (((Board) y).size() != size()) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != ((Board) y).tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}
