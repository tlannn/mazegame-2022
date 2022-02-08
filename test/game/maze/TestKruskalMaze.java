package game.maze;

import org.junit.*;
import static org.junit.Assert.*;

import game.maze.*;

//tester que tous les murs sont à l'extérieur
//tester que toutes les cases à l'intérieurs ont au moins un coté ouvert

public class TestKruskalMaze{

//je ne peux pas tester car c'est privé
    @Test
    public void testGetCellByNodeIndex(){
        Maze maze=new KruskalMaze(6,5);
        Cell cell=new Cell(0,0);
        assertTrue(cell.equal(maze.getCellByNodeIndex(0)));
    }



    public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestKruskalMaze.class);
    }
}
