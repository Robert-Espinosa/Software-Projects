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
        assertEquals(expected, test);

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
        assertEquals(expected, test);

    }

    /**
    *
    */
    @Test
    public void addTestEdge() {

        Set<String> test = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef("matthew");

        test.add("matthew");
        assertEquals(expected, test);

    }

    /**
    *
    */
    @Test
    public void removeTest() {

        Set<String> test = this.createFromArgsTest("alex", "robbie");
        Set<String> expected = this.createFromArgsRef("alex");

        String removed = test.remove("robbie");

        assertEquals("robbie", removed);
        assertEquals(expected, test);

    }

    /**
    *
    */
    @Test
    public void removeTestEdge() {

        Set<String> test = this.createFromArgsTest("alex");
        Set<String> expected = this.createFromArgsRef();

        String removed = test.remove("alex");

        assertEquals("alex", removed);
        assertEquals(expected, test);

    }

    /**
    *
    */
    @Test
    public void removeAnyTest() {

        Set<String> test = this.createFromArgsTest("alex");
        Set<String> expected = this.createFromArgsRef();

        String removed = test.remove("alex");

        assertEquals("alex", removed);
        assertEquals(expected, test);

    }

    /**
    *
    */
    @Test
    public void containsTest() {

        Set<String> test = this.createFromArgsTest("alex", "robbie");
        Set<String> expected = this.createFromArgsRef("alex", "robbie");

        assertEquals(true, test.contains("alex"));
        assertEquals(expected, test);

    }

    /**
    *
    */
    @Test
    public void containsTest2() {

        Set<String> test = this.createFromArgsTest("alex", "robbie");
        Set<String> expected = this.createFromArgsRef("alex", "robbie");

        assertEquals(false, test.contains("matthew"));
        assertEquals(expected, test);

    }

    /**
    *
    */
    @Test
    public void containsTestEdge() {

        Set<String> test = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef();

        assertEquals(false, test.contains("matthew"));
        assertEquals(expected, test);

    }

    /**
    *
    */
    @Test
    public void sizeTest() {

        Set<String> test = this.createFromArgsTest("alex", "robbie", "matthew");
        Set<String> expected = this.createFromArgsRef("alex", "robbie",
                "matthew");

        int size = test.size();
        assertEquals(3, size);
        assertEquals(expected, test);

    }

    /**
    *
    */
    @Test
    public void sizeTestEdge() {

        Set<String> test = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef();

        int size = test.size();
        assertEquals(0, size);
        assertEquals(expected, test);

    }

}
