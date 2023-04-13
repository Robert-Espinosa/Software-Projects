import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CloudTagMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CloudTagMain() {
    }

    /**
     * The method reads an input as a string and stores terms and definitions
     * into a map.
     *
     * @param glossary
     *            location where words and definitions are stored
     * @param input
     *            input file containing words and definitions
     * @updates glossary
     * @requires input.is_open and input.content contains a .txt file with words
     *           on the first line followed by its definition on the next line
     *           and a space separating each word and definition from the next.
     * @ensures glossary = [glossary(word, definition)]
     *
     */
    private static void termsHTML(SimpleReader input,
            Map<String, Integer> glossary) {

        String word = "";
        int count = 0;
        String line = "";

        while (!input.atEOS()) {
            word = input.nextLine();
            line = input.nextLine();
            while (!line.isBlank()) {
                count++;
                line = input.nextLine();
            }
            glossary.add(word, count);
            count = 0;
        }

    }

    /**
     * This method combs through the given text description and returns the
     * first word or separator starting from the given position.
     *
     * @param description
     *            the description for a given word
     * @param separatorSet
     *            set full of all possible separators
     * @param position
     *            the index position in the description
     * @return word word variable which contains either a word or a separator
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String description,
            Set<Character> separatorSet, int position) {

        String word = "";
        boolean check = true;

        if (separatorSet.contains(description.charAt(position))) {
            for (int i = position; i < description.length() && check; i++) {
                if (separatorSet.contains(description.charAt(i))) {
                    word += description.charAt(i);
                } else {
                    check = false;
                }
            }
        } else {
            for (int i = position; i < description.length() && check; i++) {
                if (!separatorSet.contains(description.charAt(i))) {
                    word += description.charAt(i);
                } else {
                    check = false;
                }
            }
        }

        return word;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter an input file: ");
        String inFile = in.nextLine();
        SimpleReader input = new SimpleReader1L(inFile);

        out.print("Enter an output file with .html: ");
        String outFile = in.nextLine();
        SimpleWriter output = new SimpleWriter1L(outFile);

        out.print("Enter how many words to be included in cloud tag: ");

        Map<String, Integer> map = new Map1L<>();
        termsHTML(input, map);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
        input.close();
        output.close();
    }

}
