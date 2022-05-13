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

public class TestQuestConditionHint extends Tester {
  @Test
  public void TestQuestConditionHint(){
    Cell startingCell = new Cell(5,1);
    Player player = new Player ("Théo", startingCell);
    QuestCondition condition = new EarnGoldCondition(player, 5);
    QuestConditionHint hint = new QuestConditionHint(condition);
    assertEquals(hint.toString().contains("5"),true);
    assertEquals(hint.toString().contains("gold"),true);
    MeetSpecificCharacterCondition condition2 = new MeetSpecificCharacterCondition(new Altruist(new Cell(87,1)));
    hint = new QuestConditionHint(condition2);
    assertEquals(hint.toString().contains("altruiste"),true);    
  }
}
