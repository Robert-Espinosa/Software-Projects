import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    /**
     *
     */
    @Test
    public void constructorTest1() {

        Set<String> test = this.createFromArgsTest("alex", "robbie");
        Set<String> expected = this.createFromArgsRef("alex", "robbie");
        assertEquals(test, expected);

    }

    /**
    *
    */
    @Test
    public void addTest() {

        Set<String> test = this.createFromArgsTest("alex", "robbie");
        Set<String> expected = this.createFromArgsRef("alex", "robbie",
                "matthew");

        test.add("matthew");
        assertEquals(test, expected);

    }

    /**
    *
    */
    @Test
    public void addTestEdge() {

        Set<String> test = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef("matthew");

        test.add("matthew");
        assertEquals(test, expected);

    }

    /**
    *
    */
    @Test
    public void removeTest() {

        Set<String> test = this.createFromArgsTest("alex", "robbie");
        Set<String> expected = this.createFromArgsRef("alex");

        String removed = test.remove("robbie");

        assertEquals(removed, "robbie");
        assertEquals(expected, test);

    }

    /**
    *
    */
    @Test
    public void containsTest() {

        Set<String> test = this.createFromArgsTest("alex", "robbie");
        Set<String> expected = this.createFromArgsRef("alex", "robbie");

        assertEquals(test.contains("alex"), expected.contains("alex"));
        assertEquals(test, expected);

    }

}
