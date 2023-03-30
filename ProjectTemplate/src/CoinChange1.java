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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
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
        final int DOLLAR = 100;
        final int HALF_DOLLAR = 50;
        final int QUATER = 25;
        final int DIME = 10;
        final int NICKLE = 5;
        final int PENNY = 1;

        out.print("please enter the amount of cents you would like to convert");
        int orginalCents = in.nextInteger();
        int cents = orginalCents;
        int numDollars = 0;
        int numHalf = 0;
        int numQuater = 0;
        int numDime = 0;
        int numNickle = 0;
        int numPenny = 0;

        if (cents >= DOLLAR) {
            numDollars = cents / DOLLAR;
            cents -= DOLLAR * numDollars;
        }
        if (cents >= HALF_DOLLAR) {
            numHalf = cents / HALF_DOLLAR;
            cents -= HALF_DOLLAR * numHalf;

        }
        if (cents >= QUATER) {
            numQuater = cents / QUATER;
            cents -= QUATER * numQuater;
        }
        if (cents >= DIME) {
            numDime = cents / DIME;
            cents -= DIME * numDime;
        }
        if (cents >= NICKLE) {
            numNickle = cents / NICKLE;
            cents -= NICKLE * numNickle;
        }
        if (cents >= PENNY) {
            numPenny = cents / PENNY;
            cents -= PENNY * numPenny;
        }

        out.print("with a total of " + orginalCents
                + " cents, Your change will be");
        out.print("\n " + numDollars + " Dollars");
        out.print("\n " + numHalf + " Half Dollars");
        out.print("\n " + numQuater + " Quaters");
        out.print("\n " + numDime + " Dimes");
        out.print("\n " + numNickle + " Nickles");
        out.print("\n " + numPenny + " pennys");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
