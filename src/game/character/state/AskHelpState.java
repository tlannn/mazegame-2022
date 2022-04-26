package game.character.state;

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
    public boolean enter(Player player, GraphicsSystem graphics) {
        graphics.displayHelp();
        return true;
    }

    @Override
    public Action handleInput(Player player, InputSystem input) {
        return new ChangeStateAction(new StartTurnState());
    }
}
