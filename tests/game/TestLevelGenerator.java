package game;

import game.character.Player;
import game.maze.MazeAlgorithm;
import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

public class TestLevelGenerator extends Tester {
    private LevelGenerator generator;
    private Player player;

    @Before
    public void before() {
        this.player = new Player("Sid");
        this.generator = new LevelGenerator();
    }

    @Test
    public void testMazeSizeIsCorrect() {
        Level level = generator.generateLevel(player, MazeAlgorithm.DEPTH_FIRST_SEARCH);

        assertEquals(10, level.getMaze().getLength());
        assertEquals(10, level.getMaze().getHeight());
    }

    @Test
    public void testWinningCellIsCorrect() {
        Level level = generator.generateLevel(player, MazeAlgorithm.DEPTH_FIRST_SEARCH);

        assertEquals(level.getMaze().getCell(8, 3), level.getQuest().getWinningCell());
    }

    @Test
    public void testItemsAndNPCsProportionsAreCorrect() {
        Level level = generator.generateLevel(player, MazeAlgorithm.DEPTH_FIRST_SEARCH);
        int nbItemsAndNPCsExpected = level.getMaze().getNbCell() / 2;
        int nbItems = level.getItems().size();
        int nbNPCs = level.getNPCs().size();

        // Items and NPCs count equal half the number of cells
        assertEquals(nbItemsAndNPCsExpected, nbItems + nbNPCs);

        // Items count is in range
        assertTrue(nbItems >= (nbItemsAndNPCsExpected / 2) - 1);
        assertTrue(nbItems <= (nbItemsAndNPCsExpected / 2) + 1);

        // NPCs count is in range
        assertTrue(nbNPCs >= (nbItemsAndNPCsExpected / 2) - 1);
        assertTrue(nbNPCs <= (nbItemsAndNPCsExpected / 2) + 1);
    }
}
