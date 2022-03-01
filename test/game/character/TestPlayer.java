package game.character;

import org.junit.*;
import static org.junit.Assert.*;

import game.character.*;
import game.item.*;

public class TestPlayer{

    @Test
    public void testConstuctor(){
        Player player = new Player("Rayan");
        assertEquals("Rayan", player.getName());
        assertEquals(0, player.getGold());
        assertEquals(0, player.getInventoryItems().size());
    }

    @Test
    public void testGoldCorrectlyAddedAndRemoved(){
        Player player = new Player("Damien");
        assertEquals(0, player.getGold());
        player.addGold(3);
        assertEquals(3, player.getGold());
        player.removeGold(1);
        assertEquals(2, player.getGold());
    }

    @Test
    public void testItemCorrectlyRemovedToInventory(){
        Player player = new Player("Rayan");
        Hint hint = new Hint("Avance à gauche akhi");
        Item parchment = new Parchment(hint);
        assertEquals(0, player.getInventoryItems().size());
        player.addItem(parchment);
        assertEquals(1, player.getInventoryItems().size());
    }
    
    @Test(expected=UnknownItemsException.class)
    public void testRemoveItemNotInInventoryThrowsException() throws UnknownItemsException {
        Player player = new Player("Rayan");
        Hint hint = new Hint("Avance à gauche akhi");
        Item parchment = new Parchment(hint);
        player.removeItem(parchment);
    }

    @Test
    public void testItemCorrectlyRemovedFromInventory() throws Exception {
        Player player = new Player("Rayan");
        Hint hint = new Hint("Avance à gauche akhi");
        Item parchment = new Parchment(hint);
        player.addItem(parchment);
        assertEquals(1, player.getInventoryItems().size());
        player.removeItem(parchment);
        assertEquals(0, player.getInventoryItems().size());
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestPlayer.class);
    }
}
