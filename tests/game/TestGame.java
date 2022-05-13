package game;

import game.character.Altruist;
import game.character.Player;
import game.item.Jewel;
import game.item.JewelRarity;
import game.maze.KruskalMaze;
import game.maze.Maze;
import game.maze.MazeAlgorithm;
import game.quest.EarnGoldCondition;
import game.quest.Quest;
import game.quest.QuestCondition;
import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

import org.junit.*;
import utils.Tester;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestGame extends Tester {

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
        game.init(MazeAlgorithm.KRUSKAL_SEARCH);
        assertNotNull(game.getPlayer());
        assertEquals("Phil", game.getPlayer().toString());
    }

    @Test
    public void testGameInitializationCreatesLevel() {
        Game game = new Game(GameGraphicsMode.CONSOLE);
        assertNull(game.getLevel());

        this.provideInput("Phil");
        game.init(MazeAlgorithm.KRUSKAL_SEARCH);
        assertNotNull(game.getLevel());
    }

    @Test
    public void testPlay() {
        // Simulate the game for the player
        this.provideInput("R" + System.lineSeparator() + // Grab the item in the cell
                "I" + System.lineSeparator() + // Open inventory
                "O" + System.lineSeparator() // Accept to use the item in inventory
        );

        Player player = new Player("Phil");
        Maze maze = new KruskalMaze(2, 2);
        maze.getCell(0, 0).addItem(new Jewel(JewelRarity.GREEN)); // Add a jewel to fulfill the quest condition
        maze.getCell(0, 0).addCharacter(new Altruist(maze.getCell(0, 0)));

        List<QuestCondition> conditions = new ArrayList<>();
        conditions.add(new EarnGoldCondition(player, 5));

        Level level = new Level(
                player,
                maze,
                new Quest(maze.getCell(0, 0), conditions),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        Game game = new Game(GameGraphicsMode.CONSOLE, player, level);
        game.play();
    }
}
