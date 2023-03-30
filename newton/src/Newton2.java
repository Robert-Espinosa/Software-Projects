import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * computes square root of number entered into program.quits when user doesnt
 * enter y. also can not crash when 0 is entered.
 *
 * @author Robbie Espinosa
 *
 */
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        //make varible guess start at x
        double guess = x;

        //error is given as percent so convert to decimal
        final double error = .0001;

        //while our condition given to us isnt met keep updating your guess
        if (Double.compare(x, 0.0) != 0) {
            while (Math.abs(Math.pow(guess, 2) - x) / x >= Math.pow(error, 2)) {
                guess = (guess + x / guess) / 2;
            }
        }
        //return the guess after its within the error
        return guess;
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

        boolean playAgain = true;
        out.print("type y to compute a square root: ");
        String str = in.nextLine();
        playAgain = str.equalsIgnoreCase("y");

        //simple whileloop that drops in when you start
        while (playAgain) {
            //asks for number and stores in value
            out.print("Please enter a number to get the square root ");
            double value = in.nextDouble();

            //stores value of sqrt from method call
            double sqrt = sqrt(value);
            out.println("the square root is " + sqrt);

            //lastly asks to play again and either stays or breaks the while
            out.print("Enter y to enter another number ");
            str = in.nextLine();
            playAgain = str.equalsIgnoreCase("y");

        }

        in.close();
        out.close();
    }

}
