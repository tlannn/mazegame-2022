package game.maze;

import org.junit.*;
import static org.junit.Assert.*;

import java.beans.Transient;

import game.maze.*;


public class TestDepthFirstSearchMaze{

    //tester que tous les murs sont à l'extérieur
    //tester que toutes les cases à l'intérieurs ont au moins un coté ouvert
    @Test
    public void testPerfectDepthFirstSearchMaze(){
        DepthFirstSearchMaze maze = new DepthFirstSearchMaze(2,2);
        for(int i = 0; i<maze.getLength(); i++){
            for(int j = 0; j<maze.getHeight(); j++){
                assertFalse(maze.getCell(i, j).hasEastWall() && maze.getCell(i, j).hasWestWall() && maze.getCell(i, j).hasNorthWall() && maze.getCell(i, j).hasSouthWall());
            }
        }
        for(int i = 0; i<maze.getHeight(); i++){
            assertTrue(maze.getCell(0, i).hasWestWall());
        }
        for(int i = 0; i<maze.getHeight(); i++){
            assertTrue(maze.getCell(maze.getHeight()-1, 0).hasEastWall());
        }
        for(int i = 0; i<maze.getLength(); i++){
            assertTrue(maze.getCell(i, 0).hasNorthWall());
        }
        for(int i = 0; i<maze.getLength(); i++){
            assertTrue(maze.getCell(i, maze.getLength()-1).hasSouthWall());
        }
    }

     @Test
    public void testRemoveWallwithOrientation(){
        DepthFirstSearchMaze maze = new DepthFirstSearchMaze(2,2);
        Cell cell = new Cell(0,0);
        Cell cell2 = new Cell(1,0);
        if(cell.hasEastWall()){
            maze.removeWall(cell, WallOrientation.EAST);
        }
        else{
            cell.setEastWall(true);
            assertTrue(cell.hasEastWall());
            assertTrue(cell2.hasWestWall());
            maze.removeWall(cell, WallOrientation.EAST);
            assertFalse(cell.hasEastWall());
            assertFalse(cell2.hasWestWall());
        }
    }

    @Test
    public void testRmoveWallwithCellAdjacent(){
        DepthFirstSearchMaze maze = new DepthFirstSearchMaze(2,2);
        Cell cell = new Cell(0,0);
        Cell cell2 = new Cell(1,0);
        if(cell.hasEastWall()){
            maze.removeWall(cell, cell2);
        }
        else{
            cell.setEastWall(true);
            assertTrue(cell.hasEastWall());
            assertTrue(cell2.hasWestWall());
            maze.removeWall(cell, cell2);
            assertFalse(cell.hasEastWall());
            assertFalse(cell2.hasWestWall());
        }
    }

    @Test
    public void testisExternalWall(){
        DepthFirstSearchMaze maze = new DepthFirstSearchMaze(2,2);
        Cell cell = maze.getCell(0, 0);
        assertTrue(cell.hasWestWall());
        assertTrue(maze.isExternalWall(cell, WallOrientation.WEST));
        assertFalse(maze.isExternalWall(cell, WallOrientation.EAST));
    }

    @Test
    public void testgetNbCell(){
        DepthFirstSearchMaze maze = new DepthFirstSearchMaze(2,2);
        assertEquals(4, maze.getNbCell());
    }

    public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestKruskalMaze.class);
    }
}
