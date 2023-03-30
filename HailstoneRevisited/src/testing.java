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
public final class testing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private testing() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static String reverseString(String s) {
        /*
         * Put your code for myMethod here
         */
        String rev = "";

        for (int i = s.length() - 1; i > 0; i--) {
            rev += s.charAt(i);
        }
        return rev;
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

//        int n = 100;
//        int test = 0;
//        while (test < n) {
//            int root = (int) Math.sqrt(test);
//            if (root * root == test) {
//                output.print(test + " ");
//            }
//            test++;
//        }
        int n = 100;
        int test = 0;
        while (test < n) {
            if (test % 10 == 0) {
                output.print(test + " ");
            }
            test++;
        }
        input.close();
        output.close();
    }

}
