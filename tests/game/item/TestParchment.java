package game.item;

import org.junit.*;
import static org.junit.Assert.*;

import game.character.*;
import game.hint.*;
import game.maze.*;

public class TestParchment {
    @Test
    public void testUseParchmentRemovesFromPlayerInventory(){
      Cell cellParchment = new Cell(3,2);
      Cell winningCell = new Cell (20,17);
      Hint hint = new WinningCellCoordinatesHint(winningCell, true, false);
      Parchment parchment = new Parchment(hint, cellParchment);
      Player gaby = new Player("gaby", cellParchment);

      gaby.getInventory().addItem(parchment);
      assertEquals(parchment, gaby.getInventory().getItems().get(0));

      parchment.use(gaby);
      assertEquals(0, gaby.getInventory().getItems().size());
    }

    @Test
    public void testUseParchmentNotInPlayerInventoryDoesNotRemoveIt(){
      Cell cellParchment = new Cell(1,2);
      Cell winningCell = new Cell (20,17);
      Hint hint = new WinningCellCoordinatesHint(winningCell, true, false);
      Hint bigHint = new WinningCellCoordinatesHint(winningCell, true, true);
      Parchment parchment1 = new Parchment(hint, cellParchment);
      Parchment parchment2 = new Parchment(bigHint, cellParchment);

      Player gaby = new Player("gaby", cellParchment);
      gaby.getInventory().addItem(parchment1);

      assertEquals(1, gaby.getInventory().getItems().size());
      assertEquals(parchment1, gaby.getInventory().getItems().get(0));

      parchment2.use(gaby); // Il ne peut pas l'utiliser comme il n'est pas dans l'inventaire
      assertEquals(1, gaby.getInventory().getItems().size());
      assertEquals(parchment1, gaby.getInventory().getItems().get(0));
    }
}
