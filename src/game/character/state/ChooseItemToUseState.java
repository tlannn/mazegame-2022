package game.character.state;

import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.character.action.UseItemAction;
import game.item.Item;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

import java.util.List;

public class ChooseItemToUseState implements BaseState {
    @Override
    public boolean enter(Player player, GraphicsSystem graphics) {
        List<Item> itemsInInventory = player.getInventory().getItems();

        if (!itemsInInventory.isEmpty()) {
            if (itemsInInventory.size() > 1) { // Single items will automatically be used
                graphics.displayText("Quel objet souhaitez-vous utiliser ?");
                graphics.displayList(itemsInInventory, true);
            }
            return true;
        }

        else {
            graphics.displayText("Vous n'avez pas d'objets que vous pouvez utiliser dans votre inventaire.");
            return false;
        }
    }

    @Override
    public Action handleInput(Player player, InputSystem input) {
        List<Item> itemsInInventory = player.getInventory().getItems();

        if (!itemsInInventory.isEmpty()) {
            if (itemsInInventory.size() > 1) { // Single items will automatically be used
                int choice = -1;

                while (choice < 0 || choice >= itemsInInventory.size())
                    choice = input.getIntegerFromLetter();

                return new UseItemAction(itemsInInventory.get(choice));
            }

            else
                return new UseItemAction(itemsInInventory.get(0));
        }

        return new ChangeStateAction(new StartTurnState());
    }
}
