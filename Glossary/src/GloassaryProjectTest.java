import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class GloassaryProjectTest {

    /**
     * checks to see if next word works for begining of text.
     */
    @Test
    public void nextWord() {
        String s = "happy birthday to you";

        String check = GloassaryProject.nextWord(s, 0);

        String word = "happy ";

        assert word.equals(check);

    }

    /**
     * checks to see if middle case worked for next word.
     */
    @Test
    public void nextWord2() {
        SimpleWriter si = new SimpleWriter1L();
        String s = "I am tired";

        String check = GloassaryProject.nextWord(s, 2);

        String word = "am ";

        assert word.equals(check);

    }

    /**
     * checks to see if another space works.
     */
    @Test
    public void nextWord3() {
        String s = "I am tired";

        String check = GloassaryProject.nextWord(s, 1);

        String word = " ";

        assert word.equals(check);

    }

    /**
     * Test to see if the return value of contains word is correct.
     *
     */
    @Test
    public void containsWord() {
        SimpleWriter si = new SimpleWriter1L();

        Sequence<String> list = new Sequence1L<>();
        list.add(0, "hi");
        list.add(1, "bye");

        String s = "bye";

        String ret = GloassaryProject.containsWord(list, s);

        assert ret.equals(s);

    }

    /**
     * test to see if return works when no word is found.
     */
    @Test
    public void containsWord2() {
        SimpleWriter si = new SimpleWriter1L();

        Sequence<String> list = new Sequence1L<>();
        list.add(0, "hi");
        list.add(1, "bye");

        String s = "hello";

        String ret = GloassaryProject.containsWord(list, s);

        assert ret.equals("");

    }

}
