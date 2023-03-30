import java.util.Iterator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.set.SetSecondary;

/**
 * {@code Sequence} represented as a pair of {@code Stack}s with implementations
 * of primary methods.
 *
 * @param <T>
 *            type of {@code Sequence} entries
 * @correspondence this = rev($this.left) * $this.right
 */
public class Set<T> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Left stack.
     */
    private Queue<T> q;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.q = new Queue1L<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public void add(T arg0) {
        // TODO Auto-generated method stub
        this.q.enqueue(arg0);
    }

    @Override
    public boolean contains(T arg0) {
        // TODO Auto-generated method stub
        boolean hasValue = false;
        Queue<T> temp = this.q.newInstance();

        for (int i = 0; i < this.q.length(); i++) {
            T check = this.q.dequeue();
            if (check.equals(arg0)) {
                hasValue = true;
            }
            temp.enqueue(check);
        }
        this.q.transferFrom(temp);
        return hasValue;
    }

    @Override
    public T remove(T arg0) {
        // TODO Auto-generated method stub
        T ret = arg0;
        for (int i = 0; i < this.q.length(); i++) {
            T check = this.q.dequeue();
            if (!check.equals(arg0)) {
                this.q.enqueue(check);
            } else {
                ret = check;
            }
        }
        return ret;
    }

    @Override
    public T removeAny() {
        // TODO Auto-generated method stub
        int randomIndex = (int) (Math.random() * this.q.length());
        T ret = this.q.dequeue();

        for (int i = 0; i < randomIndex; i++) {
            T check = this.q.dequeue();
            if (i != randomIndex - 1) {
                this.q.enqueue(check);
            } else {
                ret = check;
            }
        }
        return ret;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return this.q.length();
    }

    @Override
    public components.set.Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public void transferFrom(components.set.Set<T> source) {
        // TODO Auto-generated method stub
        for (int i = 0; i < source.size(); i++) {
            this.q.enqueue(source.removeAny());
        }

    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return this.q.iterator();
    }

}
