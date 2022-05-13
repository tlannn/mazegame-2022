package game.character;

import org.junit.*;
import static org.junit.Assert.*;

import game.maze.*;
import utils.Tester;


public class TestAltruist extends Tester {

    @Test
    public void altruistIsGood(){
        Cell start = new Cell(0,0);
        Altruist pers = new Altruist(start);
        assertEquals(start, pers.getCurrentCell());
        assertEquals("l'altruiste", pers.getName());
        assertTrue(pers.isMovable());
    }
}
