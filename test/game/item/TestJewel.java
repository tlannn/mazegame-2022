package game.item;

import org.junit.*;
import static org.junit.Assert.*;
import game.maze.*;
import game.item.*;
import game.character.*;

public class TestJewel{
    @Test
    public void testUseGoodAddGold(){
        Cell cellJewel = new Cell(1,2);
        Item monJewel = new Jewel(cellJewel, JewelRarity.GREEN);
        Item blueJewel = new Jewel(cellJewel, JewelRarity.BLUE);
        Item purpleJewel = new Jewel(cellJewel, JewelRarity.PURPLE);
        Player armin = new Player("Armin");

        armin.addItem(monJewel);
        armin.addItem(blueJewel);
        armin.addItem(purpleJewel);

        assertTrue(armin.getGold()==0);
        monJewel.use(armin);
        assertTrue(armin.getGold()==5);
        blueJewel.use(armin);
        assertTrue(armin.getGold()==15);
        purpleJewel.use(armin);
        assertTrue(armin.getGold()==35);
      }

    @Test
    public void testUseGoodRemoveItem(){
      Cell cellJewel = new Cell(1,2);
      Item purpleJewel = new Jewel(cellJewel, JewelRarity.PURPLE);
      Player armin = new Player("Armin");
      armin.addItem(purpleJewel);
      assertTrue(armin.getInventoryItems().get(0)==purpleJewel);
      purpleJewel.use(armin);
      assertTrue(armin.getInventoryItems().size()==0);
    }

    @Test
    public void testUseGoodNotRemoveItemNotInInventory(){
      Cell cellJewel = new Cell(1,2);
      Item purpleJewel = new Jewel(cellJewel, JewelRarity.PURPLE);
      Item blueJewel = new Jewel(cellJewel, JewelRarity.BLUE);
      Player armin = new Player("Armin");
      armin.addItem(purpleJewel);
      assertTrue(armin.getInventoryItems().size()==1);
      assertTrue(armin.getInventoryItems().get(0)==purpleJewel);
      blueJewel.use(armin);//il ne peut pas l'utiliser comme il n'est pas dans l'inventaire
      assertTrue(armin.getInventoryItems().size()==1);
      assertTrue(armin.getInventoryItems().get(0)==purpleJewel);
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestJewel.class);
    }
}
