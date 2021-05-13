import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final int count;
    private final double[] arrays;

    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }

        else {
            count = t;
            arrays = new double[count];
            for (int a = 0; a < count; a++) {
                Percolation percolation = new Percolation(n);
                int numberOfOpenSites = 0;
                while (!percolation.percolates()) {
                    int i = StdRandom.uniform(1, n + 1);
                    int j = StdRandom.uniform(1, n + 1);

                    if(!percolation.isOpen(i, j)) {
                        percolation.open(i, j);
                        numberOfOpenSites = numberOfOpenSites +1;
                    }
                }
                double result = (double) numberOfOpenSites / (n * n);
                arrays[a] = result;
            }
        }
    }

    public double mean() {
        return StdStats.mean(arrays);
    }

    public double stddev() {
        return StdStats.stddev(arrays);
    }

    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(count));
    }

    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(count));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
//        int n = StdIn.readInt();
//        int t = StdIn.readInt();
        PercolationStats ps = new PercolationStats(n, t);

        String confidenceInterval = "[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]";
//        StdOut.println("mean                    = " + ps.mean()); 
        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidenceInterval);
    }
}