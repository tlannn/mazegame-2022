package game.quest;

import game.character.NotEnoughGoldException;
import game.character.Player;
import org.junit.*;

import static org.junit.Assert.*;

public class TestEarnGoldCondition {
    @Test
    public void testCorrectGoldRequiredWhenCreated() {
        Player player = new Player("Phil");

        EarnGoldCondition condition = new EarnGoldCondition(player, 5);
        assertEquals(5, condition.getGoldRequired());

        condition = new EarnGoldCondition(player, 10);
        assertEquals(10, condition.getGoldRequired());
    }

    @Test
    public void testQuestNotCompletedWhenNotEnoughGold() {
        Player player = new Player("Phil");
        EarnGoldCondition condition = new EarnGoldCondition(player, 10);
        player.addObserver(condition); // Add condition as observer to update it when player earn gold

        assertFalse(condition.isCompleted());
        player.addGold(5);
        assertFalse(condition.isCompleted());
        player.addGold(2);
        assertFalse(condition.isCompleted());
    }

    @Test
    public void testQuestCompletedWhenEnoughGold() {
        Player player = new Player("Phil");
        EarnGoldCondition condition = new EarnGoldCondition(player, 10);
        player.addObserver(condition); // Add condition as observer to update it when player earn gold

        try {
            assertFalse(condition.isCompleted());
            player.addGold(5);
            assertFalse(condition.isCompleted());
            player.addGold(20);
            assertTrue(condition.isCompleted());
            player.removeGold(10);
        } catch (NotEnoughGoldException e) {
            fail();
        }
    }

    @Test
    public void testQuestNotCompletedWhenPlayerLooseGoldAndHasNotEnough() {
        Player player = new Player("Phil");
        EarnGoldCondition condition = new EarnGoldCondition(player, 10);
        player.addObserver(condition); // Add condition as observer to update it when player earn gold

        try {
            assertFalse(condition.isCompleted());
            player.addGold(10);
            assertTrue(condition.isCompleted());
            player.removeGold(5);
            assertFalse(condition.isCompleted());
        } catch(NotEnoughGoldException e) {
            fail();
        }
    }
}
