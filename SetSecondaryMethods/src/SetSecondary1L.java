import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body
        Set<T> q = new Set1L<>();
        for (T i : s) {
            if (this.contains(i)) {
                q.add(this.remove(i));
            }
        }

        return q;
    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body
        int size = s.size();

        for (int i = 0; i < size; i++) {
            T remove = s.removeAny();
            if (!this.contains(remove)) {
                this.add(remove);
            } else {
                s.add(remove);
            }
        }

    }

    /**
     * Reports whether {@code this} is a subset of {@code s}. (A is a subset of
     * B exactly when every element of A is also an element of B.)
     *
     * @param s
     *            the second set
     * @return whether {@code this} is a subset of {@code s}
     * @ensures isSubset = (this is subset of s)
     */
    @Override
    public boolean isSubset(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";
        int size = s.size();
        Set<T> q = new Set1L<>();
        int count = 0;
        for (int i = 0; i < size; i++) {
            T remove = s.removeAny();
            if (this.contains(remove)) {
                count++;
            }
            q.add(remove);

        }
        s.transferFrom(q);
        return count == s.size();

    }
}
