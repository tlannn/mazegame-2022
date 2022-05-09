package game;

import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

import org.junit.*;
import utils.GameInputTester;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestGame extends GameInputTester {

    @Test
    public void testModeIsCorrectWhenCreated() {
        Game game = new Game(GameGraphicsMode.CONSOLE);

        // Systems must be the same
        assertEquals(GameGraphicsMode.CONSOLE.getGraphicsSystem(), Game.getGraphicsSystem());
        assertEquals(GameGraphicsMode.CONSOLE.getInputSystem(), Game.getInputSystem());
    }

    @Test
    public void testModeChangeCreatesNewSystemInstances() {
        GraphicsSystem oldGraphicsSystem = Game.getGraphicsSystem();
        InputSystem oldInputSystem = Game.getInputSystem();

        // Change game graphics mode and create new console systems
        Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE);

        // Check game systems correspond to new console systems
        assertEquals(GameGraphicsMode.CONSOLE.getGraphicsSystem(), Game.getGraphicsSystem());
        assertEquals(GameGraphicsMode.CONSOLE.getInputSystem(), Game.getInputSystem());

        // Check old game systems are different from new game systems
        assertNotEquals(oldGraphicsSystem, Game.getGraphicsSystem());
        assertNotEquals(oldInputSystem, Game.getInputSystem());

    }

    @Test
    public void testGameInitializationCreatesPlayer() {
        Game game = new Game(GameGraphicsMode.CONSOLE);
        assertNull(game.getPlayer());

        this.provideInput("Phil");
        game.init();
        assertNotNull(game.getPlayer());
        assertEquals("Phil", game.getPlayer().toString());
    }

    @Test
    public void testGameInitializationCreatesLevel() {
        Game game = new Game(GameGraphicsMode.CONSOLE);
        assertNull(game.getLevel());

        this.provideInput("Phil");
        game.init();
        assertNotNull(game.getLevel());
    }
}
