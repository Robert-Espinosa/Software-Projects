import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Program estimates the value of the user by looping through every possiblity
 * using while loops.
 *
 * @author Robbie Espinosa
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String input = "";
        double userInput = -1;

        out.println("Please enter a positive real number to be estimated  ");
        input = in.nextLine();
        if (FormatChecker.canParseDouble(input)) {
            userInput = Double.parseDouble(input);
        }

        while (userInput <= 0) {
            out.println("ERROR Please enter a positive real "
                    + "number to be estimated  ");
            input = in.nextLine();
            if (FormatChecker.canParseDouble(input)) {
                userInput = Double.parseDouble(input);
            }
        }

        return userInput;

    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        String input = "";
        double userInput = -1;

        out.println("Please enter a positive real number not equal to 1 ");
        input = in.nextLine();
        if (FormatChecker.canParseDouble(input)) {
            userInput = Double.parseDouble(input);
        }

        while (userInput <= 0 || Double.compare(userInput, 1.0) == 0) {
            out.println(
                    "ERROR Please enter a positive real number not equal to 1 ");
            input = in.nextLine();
            if (FormatChecker.canParseDouble(input)) {
                userInput = Double.parseDouble(input);
            }
        }

        return userInput;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        final double[] numbers = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3,
                -1.0 / 4, 0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };

        final int oneHundred = 100;
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        double answer = getPositiveDouble(in, out);

        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        double percentError = ((Math.pow(w, numbers[0])
                * Math.pow(x, numbers[0]) * Math.pow(y, numbers[0])
                * Math.pow(z, numbers[0]))) - answer;

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        double finalA = 0;
        double finalB = 0;
        double finalC = 0;
        double finalD = 0;

        while (d < numbers.length) {

            while (c < numbers.length) {

                while (b < numbers.length) {

                    while (a < numbers.length) {

                        double guess = ((Math.pow(w, numbers[a])
                                * Math.pow(x, numbers[b])
                                * Math.pow(y, numbers[c])
                                * Math.pow(z, numbers[d]))) - answer;

                        if (Math.abs(guess) < Math.abs(percentError)) {
                            percentError = guess;
                            finalA = numbers[a];
                            finalB = numbers[b];
                            finalC = numbers[c];
                            finalD = numbers[d];

                        }
                        a++;
                    }
                    b++;
                    a = 0;
                }
                c++;
                b = 0;
            }
            d++;
            c = 0;
        }

        double finalGuess = (Math.pow(w, finalA) * Math.pow(x, finalB)
                * Math.pow(y, finalC) * Math.pow(z, finalD));

        double error = ((Math.abs(answer - finalGuess)) / answer) * oneHundred;
        out.println("A = " + finalA);
        out.println("B = " + finalB);
        out.println("C = " + finalC);
        out.println("D = " + finalD);
        out.println("estimated value = " + finalGuess);

        out.print(
                "The percent error is " + String.format("%.2f", error) + " %");

    }

}
