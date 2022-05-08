package game.enigma;

import game.Game;
import game.GameGraphicsMode;
import game.character.Player;
import game.system.input.ConsoleInputSystem;
import org.junit.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TestAnswerEnigma {
    private final InputStream systemIn = System.in;

    @After
    public void restoreSystemInput() {
        System.setIn(systemIn);
    }

    private void provideInput(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE); // Recreate the static InputSystem in Game with the new System.in
    }

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
