package game.character.state;

import game.character.Player;
import game.character.action.Action;
import game.character.action.UseItemAction;
import game.system.input.InputSystem;
import game.system.output.GraphicsSystem;

/**
 * Represents the state where the player is looking in his inventory
 */
public class LookingInventoryState implements BaseState {
    @Override
    public boolean enter(Player player, GraphicsSystem graphics) {
        if (!player.getInventory().getItems().isEmpty()) {
            graphics.displayText("Vous avez dans votre inventaire :");
            graphics.displayList(player.getInventory().getItems(), true);
            graphics.displayText("\nSouhaitez-vous utiliser un objet ? (O/N)");
            return true;
        }

        else {
            graphics.displayText("Vous n'avez rien dans votre inventaire");
            return false;
        }

    }

    @Override
    public Action handleInput(Player player, InputSystem input) {
        if (input.getLetter() == 'A')
            //player.useItem(player.getInventory().getItem(0));
            return new UseItemAction(player.getInventory().getItem(0));

        return null;
    }
}
