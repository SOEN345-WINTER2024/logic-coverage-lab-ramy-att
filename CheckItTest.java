
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CheckItTest {

    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream testOutputContent = new ByteArrayOutputStream();

    @Before
    public void setup_Output() {
        System.setOut(new PrintStream(testOutputContent));
    }

    @After
    public void restore_System_Output() {
        System.setOut(originalSystemOut);
        testOutputContent.reset();
    }

    // CLAUSE COVERAGE
    @Test
    public void when_All_Clauses_Are_True_then_Output_Should_Be_True() {
        CheckIt.checkIt(true, true, true);
        assertEquals("P is true", testOutputContent.toString().trim());
    }

    @Test
    public void when_All_Clauses_Are_False_then_Output_Should_Be_False() {
        CheckIt.checkIt(false, false, false);
        assertEquals("P is false", testOutputContent.toString().trim());
    }

    // PREDICATE COVERAGE
    @Test
    public void when_Predicate_Is_True_then_Output_Should_Be_True() {
        CheckIt.checkIt(true, false, true);
        assertEquals("P is true", testOutputContent.toString().trim());
    }

    @Test
    public void when_Predicate_Is_False_then_Output_Should_Be_False() {
        CheckIt.checkIt(false, true, false);
        assertEquals("P isn't true", testOutputContent.toString().trim());
    }

    // CACC COVERAGE
    @Test
    public void when_CACC_True_then_Output_Should_Be_True() {
        CheckIt.checkIt(true, true, false);
        assertEquals("P is true", testOutputContent.toString().trim());
    }

    @Test
    public void when_CACC_Fails_then_Output_Should_Be_False() {
        CheckIt.checkIt(false, true, false);
        assertEquals("P is false", testOutputContent.toString().trim());
    }

    // RACC COVERAGE
    @Test
    public void when_RACC_True_then_Output_Should_Be_True() {
        CheckIt.checkIt(true, false, true);
        assertEquals("P is true", testOutputContent.toString().trim());
    }

    @Test
    public void when_RACC_Fails_then_Output_Should_Be_False() {
        CheckIt.checkIt(false, false, true);
        assertEquals("P is false", testOutputContent.toString().trim());
    }
}
