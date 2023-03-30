import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 *
 * Program asks for a user HTMl file input of words definitions, sentences, and
 *
 * then outputs a espied with all of the words ordered with their definitions.
 *
 *
 *
 * @author Khalil Shakhpandarov
 *
 *
 *
 */
public final class GloassaryProject {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private GloassaryProject() {
    }

    /**
     *
     */
    public static class StringLT implements Comparator<String> {

        @Override

        public int compare(String o1, String o2) {

            return o1.compareTo(o2);
        }
    }

    /**
     *
     * @param input
     *            takes an input into order to read in html file and store
     *            defintions and terms correctly.
     * @param glossary
     *            is given the glossary in order to store sprecific values from
     *            the file.
     */
    public static void termsHTML(SimpleReader input,
            Map<String, String> glossary) {

        String word = "";
        String definition = "";
        String line = "";
        while (!input.atEOS()) {
            word = input.nextLine();
            line = input.nextLine();
            while (!line.isBlank()) {
                definition += line;
                line = input.nextLine();
            }
            glossary.add(word, definition);
            definition = "";
        }

    }

    /**
     * sorts items alphabetically.
     *
     * @param glossary
     *            take in glossary in order to sort throgh terms and make them
     *            alphabetical.
     * @param queue
     *            best way to flip a glossarry is to use a queue.
     */
    private static void sortInAlphabetical(Map<String, String> glossary,
            Queue<String> queue) {
        Comparator<String> sorter = new StringLT();

        for (Map.Pair<String, String> sort : glossary) {
            queue.enqueue(sort.key());
        }
        queue.sort(sorter);

    }

    /**
     *
     * @param glossary
     *            takes in glossary in order to print connections.
     * @param word
     *            takes in word as way to print when words take you to other
     *            pages.
     * @param outFile
     *            a way for the method to know wher to print.
     */
    public static void definitionList(Map<String, String> glossary, String word,
            String outFile) {

        char quotations = '"';
        String description = glossary.value(word);
        SimpleWriter output = new SimpleWriter1L(word + ".html");
        int position = 0;
        final String separatorStr = " \t, ";
        Set<Character> separatorSet = new Set1L<>();
        int length = separatorStr.length();

        for (int i = 0; i < length; i++) {
            char s = separatorStr.charAt(i);
            if (!separatorSet.contains(separatorStr.charAt(i))) {
                separatorSet.add(s);
            }
        }

        output.println("<html>");
        output.println("<head>");
        output.println("<title>" + word + "</title>");
        output.println("</head>");
        output.println("<body>");
        output.println("<h2><b><i><font color=" + quotations + "red"
                + quotations + ">" + word + "</font></i></b></h2>");
        output.print("<blockquote>");

        while (position < description.length()) {
            String text = nextWordOrSeparator(description, separatorSet,
                    position);

            if (glossary.hasKey(text)) {
                output.print("<a href =" + quotations + text + ".html"
                        + quotations + ">" + text + "</a>");
            } else {
                output.print(text);
            }
            position += text.length();
        }
        output.print("</blockquote>");
        output.println("<hr />");
        output.print("<p>Return to <a href=" + quotations + outFile + quotations
                + ">index</a>.</p>");
        output.println("</body>");
        output.println("</html>");
        output.close();

    }

    /**
     *
     * @param description
     *            string of the text we are looking through.
     * @param separatorSet
     *            all possible seperators it can find.
     * @param position
     *            positon in the description
     * @return string word varible or string that contains a seperator.
     */
    public static String nextWordOrSeparator(String description,
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
     * prints the begining of an html file to the given otuput.
     *
     * @param output
     */
    public static void printHeader(SimpleWriter output) {

        output.println("<html>");
        output.println("<head>");
        output.println("<title>Glossary</title>");
        output.println("</head>");
        output.println("<body>");
        output.println("<h2>Glossary</h2>");
        output.println("<hr />");
        output.println("<h3>Index</h3>");
        output.println("<ul>");

    }

    /**
     * prints the footer of any given html document.
     *
     * @param output
     */
    public static void printFooter(SimpleWriter output) {

        output.println("</ul>");
        output.println("</body>");
        output.println("</html>");

    }

    /**
     * runs program by calling methods in order to create our Dictionary!
     *
     * @param args
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
        Map<String, String> glossary = new Map1L<>();

        termsHTML(input, glossary);

        Queue<String> queue = new Queue1L<>();

        sortInAlphabetical(glossary, queue);
        printHeader(output);
        char quote = '"';
        while (queue.length() != 0) {

            String word = queue.dequeue();
            definitionList(glossary, word, outFile);

            output.print("<li><a href=" + quote + word + ".html" + quote + ">"
                    + word + "</a></li>");

        }
        printFooter(output);
        input.close();
        output.close();
        in.close();
        out.close();
    }
}
