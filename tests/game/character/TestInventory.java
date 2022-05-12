package game.character;

import org.junit.*;
import static org.junit.Assert.*;
import game.character.*;
import game.maze.*;
import game.item.*;


public class TestInventory {

    @Test
    public void removeItemWhenListcontainsThisItem() throws UnknownItemException{
        Inventory inventaire = new Inventory();
        Item jewel = new Jewel(JewelRarity.GREEN);
        assertEquals(inventaire.getItems().size(), 0);
        inventaire.addItem(jewel);
        assertEquals(inventaire.getItems().size(), 1);
        try{
            inventaire.removeItem(jewel);
        }
        catch(UnknownItemException e){
            fail();
        }
    }

    @Test(expected = UnknownItemException.class)
    public void excepectionWhenItemIsRemoveAlsoIsNotInList() throws UnknownItemException{
        Inventory inventaire = new Inventory();
        Item jewel = new Jewel(JewelRarity.GREEN);
        Item jewel2 = new Jewel(JewelRarity.BLUE);
        assertEquals(inventaire.getItems().size(), 0);
        inventaire.addItem(jewel);
        assertEquals(inventaire.getItems().size(), 1);
        inventaire.removeItem(jewel2);
    }
}
