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
    public void testGoldCorrectlyAddedAndRemoved() {
        Player player = new Player("Damien");

        // Player starts with 0 gold
        assertEquals(0, player.getGold());

        // Add 3 gold
        player.addGold(3);
        assertEquals(3, player.getGold());

        try {
            // Remove 1 gold
            player.removeGold(1);
            assertEquals(2, player.getGold());
        } catch (NotEnoughGoldException e) {
            fail();
        }

        // Add 5 gold
        player.addGold(5);
        assertEquals(7, player.getGold());

        try {
            // Remove 3 gold
            player.removeGold(3);
            assertEquals(4, player.getGold());
        } catch (NotEnoughGoldException e) {
            fail();
        }
    }

    @Test(expected = NotEnoughGoldException.class)
    public void testRemoveTooMuchGoldThrowsError() throws NotEnoughGoldException {
        Player player = new Player("Damien");
        player.removeGold(10);
    }

    /*@Test
    public void testItemCorrectlyRemovedToInventory(){
        Player player = new Player("Rayan");
        Hint hint = new Hint("Avance à gauche akhi");
        Item parchment = new Parchment(hint);
        assertEquals(0, player.getInventoryItems().size());
        player.addItem(parchment);
        assertEquals(1, player.getInventoryItems().size());
    }
    
    @Test(expected=UnknownItemException.class)
    public void testRemoveItemNotInInventoryThrowsException() throws UnknownItemException {
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
    }*/

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestPlayer.class);
    }
}
