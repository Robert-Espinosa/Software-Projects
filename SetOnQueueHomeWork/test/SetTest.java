import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Customized JUnit test fixture for {@code Sequence3}.
 */
public class SetTest extends SequenceTest {

    @Override
    protected final Set<String> constructorTest() {
        return new Set<String>();
    }

    @Override
    protected final Sequence<String> constructorRef() {
        return new Sequence1L<String>();
    }

}
