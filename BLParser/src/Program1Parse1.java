import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Robbie Espinosa and Alex Arnone
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        // dequeue our first token becasue we know its name INSTUCTION due to
        // assert statements
        tokens.dequeue();

        //check to make sure our istruction name isnt a primative instruction
        String name = tokens.dequeue();
        Boolean nameNotPrimative = !(name.equals("move")
                || name.equals("turnleft") || name.equals("turnright")
                || name.equals("infect"));
        Reporter.assertElseFatalError(nameNotPrimative,
                "Incorrect your identifier cant "
                        + "be equal to primative instruction");
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(name),
                "Incorrect your identifier isnt ");

        //check to make sure token is equal to IS
        String is = tokens.dequeue();
        Boolean nameIsNotIS = is.equals("IS");
        Reporter.assertElseFatalError(nameIsNotIS,
                "Incorrect Syntax for BL code require IS but was" + is);

        //parse tokens back to statement
        body.parseBlock(tokens);

        //check to make sure token is equal to END
        String end = tokens.dequeue();
        Boolean nameIsNotEND = end.equals("END");
        Reporter.assertElseFatalError(nameIsNotEND,
                "Incorrect Syntax for BL code require END but was" + end);

        //check to make sure the ending identifier name equals begining
        String nameEnd = tokens.dequeue();
        Boolean nameMatch = name.equals(nameEnd);

        Reporter.assertElseFatalError(nameMatch,
                "Incorrect your intial instruction name must match");

        // returns name of instruction
        return name;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        //check the program to make sure its named PROGRAM
        String programKeyWord = tokens.dequeue();
        boolean programNameCheck = programKeyWord.equals("PROGRAM");
        Reporter.assertElseFatalError(programNameCheck,
                "Error need to begin program with keywork PROGRAM but was "
                        + programKeyWord);

        //check to make sure our name ideintifier is a token.
        String programName = tokens.dequeue();
        boolean tokenizer = Tokenizer.isIdentifier(programName);
        Reporter.assertElseFatalError(tokenizer,
                "Error program name is not a unique token it was "
                        + programName);

        //check to make sure out next word is the word IS
        String is = tokens.dequeue();
        boolean isCheck = is.equals("IS");
        Reporter.assertElseFatalError(isCheck,
                "Error program required the name IS but was " + is);

        Map<String, Statement> m = this.newContext();

        //now we need to loop through all the possible
        //instructions in our program
        while (tokens.front().equals("INSTRUCTION")) {
            Statement s = this.newBody();
            String name = parseInstruction(tokens, s);

            boolean inMap = m.hasKey(name);
            Reporter.assertElseFatalError(!inMap, "Error program has "
                    + "mutiple same name idefintifiers which was " + name);

            m.add(name, s);

        }
        //swap content back into this
        this.swapContext(m);

        //check for begin key word
        String begin = tokens.dequeue();
        boolean isBegin = begin.equals("BEGIN");
        Reporter.assertElseFatalError(isBegin,
                "Error program was expeceitng word BEGIN but was " + begin);

        //need to attach new block
        Statement s = this.newBody();
        s.parseBlock(tokens);
        this.swapBody(s);

        //check for end key word
        String end = tokens.dequeue();
        boolean isEND = end.equals("END");
        Reporter.assertElseFatalError(isEND,
                "Error program was expeceitng word END but was " + end);

        //check end program name with begining
        String programNameEnd = tokens.dequeue();
        boolean isProgramName = programNameEnd.equals(programName);
        Reporter.assertElseFatalError(isProgramName,
                "Error program was expeceitng word END but was "
                        + programNameEnd);
        this.setName(programName);

        boolean empty = tokens.front().equals(Tokenizer.END_OF_INPUT)
                && tokens.length() == 1;
        Reporter.assertElseFatalError(empty,
                "Error there are more tokens after program is finished");

    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
