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

public class TestItemPositionHint{
  @Test
  public void TestItemPositionHint(){
    Jewel jewel = new Jewel(JewelRarity.GREEN, new Cell (7,14));
    ItemPositionHint hint = new ItemPositionHint(jewel);
    assertEquals(hint.toString(),"Il y a un joyau vert à la case (7,14)");
  }
}
