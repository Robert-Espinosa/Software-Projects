import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class HelloWorld {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HelloWorld() {
        // no code needed here
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        SimpleWriter out = new SimpleWriter1L();

        int lowerBound = 0;
        int Upperbound = n + 1;
        int middle = (lowerBound + Upperbound) / 2;
        int guess = 0;

        while (guess != power(n, r)) {
            if (guess < middle) {
                Upperbound = (lowerBound + Upperbound) / 2;
                middle = (lowerBound + Upperbound) / 2;
                guess = middle;

            } else {
                lowerBound = (lowerBound + Upperbound) / 2;
                middle = (lowerBound + Upperbound) / 2;
                guess = middle;
            }
        }
        return guess;

    }

    private static int power(int n, int p) {
        int num = 1;
        for (int i = 0; i < p; i++) {
            n *= n;
        }
        return num;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println(root(16, 2));
        out.close();
    }

}
