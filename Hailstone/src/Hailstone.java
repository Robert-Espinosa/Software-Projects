import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone {

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        int postive = -1;
        out.print("Please enter a postive number: ");

        String number = in.nextLine();

        while (postive < 0 || !FormatChecker.canParseInt(number)) {
            out.print("Please enter a postive number: ");
            number = in.nextLine();
            if (FormatChecker.canParseInt(number)) {
                postive = Integer.parseInt(number);
            }
        }

        return postive;
    }

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
        out.print(value + ", ");
        while (value > 1) {
            if (value % 2 == 0) {
                value /= 2;
                if (value == 1) {
                    out.print(value);
                } else {
                    out.print(value + ", ");
                }
            } else {
                value = 3 * value + 1;
                if (value == 1) {
                    out.print(value);
                } else {
                    out.print(value + ", ");
                }
            }
        }
    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone() {

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

        generateSeries(getPositiveInteger(in, out), out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
