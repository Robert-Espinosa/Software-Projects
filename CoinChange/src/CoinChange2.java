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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
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
         * Put your main program code here
         */
        final int coinSize = 6;

        int[] coinsCounts = new int[coinSize];

        final int[] coinValue = { 100, 50, 25, 10, 5, 1 };

        String[] names = { "dollars: ", "half dollars: ", "quaters: ",
                "dimes: ", "nickles: ", "pennies: " };
        out.print(
                "please enter the amount of cents you would like to convert :");
        double cents = in.nextDouble();
        int i = 0;

        while (cents > 0) {
            coinsCounts[i] = (int) cents / coinValue[i];
            cents %= coinValue[i];
            i++;
        }

        for (int j = 0; j < coinsCounts.length; j++) {
            out.println(names[j] + coinsCounts[j]);

        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
