import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF qf_A;
    private final WeightedQuickUnionUF qf_B;
    private final int num;
    // virtual top site
    private final int top;
    // virtual bottom site
    private final int bot;
    private final boolean[][] isopen;
    private int numberOfOpenSites;

    // creates n-by-n grid, with all sites initially blocked 
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else {
            num = n;
            qf_A = new WeightedQuickUnionUF(num * num + 2);
            qf_B = new WeightedQuickUnionUF( num * num + 1);
            top = 0;
            bot = num * num + 1;
            isopen = new boolean[num][num];
            numberOfOpenSites = 0;
        }
    }

    // opens the site (row, col) if it is not open already 
    public void open(int row, int col) {

        checkException(row, col);

        // open first
        isopen[row - 1][col - 1] = true;
        numberOfOpenSites = numberOfOpenSites + 1;

        // top row
        if (row == 1) {
            qf_A.union(num * (row - 1) + col, top);
            qf_B.union(num * (row - 1) + col, top);
        }

        // bottom row
        if (row == num) {
            qf_A.union(num * (row - 1) + col, bot);
        }

        // check up
        if (row > 1 && isOpen(row - 1, col)) {
            qf_A.union(num * (row - 1) + col, num * (row - 2) + col);
            qf_B.union(num * (row - 1) + col, num * (row - 2) + col);
        }

        // check down
        if (row < num && isOpen(row + 1, col)) {
            qf_A.union(num * (row - 1) + col, num * (row) + col);
            qf_B.union(num * (row - 1) + col, num * (row) + col);
        }

        // check left
        if (col > 1 && isOpen(row, col - 1)) {
            qf_A.union(num * (row - 1) + col, num * (row - 1) + col - 1);
            qf_B.union(num * (row - 1) + col, num * (row - 1) + col - 1);
        }

        // check right
        if (col < num && isOpen(row, col + 1)) {
            qf_A.union(num * (row - 1) + col, num * (row - 1) + col + 1);
            qf_B.union(num * (row - 1) + col, num * (row - 1) + col + 1);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkException(row, col);
        return isopen[row - 1][col - 1];

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkException(row, col);
        // find top connected ( not bot )
        return qf_B.find(top) == qf_B.find(num * (row - 1) + col);

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {

        return qf_A.find(top) == qf_A.find(bot);
    }

    private void checkException(int row, int col) {
        if (row <= 0 || col <= 0 || row > num || col > num) {
            throw new IllegalArgumentException();
        }
    }


//     test client (optional)
//    public static void main(String[] args) {
//        Percolation percolation = new Percolation(5);
//        percolation.open(1, 5);
//        percolation.open(2, 5);
//        percolation.open(3, 5);
//        percolation.open(4, 5);
//        percolation.open(5, 5);
//        percolation.open(5, 1);
//        percolation.open(1, 3);
//        percolation.open(2, 3);
//        percolation.open(3, 3);
//        percolation.open(3, 2);
//        percolation.open(3, 1);
//
//        System.out.println(percolation.isOpen(1, 5));
//        System.out.println(percolation.isOpen(2, 5));
//        System.out.println(percolation.isOpen(3, 5));
//        System.out.println(percolation.isOpen(4, 5));
//        System.out.println(percolation.isOpen(5, 5));
//        System.out.println(percolation.isOpen(5, 1));
//        System.out.println(percolation.isOpen(1, 3));
//        System.out.println(percolation.isOpen(2, 3));
//        System.out.println(percolation.isOpen(3, 3));
//        System.out.println(percolation.isOpen(3, 2));
//        System.out.println(percolation.isOpen(3, 1));
//
//        System.out.println(percolation.isFull(1, 5));
//        System.out.println(percolation.isFull(2, 5));
//        System.out.println(percolation.isFull(3, 5));
//        System.out.println(percolation.isFull(4, 5));
//        System.out.println(percolation.isFull(5, 5));
//        System.out.println(percolation.isFull(5, 1));
//        System.out.println(percolation.isFull(1, 3));
//        System.out.println(percolation.isFull(2, 3));
//        System.out.println(percolation.isFull(3, 3));
//        System.out.println(percolation.isFull(3, 2));
//        System.out.println(percolation.isFull(3, 1));
//
//    }

}