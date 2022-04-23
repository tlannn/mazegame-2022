package game.character.action;

import game.Level;
import game.character.Player;
import game.item.Item;

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
    public boolean execute(Level level, Player player) {
        //((Player) character).useItem(item);
        item.use(player);
        return true;
    }

    @Override
    public String toString() {
        return "Vous utilisez " + this.item;
    }
}
