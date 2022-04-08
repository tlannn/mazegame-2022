package game.item;

import org.junit.*;
import static org.junit.Assert.*;
import game.maze.*;
import game.item.*;
import game.character.*;

public class TestJewel{
    @Test
    public void testAddCorrectAmountOfGoldWhenUsingJewel(){
        Cell cellJewel = new Cell(1,2);
        Item greenJewel = new Jewel(JewelRarity.GREEN, cellJewel);
        Item blueJewel = new Jewel(JewelRarity.BLUE, cellJewel);
        Item purpleJewel = new Jewel(JewelRarity.PURPLE, cellJewel);
        Player armin = new Player("Armin", cellJewel);

        armin.addItem(greenJewel);
        armin.addItem(blueJewel);
        armin.addItem(purpleJewel);

        assertEquals(0, armin.getGold());

        greenJewel.use(armin);
        assertEquals(5, armin.getGold());

        blueJewel.use(armin);
        assertEquals(15, armin.getGold());

        purpleJewel.use(armin);
        assertEquals(35, armin.getGold());
      }

    @Test
    public void testUseJewelRemovesFromPlayerInventory(){
      Cell cellJewel = new Cell(1,2);
      Item purpleJewel = new Jewel(JewelRarity.PURPLE, cellJewel);
      Player armin = new Player("Armin", cellJewel);

      armin.addItem(purpleJewel);
      assertEquals(purpleJewel, armin.getInventoryItems().get(0));

      purpleJewel.use(armin);
      assertEquals(0, armin.getInventoryItems().size());
    }

    @Test
    public void testUseJewelNotInPlayerInventoryDoesNotRemoveIt(){
      Cell cellJewel = new Cell(1,2);
      Item purpleJewel = new Jewel(JewelRarity.PURPLE, cellJewel);
      Item blueJewel = new Jewel(JewelRarity.BLUE, cellJewel);
      Player armin = new Player("Armin", cellJewel);

      armin.addItem(purpleJewel);
      assertEquals(1, armin.getInventoryItems().size());
      assertEquals(purpleJewel, armin.getInventoryItems().get(0));

      blueJewel.use(armin); // Il ne peut pas l'utiliser comme il n'est pas dans l'inventaire
      assertEquals(1, armin.getInventoryItems().size());
      assertEquals(purpleJewel, armin.getInventoryItems().get(0));
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestJewel.class);
    }
}
