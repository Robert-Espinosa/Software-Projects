import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class Hailstone {

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        NaturalNumber copy = new NaturalNumber2(n);

        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber three = new NaturalNumber2(3);

        NaturalNumber rem = new NaturalNumber2(0);

        out.print(copy + ", ");
        while (copy.compareTo(one) > 0) {
            rem = copy.divide(two);
            copy.multiply(two);
            copy.add(rem);
            if (rem.compareTo(zero) == 0) {
                copy.divide(two);

            } else {
                copy.multiply(three);
                copy.increment();
            }

            if (copy.compareTo(one) == 0) {
                out.print(copy);
            } else {
                out.print(copy + ", ");
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

        out.print("please enter a number ");
        int number = in.nextInteger();

        NaturalNumber input = new NaturalNumber2(number);
        generateSeries(input, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
