package game.enigma;

import org.junit.*;
import utils.Tester;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestEnigmaManager extends Tester {
    private EnigmaManager emptyManager;
    private EnigmaManager filledManager;
    private Enigma enigma1;
    private Enigma enigma2;

    @Before
    public void before() {
        this.enigma1 = new AnswerEnigma("Question1", "Answer1");
        this.enigma2 = new AnswerEnigma("Question2", "Answer2");
        List<Enigma> enigmas = new ArrayList<>();
        enigmas.add(this.enigma1);
        enigmas.add(this.enigma2);

        this.emptyManager = new EnigmaManager();
        this.filledManager = new EnigmaManager(enigmas);
    }

    @Test
    public void testAddingEnigmaSavesIt() {
        assertNull(this.emptyManager.getEnigma());
        this.emptyManager.addEnigma(this.enigma1);
        this.emptyManager.addEnigma(this.enigma2);
        assertEquals(this.enigma2, this.emptyManager.getEnigma());
        assertEquals(this.enigma1, this.emptyManager.getEnigma());
    }

    @Test
    public void testAddedEnigmaIsBeforeNext() {
        // Current list : [enigma1, enigma2]
        Enigma enigma3 = new AnswerEnigma("Question3", "Answer3");
        assertEquals(this.enigma1, this.filledManager.getEnigma());
        this.filledManager.addEnigma(enigma3);
        assertEquals(enigma3, this.filledManager.getEnigma());
        assertEquals(this.enigma2, this.filledManager.getEnigma());
    }

    @Test
    public void getEnigmaReturnsFirstUnresolvedEnigma() {
        // TODO
    }
}
