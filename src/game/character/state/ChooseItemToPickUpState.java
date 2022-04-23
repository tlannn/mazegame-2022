package game.character.state;

import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.character.action.PickUpItemAction;
import game.item.Item;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

import java.util.List;

/**
 * Represents the state where the player must choose the item he wants to pick up
 */
public class ChooseItemToPickUpState implements BaseState {
    @Override
    public boolean enter(Player player, GraphicsSystem graphics) {
        List<Item> itemsInCell = player.getCurrentCell().getItemsInCell();

        if (!itemsInCell.isEmpty()) {
            graphics.displayText("Quel objet souhaitez-vous ramasser ?");
            graphics.displayList(itemsInCell, true);
            return true;
        }

        else {
            graphics.displayText("Il n'y a rien à ramasser.");
            return false;
        }
    }

    @Override
    public Action handleInput(Player player, InputSystem inputSystem) {
        List<Item> itemsInCell = player.getCurrentCell().getItemsInCell();

        if (!itemsInCell.isEmpty()) {
            int choice = -1;

            while (choice < 0 || choice >= itemsInCell.size())
                choice = inputSystem.getIntegerFromLetter();

            return new PickUpItemAction(itemsInCell.get(choice));
        }

        return new ChangeStateAction(new StartTurnState());
    }
}
