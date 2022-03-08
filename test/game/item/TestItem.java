package game.maze.item;

import org.junit.*;
import static org.junit.Assert.*;
import game.maze.*;
import game.item.*;

public class TestItem{
    @Test
    public void testCurrentCellJewel(){
        Cell cellJewel= new Cell(1,2);
        Item monJewel=new Jewel(cellJewel, JewelRarity.GREEN);
        assertEquals(monJewel.getCurrentCell(),cellJewell);

        Cell secondCell= new Cell(2,2);
        monJewel.setCurrentCell(secondCell);
        assertEquals(monJewel.getCurrentCell(),secondCell);

    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestItem.class);
    }
}