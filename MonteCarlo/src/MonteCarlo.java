import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        //use distance formula in order to check if in circle.
        return (Math
                .sqrt(Math.pow(1 - xCoord, 2) + Math.pow(1 - yCoord, 2)) < 1);

    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        int ptsInInterval = 0;
        int ptsInSubinterval = 0;
        Random rnd = new Random1L();

        while (ptsInInterval < n) {

            double x = rnd.nextDouble() * 2;

            double y = rnd.nextDouble() * 2;

            ptsInInterval++;

            if (pointIsInCircle(x, y)) {
                ptsInSubinterval++;
            }
        }
        return ptsInSubinterval;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();

//        numberOfPointsInCircle(n);
        double estemaitedArea = numberOfPointsInCircle(n) / (double) n;
        output.print(
                "The estimated area of the circle is " + estemaitedArea * 4);
        /*
         * Estimate percentage of points generated in [0.0,1.0) interval that
         * fall in the [0.0,0.5) subinterval
         */
//        double estimate = (100.0 * ptsInSubinterval) / ptsInInterval;
//        output.println("Estimate of percentage: " + estimate + "%");
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}
