package game.enigma;

import org.junit.*;
import static org.junit.Assert.*;

import utils.GameInputTester;

import java.util.ArrayList;
import java.util.List;

public class TestQCMEnigma extends GameInputTester {
    private Enigma enigma;

    @Before
    public void before() {
        List<String> answers = new ArrayList<>();
        answers.add("Answer1");
        answers.add("Answer2");

        this.enigma = new QCMEnigma("Question", answers, answers.get(0));
    }

    @Test
    public void testEnigmaStillUnresolvedOnWrongAnswer() {
        this.provideInput("1");

        this.enigma.resolve();
        assertFalse(enigma.isResolved());
    }

    @Test
    public void testEnigmaResolvedOnRightAnswer() {
        this.provideInput("0");

        this.enigma.resolve();
        assertTrue(enigma.isResolved());
    }
}
