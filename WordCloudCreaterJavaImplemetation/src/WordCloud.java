import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import components.set.Set;
import components.set.Set1L;

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
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
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
    private static void printHeader(PrintWriter output, int words,
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
    private static void processFile(BufferedReader input,
            Map<String, Integer> wordMap) {
        Set<Character> separatorSet = new Set1L<>();
        final String seperators = " `\n,-.!?[]';:/()\"*";
        for (int i = 0; i < seperators.length(); i++) {
            char s = seperators.charAt(i);
            if (!separatorSet.contains(seperators.charAt(i))) {
                separatorSet.add(s);
            }
        }

        String word = "";

        String line;
        try {
            line = input.readLine();

            while (line != null) {
//                  System.out.print("Stuck in loop line equals: " + line);
                int pos = 0;
                while (pos < line.length()) {
                    word = nextWordOrSeparator(line, separatorSet, pos);
                    pos += word.length();
                    if (!separatorSet.contains(word.charAt(0))) {
                        if (wordMap.containsKey(word.toLowerCase())) {
                            wordMap.replace(word.toLowerCase(),
                                    wordMap.get(word.toLowerCase()),
                                    wordMap.get(word.toLowerCase()) + 1);
                        } else {
                            wordMap.put(word.toLowerCase(), 1);
                        }
                    }
                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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

        Map<String, Integer> topMap = new HashMap<>();

        while (topMap.size() < topAmount || m.size() == 0) {
            String removedWord = getMaxName(m);
            Map.Entry<String, Integer> entry = null;
            for (Map.Entry<String, Integer> e : m.entrySet()) {
                if (e.getKey().equals(removedWord)) {
                    entry = e;
                }
            }
            if (entry != null) {
                topMap.put(entry.getKey(), entry.getValue());
            }

        }

        return topMap;

    }

    /**
     * @param m
     *            takes in the map with the most occuring words
     * @param out2
     *            output file to write to in html
     * @param lowest
     *            the least occuring word so we can calculate font sizing
     * @param highest
     *            the largest value of the words
     */
    private static void outputWords(Map<String, Integer> m, PrintWriter out2,
            int lowest, int highest) {

        List<String> names = new ArrayList<String>();

        for (Map.Entry<String, Integer> e : m.entrySet()) {
            names.add(e.getKey());
        }
        Comparator<String> comparator = new StringLT();

        names.sort(comparator);

        final int fontNumber = 37;
        int i = 0;
        while (names.size() > 0) {
            for (Map.Entry<String, Integer> e : m.entrySet()) {
                if (e.getKey().equals(names.get(i))) {
                    String name = e.getKey();
                    int value = e.getValue();

                    int font = value - lowest;

                    double percent = 1;

                    if (highest != lowest) {
                        percent = font / (double) (highest - lowest);
                    }

                    final int adjust = 11;
                    int finalFontDistribution = (int) (percent * fontNumber)
                            + adjust;

                    out2.println("<span style=\"cursor:default\" class= \"f"
                            + finalFontDistribution + "\"" + " title=\"count: "
                            + value + "\">" + name + "</span>");
                    i++;
                }

            }

        }

    }

    /**
     *
     * @param m
     *            take in the map and returns the least occuring word ammount
     * @return the ammount of times the least occuring word occurs
     */
    private static int getMin(Map<String, Integer> m) {
        int min = 0;

        for (Map.Entry<String, Integer> e : m.entrySet()) {
            int value = e.getValue();
            if (value < min) {
                min = value;
            }

        }
        return min;
    }

    /**
     *
     * @param m
     *            take in the map and returns the least occuring word ammount
     * @return the ammount of times the least occuring word occurs
     */
    private static int getMax(Map<String, Integer> m) {

        int max = 0;

        for (Map.Entry<String, Integer> e : m.entrySet()) {
            int value = e.getValue();
            if (value > max) {
                max = value;
            }

        }
        return max;
    }

    /**
     *
     * @param m
     *            take in the map and returns the least occuring name
     * @return the ammount of times the least occuring word occurs
     */
    private static String getMaxName(Map<String, Integer> m) {

        int max = 0;
        String name = "";

        for (Map.Entry<String, Integer> e : m.entrySet()) {
            int value = e.getValue();
            if (value > max) {
                max = value;
                name = e.getKey();
            }

        }
        return name;
    }

    /**
     *
     * @param output
     *            write to file
     */
    private static void outputFoot(PrintWriter output) {

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
     * @return
     */
    public static void main(String[] args) throws FileNotFoundException {

        System.out.print("please enter the file name: ");
        Scanner in = new Scanner(System.in);
        String inputFile = in.nextLine();

        System.out.print("please enter the output file name: ");
        String outputFile = in.nextLine();

        PrintWriter outStream;
        try {
            outStream = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputFile)));
        } catch (IOException x) {

            System.err.print("error opening printWriter");
            in.close();
            return;
        }

        BufferedReader inputStream;
        try {
            inputStream = new BufferedReader(new FileReader(inputFile));
        } catch (IOException x) {
            System.err.print("error opening input file");
            outStream.close();
            in.close();
            return;
        }

        System.out.print("how many words would you like in the cloud: ");
        int wordCount = in.nextInt();

        Map<String, Integer> map = new HashMap<String, Integer>();

        System.out.print("created hashmap");
        printHeader(outStream, wordCount, outputFile);
        System.out.print("printed header");

        processFile(inputStream, map);
        System.out.print("process header");

        try {
            inputStream.close();
        } catch (IOException x) {
            System.err.print("Error unable to close file");
        }

        Map<String, Integer> countSorted = sortCount(map, wordCount);

        int min = getMin(countSorted);
        int max = getMax(countSorted);
        outputWords(countSorted, outStream, min, max);

        outputFoot(outStream);
        /*
         * Close input and output streams
         */
        in.close();
        outStream.close();

    }

}
