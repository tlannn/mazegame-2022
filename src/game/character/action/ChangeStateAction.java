package game.character.action;

import game.Level;
import game.character.Player;
import game.character.state.BaseState;

import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

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
    public boolean execute(Level level, Player player){
        player.setState(this.nextState);
        return false;
    }

    @Override
    public String toString() {
        return "";
    }
}
