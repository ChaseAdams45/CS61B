/****************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:  java PercolationStats 200 100
 *  Dependencies: Percolation.java algs4.jar stdlib.jar
 *
 *  Percolation class for Monte Carlo simulation.
 *
 ****************************************************************************/

/**
 *
 * @author Maxim Butyrin
 *
 */
package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int sizeOfSide;
    private int numberOfTrials;
    private double[] results;

    /**
     *  Perform independent experiments on a square grid
     *  @param N      size of the grid side
     *  @param T number of experiments to perform
     */

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        sizeOfSide = N;
        numberOfTrials = T;
        results = new double[T];
        runTrials();
    }

    private void runTrials() {
        for (int i = 0; i < numberOfTrials; i++) {
            Percolation perc = new Percolation(sizeOfSide);
            double openSites = 0;

            while (!perc.percolates()) {
                int x = StdRandom.uniform(1, sizeOfSide + 1);
                int y = StdRandom.uniform(1, sizeOfSide + 1);
                if (!perc.isOpen(y, x)) {
                    perc.open(y, x);
                    openSites++;
                }
            }
            results[i] = openSites / (sizeOfSide * sizeOfSide);
        }
    }

    /**
     * Sample mean of percolation threshold
     * @return double  mean
     */
    public double mean() {
        return StdStats.mean(results);
    }

    /**
     * Sample standard deviation of percolation threshold
     * @return double  standard deviation
     */
    public double stddev() {
        return StdStats.stddev(results);
    }

    /**
     * Low  endpoint of 95% confidence interval
     * @return double  low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(numberOfTrials);
    }

    /**
     * High  endpoint of 95% confidence interval
     * @return double  high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(numberOfTrials);
    }

    /**
     * Test client for simulation. Takes two command-line arguments
     * N and T, performs T independent computational experiments
     * on an N-by-N grid, and prints the mean, standard deviation, and
     * the 95% confidence interval for the percolation threshold.
     * @param args  N:      size of the grid side
     *              T: number of experiments
     */
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 1000);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("interval = " + ps.confidenceLo() + " " + ps.confidenceHi());
    }
}
