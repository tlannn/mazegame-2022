package game.character;

import org.junit.*;
import static org.junit.Assert.*;

import java.beans.Transient;

import game.character.*;
import game.character.Character;
import game.item.*;
import game.maze.*;

public class TestCharacter{
    
    @Test
    public void testMoveOrianttaion(){
        Player player = new Player("Damien", cellStart);
        assertEquals(player.getCurrentCell(), cellStart);
        player.move(nextCell);
        assertEquals(player.getCurrentCell(), nextCell);
    }

    @Test
    public void testMove(){
        Player player = new Player("Damien", cellStart);
        Cell nextCell = new Cell(0,1);
        assertEquals(player.getCurrentCell(), cellStart);
        player.move(nextCell);
        assertEquals(player.getCurrentCell(), nextCell);
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestCharacter.class);
    }
}
