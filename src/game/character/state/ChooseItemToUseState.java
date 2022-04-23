package game.character.state;

import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.character.action.PickUpItemAction;
import game.character.action.UseItemAction;
import game.item.Item;
import game.system.input.InputSystem;
import game.system.output.GraphicsSystem;

import java.util.List;

public class ChooseItemToUseState implements BaseState {
    @Override
    public boolean enter(Player player, GraphicsSystem graphics) {
        List<Item> itemsInInventory = player.getInventory().getItems();

        if (!itemsInInventory.isEmpty()) {
            graphics.displayText("Quel objet souhaitez-vous utiliser ?");
            graphics.displayList(itemsInInventory, true);
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
            int choice = -1;

            while (choice < 0 || choice >= itemsInInventory.size())
                choice = input.getIntegerFromLetter();

            return new UseItemAction(itemsInInventory.get(choice));
        }

        return new ChangeStateAction(new StartTurnState());
    }
}
