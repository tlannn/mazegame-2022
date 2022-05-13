package game;

import game.system.graphics.ConsoleGraphicsSystem;
import game.system.graphics.GraphicsSystem;
import game.system.input.ConsoleInputSystem;
import game.system.input.InputSystem;

import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

public class TestGameGraphicsMode extends Tester {

    @Test
    public void testEnumValueReturnsSameGraphicsAndInput() {
        GameGraphicsMode mode = GameGraphicsMode.CONSOLE;
        assertTrue(mode.getGraphicsSystem() instanceof ConsoleGraphicsSystem);
        assertTrue(mode.getInputSystem() instanceof ConsoleInputSystem);
    }

    @Test
    public void testGettersReturnsCurrentSystems() {
        GameGraphicsMode mode = GameGraphicsMode.CONSOLE;
        GraphicsSystem oldGraphicsSystem = mode.getGraphicsSystem();
        InputSystem oldInputSystem = mode.getInputSystem();

        assertEquals(oldGraphicsSystem, GameGraphicsMode.CONSOLE.getGraphicsSystem());
        assertEquals(oldInputSystem, GameGraphicsMode.CONSOLE.getInputSystem());
    }

    @Test
    public void testNewGettersReturnsNewSystems() {
        GameGraphicsMode mode = GameGraphicsMode.CONSOLE;
        GraphicsSystem oldGraphicsSystem = mode.getGraphicsSystem();
        InputSystem oldInputSystem = mode.getInputSystem();

        GraphicsSystem newGraphicsSystem = mode.getNewGraphicsSystem();
        InputSystem newInputSystem = mode.getNewInputSystem();

        assertNotEquals(oldGraphicsSystem, newGraphicsSystem);
        assertNotEquals(oldInputSystem, newInputSystem);
        assertEquals(newGraphicsSystem, GameGraphicsMode.CONSOLE.getGraphicsSystem());
        assertEquals(newInputSystem, GameGraphicsMode.CONSOLE.getInputSystem());
    }
}
