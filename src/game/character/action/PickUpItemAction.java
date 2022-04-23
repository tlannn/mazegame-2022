package game.character.action;

import game.Level;
import game.character.Player;
import game.item.Item;

/**
 * Represents the action of picking up an item in the current cell and place it in the inventory of the player
 */
public class PickUpItemAction implements Action {
    private Item item;

    /**
     * Class constructor
     * @param item the item to pick up
     */
    public PickUpItemAction(Item item) {
        this.item = item;
    }

    @Override
    public boolean execute(Level level, Player player) {
        level.pickUpItem(player, item);
        return true;
    }

    @Override
    public String toString() {
        return "Vous ramassez " + this.item;
    }
}
