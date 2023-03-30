import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    /**
     * Test that the no argument constructors are equal.
     *
     */
    @Test
    public void consuctorNoArgumentTest1() {
        NaturalNumber testValue = this.constructorTest();
        NaturalNumber expectedValue = this.constructorRef();

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that the routine case works for creating natural number with
     * integer.
     *
     */
    @Test
    public void consuctorIntTest1() {
        final int x = 6;
        NaturalNumber testValue = this.constructorTest(x);
        NaturalNumber expectedValue = this.constructorRef(x);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that the routine case works for creating large value.
     *
     */
    @Test
    public void consuctorIntTest2() {
        final int x = 1345432134;
        NaturalNumber testValue = this.constructorTest(x);
        NaturalNumber expectedValue = this.constructorRef(x);

        assertEquals(testValue, expectedValue);
    }

    /**
     * Test that edge for zero.
     *
     */
    @Test
    public void consuctorIntTest3() {
        final int x = 0;
        NaturalNumber testValue = this.constructorTest(x);
        NaturalNumber expectedValue = this.constructorRef(x);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that edge for zero as string.
     *
     */
    @Test
    public void consuctorStringTest1() {
        String num = "0";
        NaturalNumber testValue = this.constructorTest(num);
        NaturalNumber expectedValue = this.constructorRef(num);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that edge for large number as string.
     *
     */
    @Test
    public void consuctorStringTest2() {
        String num = "1234892342";
        NaturalNumber testValue = this.constructorTest(num);
        NaturalNumber expectedValue = this.constructorRef(num);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that edge for routine case number as string.
     *
     */
    @Test
    public void consuctorStringTest3() {
        String num = "4332";
        NaturalNumber testValue = this.constructorTest(num);
        NaturalNumber expectedValue = this.constructorRef(num);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that edge for naturalNumber case number as string.
     *
     */
    @Test
    public void consuctorNNTest1() {
        NaturalNumber n = new NaturalNumber2();
        NaturalNumber testValue = this.constructorTest(n);
        NaturalNumber expectedValue = this.constructorRef(n);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that edge for naturalNumber case number as string.
     *
     */
    @Test
    public void consuctorNNTest2() {
        final int i = 324354212;
        NaturalNumber n = new NaturalNumber2(i);
        NaturalNumber testValue = this.constructorTest(n);
        NaturalNumber expectedValue = this.constructorRef(n);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that routine case for naturalNumber case.
     *
     */
    @Test
    public void consuctorNNTest3() {
        final int i = 109;
        NaturalNumber n = new NaturalNumber2(i);
        NaturalNumber testValue = this.constructorTest(n);
        NaturalNumber expectedValue = this.constructorRef(n);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that routine case for mutiplyBy10.
     *
     */
    @Test
    public void mutiplyBy10Test1() {
        final int ten = 10;
        NaturalNumber testValue = this.constructorTest(ten);
        NaturalNumber expectedValue = this.constructorRef(100);

        testValue.multiplyBy10(0);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that routine case for mutiplyBy10 but also implement adding part.
     *
     */
    @Test
    public void mutiplyBy10Test2() {
        final int thirteen = 13;
        final int eight = 8;
        NaturalNumber testValue = this.constructorTest(thirteen);
        NaturalNumber expectedValue = this.constructorRef(138);

        testValue.multiplyBy10(eight);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that small case for mutiplyBy10.
     *
     */
    @Test
    public void mutiplyBy10Test3() {
        NaturalNumber testValue = this.constructorTest(0);
        NaturalNumber expectedValue = this.constructorRef(1);

        testValue.multiplyBy10(1);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that large case for mutiplyBy10.
     *
     */
    @Test
    public void mutiplyBy10Test4() {
        final int large = 123432;
        final int eight = 8;
        NaturalNumber testValue = this.constructorTest(large);
        NaturalNumber expectedValue = this.constructorRef(1234328);

        testValue.multiplyBy10(eight);

        assertEquals(testValue, expectedValue);

    }

    /**
     * Test that small case for divideBy10.
     *
     */
    @Test
    public void divideBy10Test1() {
        final int large = 100;
        NaturalNumber testValue = this.constructorTest(large);
        NaturalNumber expectedValue = this.constructorRef(10);

        int returnVal = testValue.divideBy10();

        assertEquals(testValue, expectedValue);
        assertEquals(returnVal, 0);

    }

    /**
     * Test that length of one for case divideBy10.
     *
     */
    @Test
    public void divideBy10Test2() {
        final int large = 5;
        NaturalNumber testValue = this.constructorTest(large);
        NaturalNumber expectedValue = this.constructorRef(0);

        int returnVal = testValue.divideBy10();

        assertEquals(testValue, expectedValue);
        assertEquals(returnVal, 5);

    }

    /**
     * Test that large of one for case divideBy10.
     *
     */
    @Test
    public void divideBy10Test3() {
        final int large = 50523454;
        NaturalNumber testValue = this.constructorTest(large);
        NaturalNumber expectedValue = this.constructorRef(5052345);

        int returnVal = testValue.divideBy10();

        assertEquals(testValue, expectedValue);
        assertEquals(returnVal, 4);

    }

    /**
     * Test that iszero Works.
     *
     */
    @Test
    public void isZeroTest1() {

        NaturalNumber testValue = this.constructorTest();

        boolean x = testValue.isZero();

        assertEquals(x, true);

    }

    /**
     * Test that iszero Works for false.
     *
     */
    @Test
    public void isZeroTest2() {

        NaturalNumber testValue = this.constructorTest(9);

        boolean x = testValue.isZero();

        assertEquals(x, false);

    }

}
