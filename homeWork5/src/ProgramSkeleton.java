import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramSkeleton {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramSkeleton() {
    }

    /**
     * computes the sum of even numbers from 1-100.
     *
     * @return sum from 1-100 even number
     */
    private static int sumEven() {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    /**
     * computes the sum of powers 0-20.
     *
     * @return sum of power 2^0 - 2^20
     */
    private static int sumPower() {
        int sum = 0;
        for (int i = 0; i <= 20; i++) {
            sum += Math.pow(2, i);
        }
        return sum;
    }

    /**
     * computes the sum of odd numbers from A-B
     *
     * @return sum from 1-100 even number
     */
    private static int sumEven(int a, int b) {
        int sum = 0;
        for (int i = 1; i <= b; i++) {
            if (i % 2 == 1) {
                sum += i;
            }
        }
        return sum;
    }

    /**
     * computes the sum of all digits in number.
     *
     * @return sum of digits in number
     */
    private static int sumDigits(int a) {
        int sum = 0;
        while (a > 0) {
            sum += a % 10;
            a /= 10;
        }

        return sum;
    }

    /**
     * computes the sum of all digits odd positions.
     *
     * @return sum of digits in number
     */
    private static int sumOdd(int a) {
        int sum = 0;
        while (a > 0) {
            sum += a % 10;
            a /= 100;
        }

        return sum;
    }

    /**
     * computes the sum of all digits odd positions from front.
     *
     * @return sum of digits in number
     */
    private static int sumOddFront(int a) {
        String sum = String.valueOf(a);
        int counter = 0;
        int orginal = a;
        int i = 0;
        while (orginal > 0) {

            int placeHolder = Integer.parseInt(sum.substring(i, i + 1));

            counter += placeHolder;
            orginal /= 100;
            i += 2;
        }

        return counter;
    }

    private static boolean alldiffernt(int x, int y, int z) {
        return x < y && y < z && x < z;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Put your main program code here
         */
        SimpleWriter out = new SimpleWriter1L();

        System.out.println(alldiffernt(1, 1, 1));
        System.out.println(alldiffernt(2, 1, 1));
        System.out.println(alldiffernt(1, 2, 1));
        System.out.println(alldiffernt(1, 2, 3));
    }

}
