import java.util.LinkedList;
import java.util.List;

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
    private static String list(String s) {
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

        List<Integer> list = new LinkedList<>();
        list.add(7);
        list.add(-12);
        output.print(list.toString());

        input.close();

        output.close();
    }

}
