package game.quest;

import game.character.Altruist;
import game.character.Player;
import game.character.action.TalkAction;
import game.item.Jewel;
import game.item.JewelRarity;
import game.maze.Cell;
import game.observer.ObservableEvent;
import org.junit.*;
import utils.Tester;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestQuest extends Tester {
    @Test
    public void testAssignmentInConstructor() {
        Cell winningCell = new Cell(0, 0);
        List<QuestCondition> conditions = new ArrayList<>();
        conditions.add(new MeetSpecificCharacterCondition(new Altruist(new Cell(0, 0))));

        Quest quest = new Quest(winningCell, conditions);
        assertEquals(winningCell, quest.getWinningCell());
        assertEquals(conditions, quest.getWinningConditions());
    }

    @Test
    public void testQuestIsFinishedWhenPlayerIsOnWinningCell() {
        Cell winningCell = new Cell(0, 0);
        Player player = new Player("Tommy", winningCell);
        Quest quest = new Quest(winningCell, new ArrayList<>());
        assertTrue(quest.isFinished(player.getCurrentCell()));
    }

    @Test
    public void testQuestIsFinishedOnlyWhenQuestConditionsAreFulfilled() {
        Cell winningCell = new Cell(0, 0);

        // Create characters
        Player player = new Player("Tommy", winningCell); // Place player on winning cell
        Altruist altruist = new Altruist(new Cell(0, 0));

        // Create quest conditions
        QuestCondition goldCondition = new EarnGoldCondition(player, 5);
        QuestCondition characterCondition = new MeetSpecificCharacterCondition(altruist);

        List<QuestCondition> conditions = new ArrayList<>();
        conditions.add(goldCondition);
        conditions.add(characterCondition);

        Quest quest = new Quest(winningCell, conditions);
        assertFalse(quest.isFinished(player.getCurrentCell())); // Quest is unfinished at start
        assertEquals(winningCell, quest.getWinningCell());
        assertEquals(conditions, quest.getWinningConditions());

        player.addGold(5);
        goldCondition.onNotify(player, ObservableEvent.EVENT_PICK_UP_GOLD); // Simulate player gaining 5 golds
        characterCondition.onNotify(altruist, ObservableEvent.EVENT_MEET_CHARACTER); // Simulate player talking to altruist

        assertTrue(quest.isFinished(player.getCurrentCell())); // All quest conditions are fulfilled
    }
}
