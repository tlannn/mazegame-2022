package game.character;

import org.junit.*;
import static org.junit.Assert.*;
import game.character.*;
import game.maze.*;


public class TestAlstruist {

    @Test
    public void altruistIsGood(){
        Cell start = new Cell(0,0);
        Altruist pers = new Altruist(start);
        assertEquals(start, pers.getCurrentCell());
        assertEquals("l'altruiste", pers.getName());
        assertTrue(pers.isMovable());
    }
}
