import java.util.Iterator;

import components.queue.Queue;
import components.queue.QueueSecondary;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code Queue} represented as a {@code Sequence} of entries, with
 * implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @correspondence this = $this.entries
 */
public class Queue3<T> extends QueueSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Entries included in {@code this}.
     */
    private Sequence<T> entries;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.entries = new Sequence1L<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Queue3() {
        this.createNewRep();
    }

    /*
     * Standard methods removed to reduce clutter...
     */

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void enqueue(T x) {
        assert x != null : "Violation of: x is not null";

        this.entries.add(this.entries.length(), x);

    }

    @Override
    public final T dequeue() {
        assert this.length() > 0 : "Violation of: this /= <>";

        // TODO - fill in body
        return this.entries.remove(0);

        // This line added just to make the component compilable.
    }

    @Override
    public final int length() {

        // TODO - fill in body
        return this.entries.length();

        // This line added just to make the component compilable.
    }

    public final void secondaryInstance() {

        // TODO - fill in body
        this.clear();
        // This line added just to make the component compilable.
    }

    @Override
    public final T front() {

        // TODO - fill in body
        return this.entries.entry(0);

        // This line added just to make the component compilable.
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public Queue<T> newInstance() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void transferFrom(Queue<T> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * Iterator removed to reduce clutter...
     */

}
