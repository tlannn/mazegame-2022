package game.character;

import org.junit.*;
import static org.junit.Assert.*;

import game.hint.*;
import game.item.*;
import game.maze.*;

public class TestPlayer{

    @Test
    public void testConstuctor(){
        Cell cell = new Cell(2, 1);
        Player player = new Player("Rayan", cell);
        assertEquals("Rayan", player.toString());
        assertEquals(0, player.getGold());
        assertEquals(0, player.getInventoryItems().size());
    }

    @Test
    public void testGoldCorrectlyAddedAndRemoved() {
        Cell cell = new Cell(2, 1);
        Player player = new Player("Damien", cell);

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
        Cell cell = new Cell(2, 1);
        Player player = new Player("Damien", cell);
        player.removeGold(10);
    }

    @Test
    public void testItemCorrectlyRemovedToInventory(){
        Cell cell = new Cell(2, 1);
        Cell winningcell = new Cell(3,3);
        Player player = new Player("Rayan", cell);
        Hint distanceHint = new DistanceFromWinningCellHint(winningcell, player);
        Parchment parchment = new Parchment(distanceHint, cell);
        assertEquals(0, player.getInventoryItems().size());
        player.addItem(parchment);
        assertEquals(1, player.getInventoryItems().size());
    }

    @Test(expected=UnknownItemException.class)
    public void testRemoveItemNotInInventoryThrowsException() throws UnknownItemException {
        Cell cell = new Cell(2, 1);
        Cell winningcell = new Cell(3,3);
        Player player = new Player("Rayan", cell);
        Hint distanceHint = new DistanceFromWinningCellHint(winningcell, player);
        Item parchment = new Parchment(distanceHint, cell);
        player.removeItem(parchment);
    }

    @Test
    public void testItemCorrectlyRemovedFromInventory() throws Exception {
        Cell cell = new Cell(2, 1);
        Cell winningcell = new Cell(3,3);
        Player player = new Player("Rayan", cell);
        Hint distanceHint = new DistanceFromWinningCellHint(winningcell, player);
        Item parchment = new Parchment(distanceHint, cell);
        player.addItem(parchment);
        assertEquals(1, player.getInventoryItems().size());
        player.removeItem(parchment);
        assertEquals(0, player.getInventoryItems().size());
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestPlayer.class);
    }
}
