package game.character.state;

import game.character.Player;
import game.character.action.Action;
import game.system.input.InputSystem;
import game.system.output.GraphicsSystem;

/**
 * Represents the state where the player must choose the item he wants to pick up
 */
public class ChooseItemToPickupState implements BaseState {
    @Override
    public void enter(Player player, GraphicsSystem graphics) {

    }

    @Override
    public Action handleInput(Player player, InputSystem inputSystem) {
        return null;
    }
}
