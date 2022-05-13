package game.enigma;

import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

public class TestAnswerEnigma extends Tester {
    @Test
    public void testEnigmaStillUnresolvedOnWrongAnswer() {
        this.provideInput("Wrong answer" + System.lineSeparator());

        Enigma enigma = new AnswerEnigma("Question", "Answer");
        enigma.resolve();
        assertFalse(enigma.isResolved());
    }

    @Test
    public void testEnigmaResolvedOnRightAnswer() {
        this.provideInput("Answer" + System.lineSeparator());

        Enigma enigma = new AnswerEnigma("Question", "Answer");
        enigma.resolve();
        assertTrue(enigma.isResolved());
    }
}
