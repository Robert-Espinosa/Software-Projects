import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size
    @Test
    public final void testAdd() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,
                "robbie", "alex");
        SortingMachine<String> expected = this.createFromArgsTest(ORDER, true,
                "robbie", "alex", "jackson");

        test.add("jackson");
        assertEquals(expected, test);
    }

    @Test
    public final void testAdd2() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,
                "robbie", "alex", "jackson", "matt", "collin");
        SortingMachine<String> expected = this.createFromArgsRef(ORDER, true,
                "robbie", "alex", "jackson", "matt", "collin", "gus");

        test.add("gus");
        assertEquals(expected, test);
    }

    @Test
    public final void sizeTest1() {
        //test a normal size case of size 2
        int size = 4;
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "red", "purple");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, true,
                "green", "blue", "red", "purple");

        assertEquals(size, m.size());
        assertEquals(m, mExpected);

    }

    @Test
    public final void sizeTest2() {
        //test the size an empty sortingMachine

        int size = 0;
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, true);

        assertEquals(size, m.size());
        assertEquals(m, mExpected);

    }

    @Test
    public final void sizeTest3() {
        //test the size or nomal case

        int size = 2;
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, true,
                "green", "blue");

        assertEquals(size, m.size());
        assertEquals(m, mExpected);

    }

    @Test
    public final void sizeTest4() {
        //test with extraction mode false normal case

        int size = 2;
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, false,
                "green", "blue");

        assertEquals(size, m.size());
        assertEquals(m, mExpected);

    }

    @Test
    public final void sizeTest5() {
        //test with extraction mode false empty case

        int size = 0;
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER,
                false);

        assertEquals(size, m.size());
        assertEquals(m, mExpected);

    }

    @Test
    public final void orderTest() {
        //test case for normal order and check that origanl order is
        // equal to m.order and that this doesn't effect anything else
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c");

        assertEquals(ORDER, m.order());
        assertEquals(m, mExpected);
    }

    @Test
    public final void orderTest2() {
        //test case for when extraction mode is false
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "c");

        assertEquals(ORDER, m.order());
        assertEquals(m, mExpected);
    }

    @Test
    public final void testChangeToExtractionModeEmptyTest() {
        //checks that changing to extraction mode works with empty
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> expected = this.createFromArgsTest(ORDER, false);

        test.changeToExtractionMode();
        assertEquals(expected, test);
    }

    @Test
    public final void testChangeToExtractionModeNonEmpty() {
        //checks that changing to extraction mode works with a non empty

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,
                "robbie", "nate", "ben");
        SortingMachine<String> expected = this.createFromArgsTest(ORDER, false,
                "robbie", "nate", "ben");

        test.changeToExtractionMode();
        assertEquals(expected, test);
    }

    @Test
    public final void removeFirst() {
        //check That normal remove first case works
        SortingMachine<String> test = this.createFromArgsTest(ORDER, false,
                "robbie", "nate", "ben");
        SortingMachine<String> expected = this.createFromArgsTest(ORDER, false,
                "robbie", "nate");

        String name = test.removeFirst();
        assertEquals(expected, test);
        assertEquals("ben", name);

    }

    @Test
    public final void removeFirst2() {
        //check That edge case works with only one string

        SortingMachine<String> test = this.createFromArgsTest(ORDER, false,
                "rick");
        SortingMachine<String> expected = this.createFromArgsTest(ORDER, false);

        String name = test.removeFirst();
        assertEquals(expected, test);
        assertEquals("rick", name);
    }

    @Test
    public final void isInInsertionModeTest() {
        /*
         * check that normal case works for true and that sorting machine was
         * unaffected by call.
         */
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,
                "rick");
        SortingMachine<String> expected = this.createFromArgsTest(ORDER, true,
                "rick");

        Boolean returnVal = test.isInInsertionMode();

        assertEquals(expected, test);
        assertEquals(true, returnVal);
    }

    @Test
    public final void isInInsertionModeTest2() {
        /*
         * check that normal case works for false and that sorting machine was
         * unaffected by call.
         */

        SortingMachine<String> test = this.createFromArgsTest(ORDER, false,
                "rick");
        SortingMachine<String> expected = this.createFromArgsTest(ORDER, false,
                "rick");

        Boolean returnVal = test.isInInsertionMode();

        assertEquals(expected, test);
        assertEquals(false, returnVal);
    }

    @Test
    public final void isInInsertionModeTest3() {
        /*
         * check that edge case works for false and that sorting machine was
         * unaffected by call.
         */

        SortingMachine<String> test = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> expected = this.createFromArgsTest(ORDER, false);

        Boolean returnVal = test.isInInsertionMode();

        assertEquals(expected, test);
        assertEquals(false, returnVal);
    }

    @Test
    public final void isInInsertionModeTest4() {
        /*
         * check that edge case works for true and that sorting machine was
         * unaffected by call.
         */

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> expected = this.createFromArgsTest(ORDER, true);

        Boolean returnVal = test.isInInsertionMode();

        assertEquals(expected, test);
        assertEquals(true, returnVal);
    }
}
