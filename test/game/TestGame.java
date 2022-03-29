package game;

import game.maze.*;
import game.character.*;
import game.*;

public class TestGame {
    
    @Test
    public void testMoveOrientation() throws Exception{
        Maze maze = new DepthFirstSearchMaze(4, 5);
        Cell startcell = maze.getCell(2, 3);
        Cell objectifCell = maze.getCell(2, 2);
        startcell.setNorthWall(false);
        Player player = new Player("Damien", startcell);
        Game game = new Game(maze, player);        
        assertTrue(player.getCurrentCell() == startcell);
        game.move(Orientation.NORTH);
        assertFalse(player.getCurrentCell() == startcell);
        assertEquals(player.getCurrecntCell(), objectifCell); 
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestGame.class);
    }

}
