import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Robbie Espinosa
 *
 */
public final class WordCloud {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordCloud() {
    }

    /**
     * compares both strings in lowercase to check the alphabet.
     *
     */
    private static class StringLT
            implements Comparator<Map.Pair<String, Integer>> {

        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o1.key().toLowerCase().compareTo(o2.key().toLowerCase());
        }
    }

    /**
     * compares two map pairs to compare them in terms of occurrences.
     *
     */
    private static class MapPairLT
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            if (o1.value() > o2.value()) {
                return -1;
            } else if (o1.value() < o2.value()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * this methods first outputs the tags and setup required to begin the HTML.
     *
     * @param output
     *            the file to output
     * @param words
     * @param fileName
     */
    private static void printHeader(SimpleWriter output, int words,
            String fileName) {

        char quote = '"';
        output.println("<html>");
        output.println("<head>");
        output.println(
                "<title> top " + words + " word in " + fileName + "</title>");
        output.println(
                "<link href=" + quote + "http://web.cse.ohio-state.edu/software"
                        + "/2231/web-sw2/assignments/projects/tag"
                        + "-cloud-generator/data/tagcloud.css" + quote + "rel="
                        + quote + "stylesheet" + quote + "type=" + quote
                        + "text/css" + quote + ">");
        output.println("<link href=" + quote + "tagcloud.css" + quote + "rel="
                + quote + "stylesheet" + quote + "type=" + quote + "text/css"
                + quote + ">");
        output.println("</head>");

        output.println("<body>");
        output.println(
                "<h2>" + "top " + words + " word in" + fileName + "</h2>");
        output.println("<hr>");
        output.println("<div class=" + quote + "cdiv" + quote + ">");
        output.println("<p class=" + quote + "cbox" + quote + ">");

    }

    /**
     * The method reads an input as a string and stores terms and counts into a
     * map.
     *
     * @param wordMap
     *            location where words and counts are stored
     * @param input
     *            input file containing words
     * @updates wordMap
     * @requires input.is_open and input.content contains a .txt file with words
     *           on the first line followed by its definition on the next line
     *           and a space separating each word and definition from the next.
     * @ensures wordMap = [wordMap(word, count)]
     *
     */
    private static void processFile(SimpleReader input,
            Map<String, Integer> wordMap) {
        Set<Character> separatorSet = new Set1L<>();
        final String seperators = " \t\\n\\r,-.!?[]';:/()";
        for (int i = 0; i < seperators.length(); i++) {
            char s = seperators.charAt(i);
            if (!separatorSet.contains(seperators.charAt(i))) {
                separatorSet.add(s);
            }
        }

        String word = "";
        String line = "";

        while (!input.atEOS()) {
            line = input.nextLine();
            int position = 0;

            while (position < line.length()) {

                word = nextWordOrSeparator(line, separatorSet, position);
                position += word.length();
                if (!separatorSet.contains(word.charAt(0))) {
                    if (wordMap.hasKey(word.toLowerCase())) {
                        Pair<String, Integer> p = wordMap.remove(word);
                        int val = p.value();
                        val++;
                        wordMap.add(word.toLowerCase(), val);
                    } else {
                        wordMap.add(word.toLowerCase(), 1);

                    }
                }

            }

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
     *
     * @param m
     *            takes in a map of all words in our file
     * @param topAmount
     *            take in the largest value to sort up until
     * @return a map with the top N words that have the most occurrences
     */
    private static Map<String, Integer> sortCount(Map<String, Integer> m,
            int topAmount) {

        Comparator<Map.Pair<String, Integer>> is = new MapPairLT();
        SortingMachine<Map.Pair<String, Integer>> intSort = new SortingMachine1L<>(
                is);

        for (Map.Pair<String, Integer> sort : m) {
            intSort.add(sort);
        }

        Map<String, Integer> topMap = m.newInstance();
        intSort.changeToExtractionMode();

        while (topMap.size() < topAmount && intSort.size() > 0) {
            Map.Pair<String, Integer> sorted = intSort.removeFirst();
            String name = sorted.key();
            int value = sorted.value();
            topMap.add(name, value);
        }

        return topMap;

    }

    /**
     * @param m
     *            takes in the map with the most occuring words
     * @param output
     *            output file to write to in html
     * @param lowest
     *            the least occuring word so we can calculate font sizing
     */
    private static void outputWords(Map<String, Integer> m, SimpleWriter output,
            int lowest) {
        SimpleWriter out = new SimpleWriter1L();

        final int fontNumber = 37;
        Comparator<Map.Pair<String, Integer>> ss = new StringLT();
        SortingMachine<Map.Pair<String, Integer>> stringSort = new SortingMachine1L<>(
                ss);

        for (Map.Pair<String, Integer> sort : m) {
            stringSort.add(sort);
        }

        stringSort.changeToExtractionMode();
        while (stringSort.size() > 0) {

            Map.Pair<String, Integer> sorted = stringSort.removeFirst();
            String name = sorted.key();
            int value = sorted.value();

            double font = (double) value - (double) lowest;

            double FontPercent = font / fontNumber;

            final int finalFontDistribution = (int) ((FontPercent * fontNumber)
                    + 11);

            output.println("<span style=\"cursor:default\" class= \"f"
                    + finalFontDistribution + "\"" + " title=\"count: " + value
                    + "\">" + name + "</span>");

        }

    }

    /**
     *
     * @param m
     *            take in the map and returns the least occuring word ammount
     * @return the ammount of times the least occuring word occurs
     */
    private static int getMin(Map<String, Integer> m) {
        Map.Pair<String, Integer> start = m.removeAny();
        int min = start.value();
        m.add(start.key(), start.value());

        for (Map.Pair<String, Integer> sort : m) {

            Map.Pair<String, Integer> sorted = sort;
            if (min > sort.value()) {
                min = sort.value();
            }

        }
        return min;
    }

    /**
     *
     * @param output
     *            write to file
     */
    private static void outputFoot(SimpleWriter output) {

        output.println("</p>");
        output.println("</div>");
        output.println("</body>");
        output.println("</html>");

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

        int topAmount = in.nextInteger();
        printHeader(output, topAmount, inFile);

        Map<String, Integer> map = new Map1L<>();
        processFile(input, map);

        Map<String, Integer> countSorted = sortCount(map, topAmount);

        int min = getMin(countSorted);
        outputWords(countSorted, output, min);

        outputFoot(output);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
        input.close();
        output.close();
    }

}
