import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class GLossary2Test {

    /**
     * tests normal case for next word or seperator.
     */
    @Test
    public void NextWordOrSeperator() {
        String s = "hello, Word!";
        Set<Character> seperators = new Set1L<>();
        seperators.add(',');
        seperators.add('.');
        seperators.add('?');
        seperators.add('!');
        seperators.add(' ');

        String correct = GloassaryProject.nextWordOrSeparator(s, seperators, 0);

        String e = "hello";

        assert e.equals(correct);
    }

    /**
     * checks to see if index of next word works correctly.
     */
    @Test
    public void NextWordOrSeperator2() {
        SimpleWriter out = new SimpleWriter1L();
        String s = "hello, Word!";
        Set<Character> seperators = new Set1L<>();
        seperators.add(',');
        seperators.add('.');
        seperators.add('?');
        seperators.add('!');
        seperators.add(' ');

        String correct = GloassaryProject.nextWordOrSeparator(s, seperators, 7);

        String e = "Word";
        assert e.equals(correct);
    }

    /**
     * test to see if it also works for puncuation or in this case seperator.
     */
    @Test
    public void NextWordOrSeperator3() {
        SimpleWriter out = new SimpleWriter1L();

        String s = "hello, Word!";
        Set<Character> seperators = new Set1L<>();
        seperators.add(',');
        seperators.add('.');
        seperators.add('?');
        seperators.add('!');
        seperators.add(' ');

        String correct = GloassaryProject.nextWordOrSeparator(s, seperators, 5);

        String e = ", ";
        assert e.equals(correct);
    }

}
