import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Program estimates the value of the user by looping through every possiblity
 * using for loops.
 *
 * @author Robbie Espinosa
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
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

        out.println("Please enter a positive real number to be estimated ");
        input = in.nextLine();
        if (FormatChecker.canParseDouble(input)) {
            userInput = Double.parseDouble(input);
        }

        while (userInput <= 0) {
            out.println("ERROR Please enter a positive "
                    + "real number to be estimated");
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
     * Prints out all the results from the parameters passed.
     *
     * @param finalA
     *            final value for A after method runs
     * @param finalB
     *            final value for B after method runs
     * @param finalC
     *            final value for C after method runs
     * @param finalD
     *            final value for D after method runs
     * @param finalGuess
     *            the closest approx that was found
     * @param answer
     *            the orgninal answer that was given in the beginning
     * @param out
     *            output steam so the method is able to print
     */
    private static void printRestults(double finalA, double finalB,
            double finalC, double finalD, double finalGuess, double answer,
            SimpleWriter out) {

        final int oneHundred = 100;
        double error = ((Math.abs(answer - finalGuess)) / answer) * oneHundred;

        out.println("A = " + finalA);
        out.println("B = " + finalB);
        out.println("C = " + finalC);
        out.println("D = " + finalD);
        out.println("estimated value = " + finalGuess);

        out.print(
                "The percent error is " + String.format("%.2f", error) + " %");

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

        for (d = 0; d < numbers.length; d++) {
            for (c = 0; c < numbers.length; c++) {
                for (b = 0; b < numbers.length; b++) {
                    for (a = 0; a < numbers.length; a++) {

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
                    }
                    a = 0;
                }
                b = 0;
            }
            c = 0;
        }

        double finalGuess = (Math.pow(w, finalA) * Math.pow(x, finalB)
                * Math.pow(y, finalC) * Math.pow(z, finalD));

        printRestults(finalA, finalB, finalC, finalD, finalGuess, answer, out);

    }

}
