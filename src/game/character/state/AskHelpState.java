package game.character.state;

import game.Game;
import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

/**
 * Represents the state where the player is asking for help (regarding the possible actions he can do)
 */
public class AskHelpState implements BaseState {
    @Override
    public boolean enter(Player player) {
        Game.getGraphicsSystem().displayHelp();
        return true;
    }

    @Override
    public Action handleInput(Player player) {
        return new ChangeStateAction(new StartTurnState());
    }
}
