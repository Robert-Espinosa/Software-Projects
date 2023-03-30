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
public final class ProgramWithIOAndStaticMethod {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIOAndStaticMethod() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
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

        NaturalNumber max = new NaturalNumber2(n);
        int count = 1;

        NaturalNumber one = new NaturalNumber2(1);

        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber three = new NaturalNumber2(3);

        while (n.compareTo(one) > 0) {

            out.print(n.toString() + " ");

            NaturalNumber rem = n.divide(two);

            if (rem.isZero()) {
                count++;

                if (max.compareTo(n) < 0) {
                    max.copyFrom(n);
                }
            } else {
                n.multiply(two);
                n.add(rem);
                count++;
                n.multiply(three);
                n.add(one);

                if (max.compareTo(n) < 0) {
                    max.copyFrom(n);

                }
            }
        }
        out.print(n.toString());
        out.print("\nyour series has a length of " + count);
        out.print("\nThe biggest number in your series is " + max);
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
//        NaturalNumber n = new NaturalNumber2(6);
//        NaturalNumber b = new NaturalNumber2(5);
//
//        b.transferFrom(n);
//        out.println(b);
//        n.increment();
//        ;
//        out.print(n);

        String s = "hello";
        s = "spospsp";
        int[] b = { 1, 2, 3, 4 };
        int[] a = { 1, 2, 3, 4 };
        a = b;

        out.print(a == b);

//        out.print(q);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
