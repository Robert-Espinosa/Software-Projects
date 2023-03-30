import components.binarytree.BinaryTree;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;

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
     * Shifts entries between {@code leftStack} and {@code rightStack}, keeping
     * reverse of the former concatenated with the latter fixed, and resulting
     * in length of the former equal to {@code newLeftLength}.
     *
     * @param <T>
     *            type of {@code Stack} entries
     * @param leftStack
     *            the left {@code Stack}
     * @param rightStack
     *            the right {@code Stack}
     * @param newLeftLength
     *            desired new length of {@code leftStack}
     * @updates leftStack, rightStack
     * @requires <pre>
     * 0 <= newLeftLength  and
     * newLeftLength <= |leftStack| + |rightStack|
     * </pre>
     * @ensures <pre>
     * rev(leftStack) * rightStack = rev(#leftStack) * #rightStack  and
     * |leftStack| = newLeftLength}
     * </pre>
     */
    private static <T> void setLengthOfLeftStack(Stack<T> leftStack,
            Stack<T> rightStack, int newLeftLength) {

    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */

    public static void main(String[] args) {

        SimpleWriter out = new SimpleWriter1L();
        NaturalNumber n1 = new NaturalNumber1L("");
        BinaryTree<Integer> name new BinaryTree3<Integer>();

        out.print(n1);

        n1.multiplyBy10(9);
        out.print(n1);

    }

}
