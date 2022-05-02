package game.character.action;

import game.Level;
import game.character.Player;
import game.item.Item;

import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

/**
 * Represents the action of using an item
 */
public class UseItemAction implements Action {
    private Item item;

    /**
     * Class constructor
     * @param item the item to use
     */
    public UseItemAction(Item item) {
        this.item = item;
    }

    @Override
    public boolean execute(Level level, Player player){
        item.use(player);
        return true;
    }

    @Override
    public String toString() {
        return "Vous utilisez " + this.item;
    }
}
