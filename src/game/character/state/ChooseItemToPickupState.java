package game.character.state;

import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.character.action.PickUpItemAction;
import game.item.Item;
import game.system.input.InputSystem;
import game.system.output.GraphicsSystem;

import java.util.List;

/**
 * Represents the state where the player must choose the item he wants to pick up
 */
public class ChooseItemToPickupState implements BaseState {
    @Override
    public boolean enter(Player player, GraphicsSystem graphics) {
        return true;
    }

    @Override
    public Action handleInput(Player player, InputSystem inputSystem) {
        return null;
    }
}
