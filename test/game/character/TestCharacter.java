package game.character;

import org.junit.*;
import static org.junit.Assert.*;

import game.character.*;
import game.character.Character;
import game.item.*;

public class TestCharacter{

    @Test
    public void testMove(){
        Cell cellStart = new Cell(0,0);
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
