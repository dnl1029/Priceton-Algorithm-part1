import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point 
    private final int y;     // y-coordinate of this point 

    // constructs the point (x, y) 
    /**
     * Initializes a new point. 
     *
     * @param  x the <em>x</em>-coordinate of the point 
     * @param  y the <em>y</em>-coordinate of the point 
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point 
    /**
     * Draws this point to standard draw. 
     */
    public void draw() {
        StdDraw.point(x,y);
    }

    // draws the line segment from this point to that point 
    /**
     * Draws the line segment between this point and the specified point 
     * to standard draw. 
     *
     * @param that the other point 
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // string representation 
    /**
     * Returns a string representation of this point. 
     * This method is provide for debugging; 
     * your program should not rely on the format of the string representation. 
     *
     * @return a string representation of this point 
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

// compare two points by y-coordinates, breaking ties by x-coordinates
/**
 * Compares two points by y-coordinate, breaking ties by x-coordinate.
 * Formally, the invoking point (x0, y0) is less than the argument point
 * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
 *
 * @param  that the other point
 * @return the value <tt>0</tt> if this point is equal to the argument
 *         point (x0 = x1 and y0 = y1);
 *         a negative integer if this point is less than the argument
 * point; and a positive integer if this point is greater than the
 *         argument point
 */
public int compareTo(Point that) {
    if(this.x == that.x && this.y == that.y) {
        return 0;
    }
    else if(this.y < that.y ) {
        return -1;
    }
    else if(this.y == that.y && this.x < that.x) {
        return -1;
    }
    else {
        return 1;
    }
}

    // the slope between this point and that point
    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
//    The slopeTo() method should return the slope between the invoking point (x0, y0) and the argument point (x1, y1),
//    which is given by the formula (y1 − y0) / (x1 − x0). Treat the slope of a horizontal line segment as positive zero;
//    treat the slope of a vertical line segment as positive infinity;
//    treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
    public double slopeTo(Point that) {
        double slope;
        if (this.x == that.x && this.y != that.y) {
            slope = Double.POSITIVE_INFINITY;
        }
        else if (this.x == that.x && this.y == that.y) {
            slope = Double.NEGATIVE_INFINITY;
        }
        else if (this.x != that.x && this.y == that.y) {
            slope = 0;
        }
        else {
            slope = (double) (that.y - this.y) / (that.x - this.x);
        }
        return slope;
    }

    // compare two points by slopes they make with this point
    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */

//    The slopeOrder() method should return a comparator that compares its two argument points by the slopes
//    they make with the invoking point (x0, y0). Formally, the point (x1, y1) is less than the point (x2, y2)
//    if and only if the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0).
//    Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
    public Comparator<Point> slopeOrder() {
        return new slopeComparator();
    }

    private class slopeComparator implements Comparator<Point> {
        public int compare(Point q, Point r) {
            if (q == null || r == null) {
                throw new NullPointerException();
            }

            if (slopeTo(q) < slopeTo(r)) {
                return -1;
            } else if (slopeTo(q) == slopeTo(r)) {
                return 0;
            } else {
                return 1;
            }
        }
    }

//    public static void main(String[] args) {
//        return
//    }
}