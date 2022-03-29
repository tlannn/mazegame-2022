package game;

import game.maze.*;
import game.character.*;
import game.*;

public class TestGame {
    
    @Test
    public void testMoveOrientation() throws Exception{
        Maze maze = new DepthFirstSearchMaze(4, 5);
        Cell startcell = maze.getCell(2, 3);
        Player player = new Player("Damien", startcell);
        Game game = new Game(maze, player);
        List<Orientation> possibleOrientations = player.getCurrentCell().possibleOrientations();
        assertTrue(player.getCurrentCell() == startcell);
        game.move(possibleOrientations[0]);
        assertFalse(player.getCurrentCell() == startcell);
    }

}
