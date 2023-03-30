import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * ** computes square root of number entered into program.quits when user doesnt
 * enter y. also can not crash when 0 is entered. Also asks user for error and
 * uses it in method. quits when negetive number is entered
 *
 * @author Robbie Espinosa
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param error
     *            positive decimal to determine the error of the square root
     * @return estimate of square root
     */
    private static double sqrt(double x, double error) {
        //make varible guess start at x
        double guess = x;

        //error is given as percent so convert to decimal

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

        out.print("please enter a value for error ");
        double error = in.nextDouble();

        //simple whileloop that drops in when you start
        out.print("Please enter a number to get the square root ");
        double value = in.nextDouble();

        while (value >= 0) {

            //stores value of sqrt from method call
            double sqrt = sqrt(value, error);
            out.println("the square root is " + sqrt);

            out.print("Please enter a number to get the square root ");
            value = in.nextDouble();

        }

        in.close();
        out.close();
    }

}
