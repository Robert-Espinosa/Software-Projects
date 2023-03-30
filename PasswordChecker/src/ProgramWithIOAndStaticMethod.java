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
public final class ProgramWithIOAndStaticMethod {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIOAndStaticMethod() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate,
            SimpleWriter out) {

        boolean hasTwo = true;
        if (checkLength(passwordCandidate)) {
            out.print("Password too short ");
            hasTwo = false;
        }
        if (hasTwo) {
            if (!containsUpperCaseLetter(passwordCandidate)) {
                out.println("password needs upperCase ");
                hasTwo = false;
            }
            if (!containsLowerCaseLetter(passwordCandidate)) {
                out.println("password needs lowerCase ");
                hasTwo = false;
            }
            if (!containsDigit(passwordCandidate)) {
                out.println("password needs digit ");
                hasTwo = false;
            }
            if (!containsSpecialChar(passwordCandidate)) {
                out.println("you need a special charater");
                hasTwo = false;
            }
        }

        if (hasTwo) {
            out.println("all reqierments met ");
        }
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param str
     *            the String to check
     * @return returns boolean weather length is greater than 8
     */
    private static boolean checkLength(String str) {
        final int minLength = 8;
        return str.length() < minLength;

    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        boolean upperCase = false;
        for (int i = 0; i < str.length() && !upperCase; i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                upperCase = true;
            }
        }
        return upperCase;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        boolean lowerCase = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i))) {
                lowerCase = true;
            }
        }
        return lowerCase;
    }

    /**
     * Checks if the given String contains digit.
     *
     * @param str
     *            the String to check
     * @return true if str contains an digit, false otherwise
     */
    private static boolean containsSpecialChar(String str) {
        String special = "!@#$%^&*()_-+={}[]:;,.?";
        boolean containsSpecail = false;
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < special.length(); j++) {
                if (str.charAt(i) == special.charAt(j)) {
                    containsSpecail = true;
                }
            }
        }
        return containsSpecail;
    }

    /**
     * Checks if the given String contains digit.
     *
     * @param str
     *            the String to check
     * @return true if str contains an digit, false otherwise
     */
    private static boolean containsDigit(String str) {
        boolean hasDigit = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                hasDigit = true;
            }
        }
        return hasDigit;
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

        out.print("Please enter your password ");
        String password = in.nextLine();

        while (!password.equals("")) {
            checkPassword(password, out);

            out.print("please enter another password ");
            password = in.nextLine();
        }
        in.close();
        out.close();
    }

}
