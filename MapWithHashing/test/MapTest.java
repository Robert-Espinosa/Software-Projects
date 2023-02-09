import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    /**
     * Test that the no argument constructors are equal.
     *
     */
    @Test
    public void consuctorNoArgumentTest1() {
        Map<String, String> testValue = this.constructorTest();
        Map<String, String> expectedValue = this.constructorRef();

        assertEquals(expectedValue, testValue);

    }

    /**
     * Test that normal add case work by creating it from ref and checking the
     * method.
     *
     */
    @Test
    public void addTest() {
        Map<String, String> testValue = this.constructorTest();
        Map<String, String> expectedValue = this.createFromArgsRef("Robbie",
                "Espinosa");

        testValue.add("Robbie", "Espinosa");

        assertEquals(expectedValue, testValue);

    }

    /**
     * Test that normal add case work by creating it from ref and checking the
     * method.
     *
     */
    @Test
    public void addTest2() {
        Map<String, String> testValue = this.constructorTest();
        Map<String, String> expectedValue = this.createFromArgsRef("Robbie",
                "Espinosa", "hi", "hi", "check", "check");

        testValue.add("Robbie", "Espinosa");
        testValue.add("hi", "hi");
        testValue.add("check", "check");

        assertEquals(expectedValue, testValue);

    }

    /**
     * Test that normal remove case work by creating it from ref and checking
     * the method.
     **/
    @Test
    public void testRemove() {
        Map<String, String> testValue = this.createFromArgsTest("alex",
                "arnone", "robbie", "espinosa");
        Map<String, String> expected = this.createFromArgsRef("robbie",
                "espinosa");
        testValue.remove("alex");

        assertEquals(expected, testValue);
    }

    /**
     * Test that normal remove case work by creating it from ref and checking
     * the method.
     **/
    @Test
    public void testRemove2() {
        Map<String, String> testValue = this.createFromArgsTest("alex",
                "arnone", "robbie", "espinosa");
        Map<String, String> expected = this.createFromArgsRef("alex", "arnone");
        testValue.remove("robbie");

        assertEquals(expected, testValue);
    }

    /**
     * First check that the string returned for value is correct. Then check
     * that both maps have same values.
     *
     **/
    @Test
    public void testValue() {
        Map<String, String> testValue = this.createFromArgsTest("alex",
                "arnone", "robbie", "espinosa");
        Map<String, String> expected = this.createFromArgsRef("alex", "arnone",
                "robbie", "espinosa");

        String testString = testValue.value("alex");

        assertEquals("arnone", testString);
        assertEquals(expected, testValue);
    }

    /**
     * First check that the string returned for value is correct. Then check
     * that both maps have same values.
     *
     **/
    @Test
    public void testValue2() {
        Map<String, String> testValue = this.createFromArgsTest("alex",
                "arnone", "robbie", "espinosa", "jace", "perez", "sam",
                "cohen");
        Map<String, String> expected = this.createFromArgsRef("alex", "arnone",
                "robbie", "espinosa", "jace", "perez", "sam", "cohen");

        String testString = testValue.value("robbie");

        assertEquals("espinosa", testString);
        assertEquals(expected, testValue);
    }

    /**
     * First check that the string returned for value is boolean and is correct.
     * then check both maps to make sure nothing changed.
     *
     */
    @Test
    public void hasKeyTest() {
        Map<String, String> testValue = this.constructorTest();
        boolean expected = true;
        Map<String, String> correct = this.createFromArgsRef("Robbie",
                "Espinosa");

        boolean value;
        testValue.add("Robbie", "Espinosa");
        value = testValue.hasKey("Robbie");

        assertEquals(expected, value);
        assertEquals(correct, testValue);

    }

    /**
     * First check that the string returned for value is boolean and is correct.
     * then check both maps to make sure nothing changed.
     *
     */
    @Test
    public void hasKeyTest2() {
        Map<String, String> testValue = this.constructorTest();
        boolean expected = true;
        Map<String, String> correct = this.createFromArgsRef("Robbie",
                "Espinosa", "alex", "arnone", "seven", "darpel", "bot",
                "midba");
        boolean value;
        testValue.add("Robbie", "Espinosa");
        testValue.add("alex", "arnone");
        testValue.add("seven", "darpel");
        testValue.add("bot", "midba");

        value = testValue.hasKey("bot");

        assertEquals(expected, value);
        assertEquals(correct, testValue);

    }

    /**
     * First check that the string returned for value is boolean and is correct.
     * then check both maps to make sure nothing changed.
     *
     */
    @Test
    public void hasKeyTest3() {
        Map<String, String> testValue = this.constructorTest();
        boolean expected = false;
        Map<String, String> correct = this.createFromArgsRef("Robbie",
                "Espinosa", "alex", "arnone", "seven", "darpel", "bot",
                "midba");
        boolean value;
        testValue.add("Robbie", "Espinosa");
        testValue.add("alex", "arnone");
        testValue.add("seven", "darpel");
        testValue.add("bot", "midba");
        value = testValue.hasKey("henery");

        assertEquals(expected, value);
        assertEquals(correct, testValue);

    }

    /**
     * check that has key works on empty map. Then makes sure that the map is
     * still empty.
     *
     */
    @Test
    public void hasKeyTest4() {
        Map<String, String> testValue = this.constructorTest();
        boolean expected = false;
        Map<String, String> correct = this.constructorRef();

        boolean val = testValue.hasKey("Robbie");

        assertEquals(expected, val);
        assertEquals(correct, testValue);

    }

    /**
     * Checks that the size of the map method works and compares it to known
     * size integer.
     *
     */
    @Test
    public void sizeTest1() {
        Map<String, String> testValue = this.constructorTest();
        int size = 4;
        Map<String, String> expected = this.createFromArgsRef("Robbie",
                "Espinosa", "alex", "arnone", "seven", "darpel", "bot",
                "midba");

        testValue.add("Robbie", "Espinosa");
        testValue.add("alex", "arnone");
        testValue.add("seven", "darpel");
        testValue.add("bot", "midba");

        assertEquals(size, testValue.size());
        assertEquals(expected, testValue);

    }

    /**
     * Checks that the size of the map method works and compares it to known
     * size integer of zero.
     */
    @Test
    public void sizeTest2() {
        Map<String, String> testValue = this.constructorTest();
        int size = 0;
        Map<String, String> expected = this.constructorRef();

        assertEquals(size, testValue.size());
        assertEquals(expected, testValue);

    }

    /**
     * Checks that the size of the map method works and compares it to known
     * size integer of 3 with also removing some to see if it works.
     */
    @Test
    public void sizeTest3() {
        Map<String, String> testValue = this.constructorTest();
        int size = 3;
        testValue.add("Robbie", "Espinosa");
        testValue.add("alex", "arnone");
        testValue.add("seven", "darpel");
        testValue.add("bot", "midba");
        testValue.add("software 1", "OHIO STATE");
        testValue.add("duck", "goose");
        testValue.removeAny();
        testValue.removeAny();
        testValue.removeAny();

        assertEquals(size, testValue.size());

    }

    /**
     * Checks that the size of the map method works if everything in map gets
     * removed.
     */
    @Test
    public void sizeTest4() {
        Map<String, String> testValue = this.constructorTest();
        int size = 0;
        testValue.add("Robbie", "Espinosa");
        testValue.add("alex", "arnone");
        testValue.add("seven", "darpel");
        testValue.add("bot", "midba");
        testValue.add("software 1", "OHIO STATE");
        testValue.add("duck", "goose");
        testValue.removeAny();
        testValue.removeAny();
        testValue.removeAny();
        testValue.removeAny();
        testValue.removeAny();
        testValue.removeAny();

        assertEquals(size, testValue.size());

    }
}
