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
 * @author Robbie
 *
 *
 *
 */

public final class GlossaryProject {

    private static class StringLT implements Comparator<String> {

    @Override

            public int compare(String o1, String o2) {

                return o1.compareTo(o2);

        /**
         *
         * private static XMLTree linkTree;
         *
         *
         * 
         * /** Private constructor so this utility class cannot be instantiated.
         * 
         */

        private StringLT() {

        }

        private static void termsHTML(SimpleReader input,

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

        private static void sortInAlphabetical(Map<String, String> glossary,

                Queue<String> queue) {

            Comparator<String> sorter = new StringLT();

            for (Map.Pair<String, String> sort : glossary) {

                queue.enqueue(sort.key());

                queue.sort(sorter);

            }

        }

        private static void definitionList(Map<String, String> glossary,

                String word, String outFile) {

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

                    output.println("<html>");

                    output.println("<head>");

                    output.println("<title>" + word + "</title>");

                    output.println("</head>");

                    output.println("<body>");

                    output.println("<h2><b><i><font color=" + quotations + "red"

                            + quotations + ">" + word + "</font></i></b></h2>");

                    output.print("<blockquote>");

                    while (position < description.length()) {

                        String text = this.nextWordOrSeparator(description,

                                separatorSet, position);

                        if (glossary.hasKey(text)) {

                            output.print(

                                    "<a href =" + quotations + text + ".html"

                                            + quotations + ">" + text + "</a>");

                        } else {

                            output.print(text);

                        }

                        position += text.length();

                    }

                    output.print("</blockquote>");

                    output.println("<hr />");

                    output.print("<p>Return to <a href=" + quotations + outFile

                            + quotations + ">index</a>.</p>");

                    output.println("</body>");

                    output.println("</html>");

                    output.close();

                }

            }

        }

        private static String nextWordOrSeparator(String

                    description, Set<Character> separatorSet, int position) {

            String word = "";

            boolean check = true;



            if

            (separatorSet.contains(description.charAt(position)))

            {

                        for (int i = position; i <

            description.length() && check; i++) {

            if

            (separatorSet.contains(description.charAt(i))) {

                                word += description.charAt(i);

            } else {

                                check = false;





         else {

            for (int i = position; i <

                    description.length() && check; i++) {

                         if (!

                            separatorSet.contains(description.charAt(i))) {

                             word += description.charAt(i);

                         } else {

                             check = false;

                      }

               }





          }

             return word;



    }

        private static void printHeader(SimpleWriter output) {

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

        private static void printFooter(SimpleWriter output) {

            output.println("</ul>");

            output.println("</body>");

            output.println("</html>");

        }

public static void main(String[] args) {

            SimpleReader in = new SimpleReader1L();

            SimpleWriter out = new SimpleWriter1L();





            out.print("Enter an input file: ");

            String inFile = in.nextLine();

            SimpleReader input = new

    SimpleReader1L(inFile);

            out.print("Enter an output file with .html: ");



    SimpleWriter1L(outFile);

    String outFile = in.nextLine();

    SimpleWriter output = new

    Map<String, String> glossary = new Map1L<>(); this.termsHTML(input, glossary);

    Queue<String> queue = new Queue1L<>(); alphabeticalSorter(glossary, queue);



    this.printHeader(output); char quote = '"';

    while (queue.length() != 0) {

String word = queue.dequeue(); definitionPage(glossary, word, outFile);

output.print("<li><a href=" + quote + word

+ ".html" + quote + ">"

                + word + "</a></li>");







               this.printFooter(output);

               input.close();

                output.close();

                in.close();

                out.close();



    }

}