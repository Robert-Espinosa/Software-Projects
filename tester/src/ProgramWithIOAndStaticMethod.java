import components.queue.Queue;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
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
     * Reports the smallest integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return the smallest integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * min is in entries(q) and
     * for all x: integer
     *     where (x is in entries(q))
     *   (min <= x)
     * </pre>
     */
    private static int min(Queue<Integer> q) {
        int min = q.dequeue();
        q.enqueue(min);
        for (int i = 0; i < q.length(); i++) {
            int guess = q.dequeue();
            if (guess < min) {
                min = guess;
            }
            q.enqueue(guess);
        }
        return min;
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax(Queue<Integer> q) {
        int[] minAndMax = new int[2];
        int min = q.dequeue();
        q.enqueue(min);
        for (int i = 0; i < q.length(); i++) {
            int guess = q.dequeue();
            if (guess < min) {
                min = guess;
            }
            q.enqueue(guess);
        }
        minAndMax[0] = min;

        int max = q.dequeue();
        q.enqueue(max);
        for (int i = 0; i < q.length(); i++) {
            int g = q.dequeue();
            if (g > max) {
                max = g;
            }
            q.enqueue(g);
        }
        minAndMax[1] = max;

        return minAndMax;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter output = new SimpleWriter1L();
        Sequence<Integer> t = new Sequence1L<>();
        Sequence<Integer> fliped = new Sequence1L<>();

        t.add(0, 6);

        t.add(1, 2);
        t.add(2, 9);
        t.add(3, 8);
        t.add(4, 3);
        t.add(5, 12);
        t.add(6, 19);

        for (int i = t.length() - 1, x = 0; i >= 0; i--, x++) {

            fliped.add(x, t.entry(i));
        }

        output.println(t.toString());
        output.println(fliped.toString());

        output.close();
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public void flip() {
        Sequence<Integer> fliped = new Sequence1L<>();
        for (int i = this.length() - 1, x = 0; i >= 0; i--, x++) {
            flipped.add(x, this.entry(i));
        }

        this.transferfrom(flipped);
    }
}
