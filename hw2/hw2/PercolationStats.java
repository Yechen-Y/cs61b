package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] xt;
    private PercolationFactory systemMake;
    private double sum;
    private int side;
    private int times;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats (int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("illegal argument exception");
        }
        systemMake = pf;
        xt = new double[T];
        sum = N * N;
        side = N;
        times = T;
        for (int index = 0; index < T; index += 1) {
            doExperiment(index);
        }
    }

    private void doExperiment (int index) {
        Percolation system = systemMake.make(side);
        while (!system.percolates()) {
            system.open(StdRandom.uniform(side), StdRandom.uniform(side));
        }
        xt[index] = system.numberOfOpenSites() / sum;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(xt);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(xt);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }
}
