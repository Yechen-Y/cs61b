package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size;
    private int openSite;
    private WeightedQuickUnionUF grid;
    private int[] openGrid;

    //将顶部和底部链接
    private void setStartAndEnd (WeightedQuickUnionUF p) {
        for (int index = 0; index < size; index += 1) {
            p.union(0, countIndex(0, index));
        }
        for (int index = 0; index < size; index += 1) {
            p.union(countIndex(size - 1, 0), countIndex(size - 1, index));
        }
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Illegal argument Exception");
        }
        size = N;
        grid = new WeightedQuickUnionUF(N * N);
        openGrid = new int[N * N]; //如果为open设置为1 否则为0
        for (int index = 0; index < N * N; index += 1) {
            openGrid[index] = 0;
        }
        setStartAndEnd(grid);
        openSite = 0;
    }

    private int countIndex (int x, int y) {
        return x * size + y;
    }


    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row >= size || col >= size) {
            throw new IndexOutOfBoundsException("Out of bounds exception");
        }
        int index = countIndex(row, col);
        // 如果格子已经打开则返回 不然会导致openSite > 总共的site (考虑一直打开一个格子)
        if (openGrid[index] == 1) {
            return;
        }
        openGrid[index] = 1;
        // 判断四周格子是否打开 如果打开的话就连接起来
        openHelper(row, col);
        openSite += 1;
    }

    //open的辅助函数 用于连接四周格子
    private void openHelper (int row, int col) {
        int midIndex = countIndex(row, col);
        if (col + 1 < size) {
            openHelperHelper(row, col + 1, midIndex);
        }
        if (col - 1 >= 0) {
            openHelperHelper(row, col - 1, midIndex);
        }
        if (row + 1 < size) {
            openHelperHelper(row + 1, col, midIndex);
        }
        if (row - 1 >= 0) {
            openHelperHelper(row - 1, col, midIndex);
        }
    }

    private void openHelperHelper (int row, int col, int midIndex) {
        int index = countIndex(row, col);
        if (isOpen(row, col)) {
            grid.union(midIndex, index);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= size || col >= size) {
            throw new IndexOutOfBoundsException("Out of bounds exception");
        }
        int index = countIndex(row, col);
        return openGrid[index] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= size || col >= size) {
            throw new IndexOutOfBoundsException("Out of bounds exception");
        }
        int index = countIndex(row, col);
        return grid.connected(0, index) && isOpen(row, col);
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(0, size * size - 1);
    }
    // number of open sites
    public int numberOfOpenSites() {
        return openSite;
    }
}
