import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone3 {

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int value = n;
        int counter = 1;
        int max = value;
        out.print(value + ", ");
        while (value > 1) {
            if (value % 2 == 0) {
                value /= 2;
                counter++;
                if (value > max) {
                    max = value;
                }
                if (value == 1) {
                    out.print(value);
                } else {
                    out.print(value + ", ");
                }
            } else {
                value = 3 * value + 1;
                counter++;
                if (value > max) {
                    max = value;
                }
                if (value == 1) {
                    out.print(value);
                } else {
                    out.print(value + ", ");
                }
            }
        }

        out.print("\nThe length of the series is: " + counter);
        out.print("\nThe largest number in the series is: " + max);

    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone3() {

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("please enter a number ");
        int number = in.nextInteger();

        generateSeries(number, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
