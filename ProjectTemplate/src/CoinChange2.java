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

        out.print(
                "please enter the amount of cents you would like to convert ");
        int orginalCents = in.nextInteger();
        int cents = orginalCents;
        int[] coinCounts = new int[6];
        int[] coinValues = { 100, 50, 25, 10, 5, 1 };

        for (int i = 0; i < coinCounts.length; i++) {
            coinCounts[i] = cents / coinValues[i];
            cents -= coinValues[i] * coinCounts[i];
        }

        out.print("with a total of " + orginalCents
                + " cents, Your change will be");
        out.print("\n " + coinCounts[0] + " Dollars");
        out.print("\n " + coinCounts[1] + " Half Dollars");
        out.print("\n " + coinCounts[2] + " Quaters");
        out.print("\n " + coinCounts[3] + " Dimes");
        out.print("\n " + coinCounts[4] + " Nickles");
        out.print("\n " + coinCounts[5] + " pennys");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
