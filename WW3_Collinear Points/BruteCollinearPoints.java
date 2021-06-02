import java.util.Arrays;
import java.util.LinkedList;

// Brute force. Write a program BruteCollinearPoints.java that examines 4 points
//        at a time and checks whether they all lie on the same line segment,
//        returning all such line segments. To check whether the 4 points
//        p, q, r, and s are collinear, check whether the three slopes between
//        p and q, between p and r, and between p and s are all equal.

public class BruteCollinearPoints {

    private final Point[] arrPoint;
    private final LineSegment[] arrSegment;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        checkExceptionIIE(points);

        int lens = points.length;
        arrPoint = Arrays.copyOf(points, lens);
        Arrays.sort(arrPoint);

        // Corner cases. Throw an IllegalArgumentException if the argument to the constructor is null
        // if any point in the array is null, or if the argument to the constructor contains a repeated point.
        for (int i = 0; i < lens; i++) {
            if (i > 0 && arrPoint[i].slopeTo(arrPoint[i - 1]) == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException();
            }
        }

        LinkedList<LineSegment> templist = new LinkedList<>();

        for (int i = 0; i < lens; i++) {
            for (int j = i + 1; j < lens; j++) {
                for (int k = j + 1; k < lens; k++) {
                    for (int m = k + 1; m < lens; m++) {
                        Point p = arrPoint[i];
                        Point q = arrPoint[j];
                        Point r = arrPoint[k];
                        Point s = arrPoint[m];
                        double slope1 = p.slopeTo(q);
                        double slope2 = p.slopeTo(r);
                        double slope3 = p.slopeTo(s);
                        if (Double.compare(slope1, slope2) == 0 && Double.compare(slope2, slope3) == 0) {
                            templist.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }

        int size = templist.size();
        arrSegment = templist.toArray(new LineSegment[size]);
    }

    private void checkExceptionIIE(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return arrSegment.length;
    }

//    The method segments() should include each line segment containing 4 points exactly once.
//        If 4 points appear on a line segment in the order p→q→r→s,
//    then you should include either the line segment p→s or s→p (but not both)
//    and you should not include subsegments such as p→r or q→r. For simplicity,
//    we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.
    public LineSegment[] segments() {
//        return arrSegment;
        return Arrays.copyOf(arrSegment, arrSegment.length);
    }
}