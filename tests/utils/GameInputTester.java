package utils;

import game.Game;
import game.GameGraphicsMode;
import org.junit.After;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class GameInputTester {
    protected final InputStream systemIn = System.in;

    @After
    public void restoreSystemInput() {
        System.setIn(systemIn);
    }

    protected void provideInput(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream((input + System.lineSeparator()).getBytes());
        System.setIn(testIn);

        Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE); // Recreate the static InputSystem in Game with the new System.in
    }
}
