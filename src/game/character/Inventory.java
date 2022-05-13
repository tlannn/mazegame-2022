package game.character;

import game.item.*;
import java.util.List;
import java.util.ArrayList;

public class Inventory {
	protected List<Item> items;

    /**
     * class consructor
     */
	public Inventory() {
		this.items = new ArrayList<>();
	}

    /**
     * add the new item in the list
     * @param item the new item to add
     */
	public void addItem(Item item){
        this.items.add(item);
    }

    /**
     * remove the item if this item is in list items
     * @param item the item to remove
     * @throws UnknownItemException when the itrem is not in list 
     */
    public void removeItem(Item item) throws UnknownItemException {
        if (!this.items.contains(item))
           throw new UnknownItemException("Item inconnu");

        this.items.remove(item);
    }

    /**
     * getter for attribute item with an index
     * @param index index of the item in list
     * @return the value of item
     */
    public Item getItem(int index) {
    	return this.items.get(index);
    }

    /**
     * getter for attribute items
     * @return the list items
     */
    public List<Item> getItems() {
    	return this.items;
    }
}
