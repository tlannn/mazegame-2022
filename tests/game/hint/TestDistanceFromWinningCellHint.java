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

public class TestDistanceFromWinningCellHint extends Tester {
    @Test
    public void TestDistanceFromWinningCell(){
      Cell winningCell = new Cell(2,2);
      Cell startingCell = new Cell(1,1);
      Player player = new Player ("Théo", startingCell);
      DistanceFromWinningCellHint hint = new DistanceFromWinningCellHint(winningCell, player);
      assertEquals(hint.toString().contains("2"),true);
      winningCell = new Cell(1,1);
      hint = new DistanceFromWinningCellHint(winningCell, player);
      assertEquals(hint.toString().contains("0"),true);
    }
}
