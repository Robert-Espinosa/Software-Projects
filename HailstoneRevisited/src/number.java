/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class number {
    private static int n;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    public number(int n) {
        this.n = n;

    }

    public static int power(int x) {
        return (int) Math.pow(n, x);
    }

    @Override
    public String toString() {
        return "number [n=" + this.n + "]";
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */

    public static void main(String[] args) {

    }

}
