package game.quest;

import game.Game;
import game.GameGraphicsMode;
import game.character.Altruist;
import game.character.Fool;
import game.character.Player;
import game.maze.Cell;
import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

public class TestMeetSpecificCharacterCondition extends Tester {
    @Test
    public void testQuestCompletedWhenPlayerMeetRightCharacter() {
        Player player = new Player("Phil");
        Altruist altruist = new Altruist(new Cell(0, 0));
        Fool fool = new Fool(new Cell(0, 0));

        // Set game graphics mode so the player can talk with characters
        Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE);
        MeetSpecificCharacterCondition condition = new MeetSpecificCharacterCondition(altruist);

        // Add observers to characters so the condition can be completed
        altruist.addObserver(condition);
        fool.addObserver(condition);

        assertFalse(condition.isCompleted());
        fool.talk(player);
        assertFalse(condition.isCompleted());
        altruist.talk(player);
        assertTrue(condition.isCompleted());
    }
}
