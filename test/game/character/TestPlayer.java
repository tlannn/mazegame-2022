package game.character;

import org.junit.*;
import static org.junit.Assert.*;

import game.character.*;
import game.item.*;
import game.maze.*;


import game.item.Parchment;

public class TestPlayer{

    @Test
    public void testConstuctor(){
        Player player = new Player("Rayan", 0);
        assertEquals("Rayan", player.getName());
        assertEquals(0, player.getGold());
        assertEquals(0, player.getInventoryItems().size());
    }

    @Test
    public void testGoldCorrectlyAddedAndRemoved(){
        Player player = new Player("Damien", 2);
        assertEquals(2, player.getGold());
        player.addGold(1);
        assertEquals(3, player.getGold());
        player.removeGold(3);
        assertEquals(0, player.getGold());
    }

    @Test
    public void testItemCorrectlyRemovedToInventory(){
        Player player = new Player("Rayan", 5);
        Hint hint = new Hint("Avance à gauche akhi");
        Item parchment = new Parchment(hint);
        assertEquals(0, player.getInventoryItems().size());
        player.addItem(parchment);
        assertEquals(1, player.getInventoryItems().size());
    }
    
    @Test(expected=UnknownItemsException.class)
    public void testRemoveItemNotInInventoryThrowsException() throws UnknownItemsException {
        Player player = new Player("Rayan", 5);
        Hint hint = new Hint("Avance à gauche akhi");
        Item parchment = new Parchment(hint);
        player.removeItem(parchment);
    }

    @Test
    public void testItemCorrectlyRemovedFromInventory() throws Exception {
        Player player = new Player("Rayan", 5);
        Hint hint = new Hint("Avance à gauche akhi");
        Item parchment = new Parchment(hint);
        player.addItem(parchment);
        assertEquals(1, player.getInventoryItems().size());
        player.removeItem(parchment);
        assertEquals(0, player.getInventoryItems().size());
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestCell.class);
    }
}
