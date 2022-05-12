package game.hint;
import org.junit.*;
import static org.junit.Assert.*;
import game.hint.*;
import game.hint.fake.*;
import game.maze.Cell;
import game.character.Player;
import game.item.Jewel;
import game.item.JewelRarity;
import game.quest.EarnGoldCondition;
import game.quest.QuestCondition;
import game.quest.MeetSpecificCharacterCondition;
import game.character.Altruist;

public class TestFakeWinningCellCoordinatesHint {
  /*
  *Test que l'indice WinningCellCoordinates donne les bons coordonnés.
  */
  @Test
  public void constructor(){
      Cell winningCell = new Cell(5,7);
      FakeWinningCellCoordinatesHint hint = new FakeWinningCellCoordinatesHint(10,10, winningCell);
      assertEquals(hint.toString().contains("(5,7)"),false);
  }
}
