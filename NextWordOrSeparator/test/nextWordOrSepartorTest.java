import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

public class nextWordOrSepartorTest {

    @Test
    public void test() {
        String word = "abcdefg";

        Set<Character> t1 = new Set1L<>();
        Set<Character> t2 = new Set1L<>();

        t2.add('a');
        t2.add('b');
        t2.add('c');
        t2.add('d');
        t2.add('e');
        t2.add('f');
        t2.add('g');
        NextWordOrSeparatorTest.generateElements(word, t1);
        assertEquals(t1, t2);

    }

    @Test
    public void test2() {
        String word = "qdas@";

        Set<Character> t1 = new Set1L<>();
        Set<Character> t2 = new Set1L<>();

        t2.add('q');
        t2.add('d');
        t2.add('a');
        t2.add('s');
        t2.add('@');

        NextWordOrSeparatorTest.generateElements(word, t1);
        assertEquals(t1, t2);

    }

    @Test
    public void test3() {
        String word = "1234";

        Set<Character> t1 = new Set1L<>();
        Set<Character> t2 = new Set1L<>();

        t2.add('1');
        t2.add('2');
        t2.add('3');
        t2.add('4');

        NextWordOrSeparatorTest.generateElements(word, t1);
        assertEquals(t1, t2);

    }

    @Test
    public void test4() {
        String word = "Robbie,Espinosa now";

        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        NextWordOrSeparatorTest.generateElements(separatorStr, separatorSet);

        String answer = NextWordOrSeparatorTest.nextWordOrSeparator(word, 0,
                separatorSet);

        assertEquals(answer, "Robbie");

    }

    @Test
    public void test5() {
        String word = "Robbie,Espinosa now";

        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        NextWordOrSeparatorTest.generateElements(separatorStr, separatorSet);

        String answer = NextWordOrSeparatorTest.nextWordOrSeparator(word, 6,
                separatorSet);

        assertEquals(answer, ",");

    }

    @Test
    public void test6() {
        String word = "Robbie,Espinosa now";

        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        NextWordOrSeparatorTest.generateElements(separatorStr, separatorSet);

        String answer = NextWordOrSeparatorTest.nextWordOrSeparator(word, 7,
                separatorSet);

        assertEquals(answer, "Espinosa");

    }

    @Test
    public void test7() {
        String word = "Robbie,Espinosa now";

        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        NextWordOrSeparatorTest.generateElements(separatorStr, separatorSet);

        String answer = NextWordOrSeparatorTest.nextWordOrSeparator(word, 16,
                separatorSet);

        assertEquals(answer, "now");

    }
}
