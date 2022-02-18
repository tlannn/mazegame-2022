package game.maze;

import org.junit.*;
import static org.junit.Assert.*;

import java.beans.Transient;

import game.maze.*;

public class TestMaze {

    @Test
    public void testAllWallinMaze(){
        Maze maze = new Maze(2,2);
        assertEquals(4, maze.getNbCell());
        for(int i = 0; i<maze.getLength(); i++){
            for(int j = 0; j<maze.getHeight(); j++){
                assertTrue(maze.getCell(i, j).hasEastWall());
                assertTrue(maze.getCell(i, j).hasNorthWall());
                assertTrue(maze.getCell(i, j).hasSouthWall());
                assertTrue(maze.getCell(i, j).hasWestWall());
            }
        }

    }

    @Test
    public void testRemoveWallwithOrientation(){
        KruskalMaze maze = new KruskalMaze(2,2);
        Cell cell = maze.getCell(1, 1);
        Cell cell2 = maze.getCell(1, 2);
        assertTrue(cell.hasEastWall());
        assertTrue(cell2.hasWestWall());
        maze.removeWall(cell, WallOrientation.EAST);
        assertFalse(cell.hasEastWall());
        assertFalse(cell2.hasWestWall());
    }

    @Test
    public void testRmoveWallwithCellAdjacent(){
        Maze maze = new KruskalMaze(2,2);
        Cell cell = maze.getCell(1, 1);
        Cell cell2 = maze.getCell(2, 1);
        assertTrue(cell.hasSouthWall());
        assertTrue(cell2.hasNorthWall());
        maze.removeWall(cell, cell2);
        assertFalse(cell.hasSouthWall());
        assertFalse(cell2.hasNorthWall());
    }

    @Test
    public void testisExternalWall(){
        Maze maze = new Maze(2,2);
        Cell cell = maze.getCell(1, 1);
        assertTrue(cell.hasWestWall());
        assertTrue(maze.isExternalWall(cell, WallOrientation.WEST));
        assertFalse(maze.isExternalWall(cell, WallOrientation.EAST));
    }

    @Test
    public void testgetCell(){
        Maze maze = new KruskalMaze(2,2);
        Cell cell = maze.getCell(1, 1);
        assertEquals(cell, maze.cells[1][1]);
    }

    @Test
    public void testgetNbCell(){
        Maze maze = new Maze(2,2);
        assertEquals(4, maze.getNbCell());
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestCell.class);
    }
}

