package game.character.state;

import game.character.Player;
import game.character.action.Action;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

public interface BaseState {
    /**
     * Graphically inform the player about the state he is currently in
     * @param player the player
     * @return true if the player can enter this state, false if we must go back to the previous state
     */
    public boolean enter(Player player);

    /**
     * Handle the input of the player according to his current state
     * @param player the player
     * @return the Action to do according to the player's input. Can be null if the input is invalid
     */
    public Action handleInput(Player player);
}
