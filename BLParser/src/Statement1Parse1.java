import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";

        //dequeue the IF
        tokens.dequeue();

        //get the condition
        String condition = tokens.dequeue();
        boolean isCondition = Tokenizer.isCondition(condition);
        Reporter.assertElseFatalError(isCondition,
                "Error the program was expecting a condition but was "
                        + condition);

        //get the THEN
        String then = tokens.dequeue();
        boolean isThen = then.equals("THEN");
        Reporter.assertElseFatalError(isThen,
                "Error program was expecting word THEN but was " + then);

        //parse block
        Statement state = s.newInstance();
        state.parseBlock(tokens);

        //dequeue the end or the else for the if
        String endOrElse = tokens.dequeue();
        boolean end = endOrElse.equals("END");
        boolean elseWord = endOrElse.equals("ELSE");

        Reporter.assertElseFatalError(elseWord || end,
                "Error the program was expecting and END or ELSE but was "
                        + endOrElse);

        Condition assembly = parseCondition(condition);

        if (end) {
            //assemble the if and check for final keyWord IF
            s.assembleIf(assembly, state);

            String wordIfEnd = tokens.dequeue();
            boolean isWordIfEnd = wordIfEnd.equals("IF");
            Reporter.assertElseFatalError(isWordIfEnd,
                    "Error the program was expecting the word IF but was "
                            + wordIfEnd);
        } else {
            //make new statement and assmble our IF ELSE block
            Statement elseBlock = s.newInstance();
            elseBlock.parseBlock(tokens);
            s.assembleIfElse(assembly, state, elseBlock);

            //get the end
            tokens.dequeue();

            String wordIfEnd = tokens.dequeue();
            boolean isWordIfEnd = wordIfEnd.equals("IF");
            Reporter.assertElseFatalError(isWordIfEnd,
                    "Error the program was expecting the word IF but was "
                            + wordIfEnd);
        }
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";

        //dequeue the first while block
        tokens.dequeue();

        //check for the condtion next
        String condition = tokens.dequeue();
        boolean isCondition = Tokenizer.isCondition(condition);
        Reporter.assertElseFatalError(isCondition,
                "Error was especting condition but was " + condition);

        //check for the DO keyword
        String wordDo = tokens.dequeue();
        boolean isDo = wordDo.equals("DO");
        Reporter.assertElseFatalError(isDo,
                "Error was especting word DO but was " + wordDo);

        //parse statement
        Statement s2 = s.newInstance();
        s2.parseBlock(tokens);

        //check keyword END
        String end = tokens.dequeue();
        boolean isEnd = end.equals("END");
        Reporter.assertElseFatalError(isEnd,
                "Error was especting word END but was " + end);

        //check keyword WHILE for our last check
        String wordWhile = tokens.dequeue();
        boolean isWhile = end.equals("WHILE");
        Reporter.assertElseFatalError(isWhile,
                "Error was especting word WHILE but was " + wordWhile);

        Condition con = parseCondition(condition);
        s.assembleWhile(con, s2);

    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";

        //assemble call to s
        String call = tokens.dequeue();
        s.assembleCall(call);

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        if (tokens.front().equals("IF")) {
            parseIf(tokens, this);
        } else if (tokens.front().equals("WHILE")) {
            parseWhile(tokens, this);
        } else {
            String check = tokens.front();
            Boolean checkSecond = Tokenizer.isIdentifier(check);
            Reporter.assertElseFatalError(checkSecond,
                    "First token cant be a identifier");
            parseCall(tokens, this);
        }

    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        this.clear();

        while (Tokenizer.isIdentifier(tokens.front())
                || tokens.front().equals("IF")
                || tokens.front().equals("WHILE")) {

            Statement s = this.newInstance();
            s.parse(tokens);
            this.addToBlock(this.lengthOfBlock(), s);

        }

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
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
