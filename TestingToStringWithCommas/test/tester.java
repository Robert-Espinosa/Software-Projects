import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Sample JUnit test fixture for FactoringUtility.
 *
 * @author Paolo Bucci
 *
 */
public final class tester {

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas5.toStringWithCommas(n);
    }

    /**
     * Test aFactor with input 0. To test edge case
     */
    @Test
    public void toStringWithCommas() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2();
        String s = redirectToMethodUnderTest(n);
        String expected = "0";

        assertEquals(expected, s);
    }

    /**
     * Test aFactor with input 1. To test edge case with low number
     */
    @Test
    public void toStringWithCommas1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(123234221);
        String s = redirectToMethodUnderTest(n);
        String expected = "123,234,221";

        assertEquals(expected, s);
    }

    /**
     * Test aFactor with input 18. to test rutine case
     */
    @Test
    public void toStringWithCommas2() {
        /*
         * the value 18
         */
        NaturalNumber n = new NaturalNumber2(18);
        String s = redirectToMethodUnderTest(n);
        String expected = "18";

        assertEquals(expected, s);
    }

    /**
     * Test aFactor with input 18. to test rutine case
     */
    @Test
    public void toStringWithCommas3() {
        /*
         * large number
         */
        NaturalNumber n = new NaturalNumber2("900000102121232");
        String s = redirectToMethodUnderTest(n);
        String expected = "900,000,102,121,232";

        assertEquals(expected, s);
    }

    /**
     * Test aFactor with input 18. to test rutine case
     */
    @Test
    public void toStringWithCommas4() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2("2000");
        String s = redirectToMethodUnderTest(n);
        String expected = "2,000";

        assertEquals(expected, s);
    }

    /**
     * Test aFactor with input 18. to test rutine case
     */
    @Test
    public void toStringWithCommas5() {
        /*
         * test five any big numver with mutiple zeros infront breaks
         */
        NaturalNumber n = new NaturalNumber2(123123);
        NaturalNumber z = new NaturalNumber2();
        z.copyFrom(n);
        String s = redirectToMethodUnderTest(n);
        String expected = "123,123";

        assertEquals(n, z);

        assertEquals(expected, s);
    }
}
