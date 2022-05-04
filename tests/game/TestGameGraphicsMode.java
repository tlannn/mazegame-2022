package game;

import game.system.graphics.ConsoleGraphicsSystem;
import game.system.input.ConsoleInputSystem;
import org.junit.*;
import static org.junit.Assert.*;

public class TestGameGraphicsMode {
    @Test
    public void testEnumValueReturnsSameGraphicsAndInput() {
        GameGraphicsMode mode = GameGraphicsMode.CONSOLE;
        assertTrue(mode.getGraphicsSystem() instanceof ConsoleGraphicsSystem);
        assertTrue(mode.getInputSystem() instanceof ConsoleInputSystem);
    }
}
