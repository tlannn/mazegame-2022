package game.item;

import org.junit.*;
import static org.junit.Assert.*;
import game.maze.*;
import game.item.*;
import game.character.*;
import game.enigma.*;

public class TestParchment{
    @Test
    public void testAddCorrectHintToPlayerWhenUsingParchment(){
        Cell cellParchment = new Cell(1,2);
        Cell winningCell = new Cell (20,17);
        Hint hint = new WinningCellCoordinatesHint(winningCell, true, false);
        Parchment parchment = new Parchment(cellParchment,hint);
        Player gaby = new Player("gaby", cellParchment);

        gaby.addItem(parchment);
        assertEquals(parchment, gaby.getInventoryItems().get(0)); // On regarde l'adresse comme ça on est vraiment certain que c'est le même objet
        assertEquals(0, gaby.getHints().size());

        parchment.use(gaby);
        assertEquals(1, gaby.getHints().size());
        assertEquals(hint, gaby.getHints().get(0));
      }

    @Test
    public void testUseParchmentRemovesFromPlayerInventory(){
      Cell cellParchment = new Cell(3,2);
      Cell winningCell = new Cell (20,17);
      Hint hint = new WinningCellCoordinatesHint(winningCell, true, false);
      Parchment parchment = new Parchment(cellParchment,hint);
      Player gaby = new Player("gaby", cellParchment);

      gaby.addItem(parchment);
      assertEquals(parchment, gaby.getInventoryItems().get(0));

      parchment.use(gaby);
      assertEquals(0, gaby.getInventoryItems().size());
    }

    @Test
    public void testUseParchmentNotInPlayerInventoryDoesNotRemoveIt(){
      Cell cellParchment = new Cell(1,2);
      Cell winningCell = new Cell (20,17);
      Hint hint = new WinningCellCoordinatesHint(winningCell, true, false);
      Hint bigHint = new WinningCellCoordinatesHint(winningCell, true, true);
      Parchment parchment1 = new Parchment(cellParchment,hint);
      Parchment parchment2 = new Parchment(cellParchment,bigHint);

      Player gaby = new Player("gaby", cellParchment);
      gaby.addItem(parchment1);

      assertEquals(1, gaby.getInventoryItems().size());
      assertEquals(parchment1, gaby.getInventoryItems().get(0));

      parchment2.use(gaby); // Il ne peut pas l'utiliser comme il n'est pas dans l'inventaire
      assertEquals(1, gaby.getInventoryItems().size());
      assertEquals(parchment1, gaby.getInventoryItems().get(0));
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestParchment.class);
    }
}
