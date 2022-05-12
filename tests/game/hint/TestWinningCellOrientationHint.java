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

public class TestWinningCellOrientationHint{
  /*
  *Test que l'indice WinningCellOrientation donne les bons coordonnés.

  */
  @Test
  public void TestWinningCellOrientationHint(){
      Cell winningCell = new Cell(2,2);
      Cell startingCell = new Cell(1,1);
      Player player = new Player ("Théo", startingCell);
      WinningCellOrientationHint hint = new WinningCellOrientationHint(winningCell, player);
      assertEquals(hint.toString().contains("au Sud-Est"),true);
      winningCell = new Cell(0,2);
      hint = new WinningCellOrientationHint(winningCell, player);
      assertEquals(hint.toString().contains("au Sud-Ouest"),true);
      winningCell = new Cell(1,2);
      hint = new WinningCellOrientationHint(winningCell, player);
      assertEquals(hint.toString().contains("au Sud"),true);
      winningCell = new Cell(1,0);
      hint = new WinningCellOrientationHint(winningCell, player);
      assertEquals(hint.toString().contains("au Nord"),true);
      winningCell = new Cell(0,0);
      hint = new WinningCellOrientationHint(winningCell, player);
      assertEquals(hint.toString().contains("au Nord-Ouest"),true);
      winningCell = new Cell(2,0);
      hint = new WinningCellOrientationHint(winningCell, player);
      assertEquals(hint.toString().contains("au Nord-Est"),true);
      winningCell = new Cell(0,1);
      hint = new WinningCellOrientationHint(winningCell, player);
      assertEquals(hint.toString().contains("à l'Ouest"),true);
      winningCell = new Cell(2,1);
      hint = new WinningCellOrientationHint(winningCell, player);
      assertEquals(hint.toString().contains("à l'Est"),true);
      winningCell = new Cell(1,1);
      hint = new WinningCellOrientationHint(winningCell, player);
      assertEquals(hint.toString().contains("gagnante"),true);
  }
}
