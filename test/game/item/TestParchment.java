package game.item;

import org.junit.*;
import static org.junit.Assert.*;
import game.maze.*;
import game.item.*;
import game.character.*;
import game.enigma.*;

public class TestParchment{
    @Test
    public void testUseGoodAddHint(){
        Cell cellParchment = new Cell(1,2);
        // je ne sais  pas comment récupérer la vrai winningCell
        Cell winningCell = new Cell (20,17);
        Hint hint= new WinningCellCoordinatesHint(winningCell, true, false);
        Parchment parchment = new Parchment(cellParchment,hint);
        Player gaby = new Player("gaby");
        gaby.addItem(parchment);
        assertTrue(gaby.getInventoryItems().get(0)==parchment);//on regarde l'adresse comme ça on eset vraiment sure que c'est le meme objet
        assertTrue(gaby.getHints().size()==0);
        parchment.use(gaby);
        assertTrue(gaby.getHints().size()==1);
      }

    @Test
    public void testUseGoodRemoveItem(){
      Cell cellParchment = new Cell(3,2);
      Cell winningCell = new Cell (20,17);
      Hint hint= new WinningCellCoordinatesHint(winningCell, true, false);
      Parchment parchment = new Parchment(cellParchment,hint);
      Player gaby = new Player("gaby");
      gaby.addItem(parchment);
      assertTrue(gaby.getInventoryItems().get(0)==parchment);
      parchment.use(gaby);
      assertTrue(gaby.getInventoryItems().size()==0);
    }

    @Test
    public void testUseGoodNotRemoveItemNotInInventory(){
      Cell cellParchment = new Cell(1,2);
      Cell winningCell = new Cell (20,17);
      Hint hint= new WinningCellCoordinatesHint(winningCell, true, false);
      Hint bigHint= new WinningCellCoordinatesHint(winningCell, true, true);
      Parchment parchment1 = new Parchment(cellParchment,hint);
      Parchment parchment2 = new Parchment(cellParchment,bigHint);

      Player gaby = new Player("gaby");
      gaby.addItem(parchment1);

      assertTrue(gaby.getInventoryItems().size()==1);
      assertTrue(gaby.getInventoryItems().get(0)==parchment1);
      parchment2.use(gaby);//il ne peut pas l'utiliser comme il n'est pas dans l'inventaire
      assertTrue(gaby.getInventoryItems().size()==1);
      assertTrue(gaby.getInventoryItems().get(0)==parchment1);
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestParchment.class);
    }
}
