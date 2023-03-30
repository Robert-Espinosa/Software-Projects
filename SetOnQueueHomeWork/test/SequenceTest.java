import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            set.add(s);
        }
        return set;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length

    /**
     *
     */
    @Test
    public void TestAdd() {
        Set<String> test = this.createFromArgsTest("1", "2");
        Set<String> expected = this.createFromArgsTest("1", "2", "3");

        test.add("3");
        assertEquals(test, expected);

    }

    /**
    *
    */
    @Test
    public void TestAdd3() {
        Set<String> test = this.createFromArgsTest("1", "2");
        Set<String> expected = this.createFromArgsTest("1", "2", "3");

        test.add("3");
        assertEquals(test, expected);

    }

    /**
    *
    */
    @Test
    public void TestRemove() {
        Set<String> test = this.createFromArgsTest("1", "2");
        Set<String> expected = this.createFromArgsTest("1");

        test.remove("2");
        assertEquals(test, expected);

    }

    /**
    *
    */
    @Test
    public void TestRemove2() {
        Set<String> test = this.createFromArgsTest("5", "4");
        Set<String> expected = this.createFromArgsTest("4");

        test.remove("5");
        assertEquals(test, expected);

    }

    /**
    *
    */
    @Test
    public void TestContains() {
        Set<String> test = this.createFromArgsTest("5", "4");

        boolean correct = test.contains("5");
        assertEquals(correct, true);
//        assertEquals(test, expected);

    }

    /**
    *
    */
    @Test
    public void TestContains2() {
        Set<String> test = this.createFromArgsTest("5", "122");

        boolean correct = test.contains("6");
        assertEquals(correct, false);

    }

    /**
    *
    */
    @Test
    public void size() {
        Set<String> test = this.createFromArgsTest("5", "122");

        int num = test.size();
        assertEquals(num, 2);

    }

    /**
    *
    */
    @Test
    public void size3() {
        Set<String> test = this.createFromArgsTest();

        int num = test.size();
        assertEquals(num, 0);

    }

    /**
    *
    */
    @Test
    public void size4() {
        Set<String> test = this.createFromArgsTest("5", "122", "2", "4");

        int num = test.size();
        assertEquals(num, 4);

    }
}
