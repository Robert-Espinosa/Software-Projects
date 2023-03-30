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
public final class ProgramWithIO {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * asks the user to input a number using print command
         */
        output.print("Enter a nuuber to print all sqares before that number");
        /*
         * stores number that they enter into variable n
         */
        int n = input.nextInteger();
        /*
         * initialize i to zero as starting point
         */
        int i = 1;
        /*
         * set divisor to 10
         */
        int count = 0;
        /*
         * start while loop to continue from zero till n to check every number
         * up to n
         */
        while (i < n) {
            if (i > 0) {

                if (Math.pow(2, count) < n) {
                    output.print(Math.pow(i, i + 1) + " ");
                }
            }
            count++;
            i++;
        }
        input.close();
        output.close();
    }

}
