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
        int dollars = 0;
        int halfDollars = 0;
        int quaters = 0;
        int dimes = 0;
        int nickles = 0;
        int pennies = 0;

        final int dollarValue = 100;
        final int halfDollarValue = 50;
        final int quaterValue = 25;
        final int dimeValue = 10;
        final int nickleValue = 5;

        out.print(
                "please enter the amount of cents you would like to convert :");
        int cents = in.nextInteger();

        dollars = (cents / dollarValue);
        cents %= dollarValue;

        halfDollars = (cents / halfDollarValue);
        cents %= halfDollarValue;

        quaters = (cents / quaterValue);
        cents %= quaterValue;

        dimes = (cents / dimeValue);
        cents %= dimeValue;

        nickles = (cents / nickleValue);
        cents %= nickleValue;

        pennies = (cents);

        out.println("dollars: " + dollars);
        out.println("half dollars: " + halfDollars);
        out.println("quaters: " + quaters);
        out.println("dimes: " + dimes);
        out.println("nickles: " + nickles);
        out.println("pennies: " + pennies);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
