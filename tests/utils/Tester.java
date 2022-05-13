package utils;

import game.Game;
import game.GameGraphicsMode;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Tester {
    protected final InputStream systemIn = System.in;
    protected final PrintStream systemOut = System.out;

    @Before
    public void changeOutputStream() {
        PrintStream testOut = new PrintStream(new ByteArrayOutputStream());
        System.setOut(testOut); // Redirect output so prints during tests don't display on the screen
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    protected void provideInput(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream((input + System.lineSeparator()).getBytes());
        System.setIn(testIn);

        Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE); // Recreate the static InputSystem in Game with the new System.in
    }
}
