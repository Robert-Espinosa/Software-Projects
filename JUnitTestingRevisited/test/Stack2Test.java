import components.stack.Stack;
import components.stack.Stack1L;
import components.stack.Stack2;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack2Test extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        Stack<String> s = new Stack1L<>();
        return s;
    }

    @Override
    protected final Stack<String> constructorRef() {

        Stack<String> s = new Stack2<>();
        return s;
    }

}
