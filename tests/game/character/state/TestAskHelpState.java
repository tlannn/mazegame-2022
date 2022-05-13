package game.character.state;

import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

public class TestAskHelpState extends Tester {
    @Test
    public void testEnterReturnTrue() {
        Player player = new Player("Sig");
        AskHelpState state = new AskHelpState();
        assertTrue(state.enter(player));
    }

    @Test
    public void testNextStateIsStartTurnState() {
        Player player = new Player("Sig");
        AskHelpState state = new AskHelpState();
        Action action = state.handleInput(player);
        assertTrue(action instanceof ChangeStateAction);
        BaseState nextState = ((ChangeStateAction) action).getNextState();
        assertTrue(nextState instanceof StartTurnState);
    }
}
