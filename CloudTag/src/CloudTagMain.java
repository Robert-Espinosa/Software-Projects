import components.map.Map;
import components.map.Map1L;
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
