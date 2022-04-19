package game.character.action;

import game.Level;
import game.character.Player;
import game.character.state.BaseState;

/**
 * Represents the action of changing the current state of a player
 */
public class ChangeStateAction implements Action {
    private BaseState nextState;

    /**
     * Class constructor
     * @param nextState the new state for the player
     */
    public ChangeStateAction(BaseState nextState) {
        this.nextState = nextState;
    }

    @Override
    public boolean execute(Level level, Player player) {
        player.setState(this.nextState);
        return false;
    }
}
