import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Sample JUnit test fixture for FactoringUtility.
 *
 * @author Paolo Bucci
 *
 */
public final class FactoringUtilityTest {

    /**
     * Test aFactor with input 0.
     */
    @Test
    public void aFactorTest1() {
        /*
         * Set up variables and call method under test
         */
        int n = Integer.MAX_VALUE;
        int p = Integer.MAX_VALUE - 1;

        int answer = Integer.MAX_VALUE - 1;
        int method = number.average(n, p);
//        int factor = FactoringUtility.aFactor(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(answer, method);
    }

    /**
     * Test aFactor with input 1.
     */
    @Test
    public void aFactorTest2() {
        /*
         * Set up variables and call method under test
         */
        int n = Integer.MIN_VALUE;
        int p = Integer.MIN_VALUE + 1;

        int answer = Integer.MIN_VALUE + 1;
        int method = number.average(n, p);
//        int factor = FactoringUtility.aFactor(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(answer, method);
    }

    /**
     * Test aFactor with input 2.
     */
    @Test
    public void aFactorTest3() {

        int n = Integer.MIN_VALUE;
        int p = Integer.MIN_VALUE;

        int answer = Integer.MIN_VALUE;
        int method = number.average(n, p);
//        int factor = FactoringUtility.aFactor(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(answer, method);
    }

    /**
     * Test aFactor with input 4.
     */
    @Test
    public void aFactorTest4() {

        int n = Integer.MAX_VALUE;
        int p = Integer.MAX_VALUE;

        int answer = Integer.MAX_VALUE;
        int method = number.average(n, p);
//        int factor = FactoringUtility.aFactor(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(answer, method);
    }

    /**
     * Test aFactor with input 12.
     */
    @Test
    public void aFactorTest5() {

        int n = 5;
        int p = 8;

        int answer = 6;
        int method = number.average(n, p);
//        int factor = FactoringUtility.aFactor(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(answer, method);
    }

    /**
     * Test aNonTrivialFactorV1 with input 15.
     */
    @Test
    public void aNonTrivialFactorV1Test1() {
        int n = -5;
        int p = -8;

        int answer = -6;
        int method = number.average(n, p);
//        int factor = FactoringUtility.aFactor(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(answer, method);
    }

    /**
     * Test aNonTrivialFactorV1 with input 17.
     */
    @Test
    public void aNonTrivialFactorV1Test2() {
        int n = 11;
        int p = -4;

        int answer = 3;
        int method = number.average(n, p);
//        int factor = FactoringUtility.aFactor(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(answer, method);
    }

    /**
     * Test aNonTrivialFactorV1 with input 32.
     */
    @Test
    public void aNonTrivialFactorV1Test3() {
        int n = -3;
        int p = 2;

        int answer = 0;
        int method = number.average(n, p);
//        int factor = FactoringUtility.aFactor(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(answer, method);
    }

    /**
     * Test aNonTrivialFactorV2 with input 12.
     */
    @Test
    public void aNonTrivialFactorV2Test1() {
        int n = 3;
        int p = 5;

        int answer = 4;
        int method = number.average(n, p);
//        int factor = FactoringUtility.aFactor(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(answer, method);
    }

    /**
     * Test aNonTrivialFactorV2 with input 15.
     */
    @Test
    public void aNonTrivialFactorV2Test2() {
        int n = -3;
        int p = -5;

        int answer = -4;
        int method = number.average(n, p);
//        int factor = FactoringUtility.aFactor(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(answer, method);
    }
}