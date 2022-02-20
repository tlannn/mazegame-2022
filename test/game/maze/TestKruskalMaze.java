package game.maze;

import org.junit.*;
import static org.junit.Assert.*;

public class TestKruskalMaze {
    @Test
    public void testPerfectKruskalMaze() {
        KruskalMaze maze = new KruskalMaze(3, 3);

        // Check all cells have at least one wall removed
        for (int i = 0; i < maze.getLength(); i++) {
            for (int j = 0; j < maze.getHeight(); j++) {
                assertFalse(maze.getCell(i, j).hasEastWall() && maze.getCell(i, j).hasWestWall() && maze.getCell(i, j).hasNorthWall() && maze.getCell(i, j).hasSouthWall());
            }
        }

        // Check that vertical external walls are not removed
        for (int i = 0; i < maze.getHeight(); i++) {
            assertTrue(maze.getCell(0, i).hasWestWall());
            assertTrue(maze.getCell(maze.getHeight() - 1, 0).hasEastWall());
        }

        // Check that horizontal external walls are not removed
        for (int i = 0; i < maze.getLength(); i++) {
            assertTrue(maze.getCell(i, 0).hasNorthWall());
            assertTrue(maze.getCell(i, maze.getLength() - 1).hasSouthWall());
        }
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestKruskalMaze.class);
    }
}
