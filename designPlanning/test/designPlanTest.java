import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Sample JUnit test fixture for FactoringUtility.
 *
 * @author Paolo Bucci
 *
 */
public final class designPlanTest {

    /**
     * Test aFactor with input 0. To test edge case
     */
    @Test
    public void toStringWithCommas() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2();
        String s = deignPlan.toStringWithCommas(n);
        String expected = "0";
        SimpleWriter out = new SimpleWriter1L();

        assertEquals(expected, s);
        out.close();
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
        String s = deignPlan.toStringWithCommas(n);
        String expected = "123,234,221";
        SimpleWriter out = new SimpleWriter1L();

        assertEquals(expected, s);
        out.close();
    }

    /**
     * Test aFactor with input 18. to test rutine case
     */
    @Test
    public void toStringWithCommas2() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(18);
        String s = deignPlan.toStringWithCommas(n);
        String expected = "18";
        SimpleWriter out = new SimpleWriter1L();

        assertEquals(expected, s);
        out.close();
    }

    /**
     * Test aFactor with input 13212. to test abnormally large case
     */
    @Test
    public void toStringWithCommas3() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(13212);
        String s = deignPlan.toStringWithCommas(n);
        /*
         * print result of test case
         */
        SimpleWriter out = new SimpleWriter1L();

        out.print("Result: " + s);
        out.close();
    }

}