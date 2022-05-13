package game.item;

import org.junit.*;
import static org.junit.Assert.*;
import game.maze.*;
import game.item.*;
import utils.Tester;

public class TestItem extends Tester {
    @Test
    public void testCurrentCellIsCorrectlyUpdated(){
        Cell cell1 = new Cell(1,2);
        Item greenJewel = new Jewel(JewelRarity.GREEN, cell1);
        assertEquals(greenJewel.getCurrentCell(), cell1);

        Cell cell2 = new Cell(2,2);
        greenJewel.setCurrentCell(cell2);
        assertEquals(greenJewel.getCurrentCell(), cell2);
    }
}
