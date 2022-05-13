package game.hint;
import org.junit.*;
import static org.junit.Assert.*;
import game.hint.*;
import game.maze.Cell;
import game.character.Player;
import game.item.Jewel;
import game.item.JewelRarity;
import game.quest.EarnGoldCondition;
import game.quest.QuestCondition;
import game.quest.MeetSpecificCharacterCondition;
import game.character.Altruist;
import utils.Tester;

public class TestWinningCellCoordinatesHint extends Tester {
  /*
  *Test que l'indice WinningCellCoordinates donne les bons coordonnés.

  */
  @Test
  public void TestWinningCellCoordinatesHint(){
      Cell cell = new Cell(5,7);
      WinningCellCoordinatesHint hint = new WinningCellCoordinatesHint(cell, true, true);
      assertEquals(hint.toString().contains("(5,7)"),true);
      WinningCellCoordinatesHint hint2 = new WinningCellCoordinatesHint(cell, false, true);
      assertEquals(hint2.toString().contains("(?,7)"),true);
      WinningCellCoordinatesHint hint3 = new WinningCellCoordinatesHint(cell, true, false);
      assertEquals(hint3.toString().contains("(5,?)"),true);
  }
}
