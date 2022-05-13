package game.character;

import game.item.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents the inventory of a player, storing items that he has picked up
 */
public class Inventory {
	protected List<Item> items;

    /**
     * Class constructor
     */
	public Inventory() {
		this.items = new ArrayList<>();
	}

    /**
     * Add an item in the inventory
     * @param item the item to add
     */
	public void addItem(Item item){
        this.items.add(item);
    }

    /**
     * Remove the item if this item is in the inventory
     * @param item the item to remove
     * @throws UnknownItemException when the item is not in the inventory
     */
    public void removeItem(Item item) throws UnknownItemException {
        if (!this.items.contains(item))
           throw new UnknownItemException("Item inconnu");

        this.items.remove(item);
    }

    /**
     * Getter for attribute item with an index
     * @param index index of the item in list
     * @return the value of item
     */
    public Item getItem(int index) {
    	return this.items.get(index);
    }

    /**
     * Getter for attribute items
     * @return all the items in the inventory
     */
    public List<Item> getItems() {
    	return this.items;
    }
}
