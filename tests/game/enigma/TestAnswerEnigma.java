package game.enigma;

import game.Game;
import game.GameGraphicsMode;
import game.character.Player;
import game.system.input.ConsoleInputSystem;
import org.junit.*;
import utils.GameInputTester;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TestAnswerEnigma extends GameInputTester {
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
