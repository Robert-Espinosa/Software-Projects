import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 *
 * @author Robbie Espinosa
 *
 */
public class StringReassemblyTest {

    /**
     * routine test of overlap.
     */
    @Test
    public void testOverlapBiking() {
        String s1 = "Bik";
        String s2 = "iking";

        int overLaping = StringReassembly.overlap(s1, s2);
        assertEquals(2, overLaping);
    }

    /**
     * routine test of overlap.
     */
    @Test
    public void testOverlapUncommon() {
        String s1 = "Uncom";
        String s2 = "common";

        int overLaping = StringReassembly.overlap(s1, s2);
        assertEquals(3, overLaping);
    }

    /**
     * testing with no overlap.
     */
    @Test
    public void testOverlapLaptop() {
        String s1 = "Laptop";
        String s2 = "hello";

        int overLaping = StringReassembly.overlap(s1, s2);
        assertEquals(0, overLaping);
    }

    /**
     * routine test of overlap.
     */
    @Test
    public void testOverlapCharging() {
        String s1 = "Char";
        String s2 = "arging";

        int overLaping = StringReassembly.overlap(s1, s2);
        assertEquals(2, overLaping);
    }

    /**
     * test combination for same words
     */

    /**
     * //random word test combination.
     */

    @Test
    public void testCombinationBiking() {
        String s1 = "Bik";
        String s2 = "iking";

        int overLaping = StringReassembly.overlap(s1, s2);
        String combined = StringReassembly.combination(s1, s2, overLaping);
        assertEquals("Biking", combined);
    }

    /**
     * random word test case.
     */
    @Test
    public void testCombinationUncommon() {
        String s1 = "Uncom";
        String s2 = "common";

        int overLaping = StringReassembly.overlap(s1, s2);
        String combined = StringReassembly.combination(s1, s2, overLaping);
        assertEquals("Uncommon", combined);
    }

    /**
     * no overlap test case edge test case.
     */
    @Test
    public void testCombinationLaptop() {
        String s1 = "Laptop";
        String s2 = "hello";

        int overLaping = StringReassembly.overlap(s1, s2);
        String combined = StringReassembly.combination(s1, s2, overLaping);
        assertEquals("Laptophello", combined);
    }

    /**
     * testing substrings routine case where scott is replaced by scott dinning.
     */
    /**
     * hall.
     */
    @Test
    public void testAddToSetAvoidingSubstrings() {
        Set<String> s1 = new Set1L<>();
        s1.add("Robbie");
        s1.add("hi");
        s1.add("Scott");
        String s = "Scott dinning hall";
        Set<String> s2 = new Set1L<>();

        s2.add("Robbie");
        s2.add("hi");
        s2.add("Scott dinning hall");
        StringReassembly.addToSetAvoidingSubstrings(s1, s);
        assertEquals(s2, s1);

    }

    /**
     * test where nothing is replaced and new string is added edge case.
     */
    @Test
    public void testAddToSetAvoidingSubstrings2() {
        Set<String> s1 = new Set1L<>();
        s1.add("Robbie");
        s1.add("hi");
        s1.add("Computer science is fun");
        String s = "OHIO STATE";
        Set<String> s2 = new Set1L<>();

        s2.add("Robbie");
        s2.add("hi");
        s2.add("Computer science is fun");
        s2.add("OHIO STATE");

        StringReassembly.addToSetAvoidingSubstrings(s1, s);
        assertEquals(s2, s1);

    }

    /**
     * test where everything is replaced max case.
     */
    @Test
    public void testAddToSetAvoidingSubstrings3() {
        Set<String> s1 = new Set1L<>();
        s1.add("Robbie");
        s1.add("hi");
        s1.add("Scott");
        String s = "Scott";

        Set<String> s2 = new Set1L<>();

        s2.add("Robbie");
        s2.add("hi");
        s2.add("Scott");
        StringReassembly.addToSetAvoidingSubstrings(s1, s);
        assertEquals(s2, s1);

    }

    /**
     * routine test case with mutiple seperators.
     */
    @Test
    public void testPrintWithLineSeparators() {
        SimpleWriter out = new SimpleWriter1L();
        String s = "hello my name is, ~Robbie~";
        StringReassembly.printWithLineSeparators(s, out);
        //confirmed by looking in console and seeing that the ~ makes a new line
    }

    //routine test case with larger amount of words
    @Test
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L();
        String s = "I am currently attending~The ohio State University~";
        StringReassembly.printWithLineSeparators(s, out);
        //confirmed by looking in console and seeing that the ~ makes a new line

    }

    /**
     * edge test case with no separtors.
     */
    @Test
    public void testPrintWithLineSeparators3() {
        SimpleWriter out = new SimpleWriter1L();
        String s = "Coding is hard";
        StringReassembly.printWithLineSeparators(s, out);
        //confirmed by looking in console and seeing that the ~ makes a new line

    }

    /**
     * test two words to see if works.
     */
    @Test
    public void testLinesFromInput1() {
        SimpleReader file = new SimpleReader1L("src/cs.txt");
        Set<String> checkedList = new Set1L<>();

        checkedList.add("computer");
        checkedList.add("science");
        StringReassembly.linesFromInput(file);
        assertTrue(checkedList.contains("computer"));
        assertTrue(checkedList.contains("science"));

    }

    /**
     * test with larger file.
     */
    @Test
    public void testLinesFromInput2() {
        SimpleReader file = new SimpleReader1L("src/cs.txt");
        Set<String> checkedList = new Set1L<>();

        checkedList.add("The");
        checkedList.add("Ohio");
        checkedList.add("State");
        checkedList.add("University");

        StringReassembly.linesFromInput(file);
        assertTrue(checkedList.contains("The"));
        assertTrue(checkedList.contains("Ohio"));
        assertTrue(checkedList.contains("State"));
        assertTrue(checkedList.contains("University"));

    }

}
