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
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static NaturalNumber sumDigits(NaturalNumber n1) {
        NaturalNumber sum = new NaturalNumber2(0);
        while (!n1.isZero()) {
            int rem = n1.divideBy10();
            NaturalNumber remainder = new NaturalNumber2(rem);

            sum.add(remainder);
        }
        n1.clear();
        return sum;
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

        NaturalNumber[] array = new NaturalNumber[5];
        NaturalNumber count = new NaturalNumber2(51);
        out.print(sumDigits(count).toString());

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
