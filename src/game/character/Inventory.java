package game.character;

import java.util.List;
import java.util.ArrayList;

public class Inventory {
	protected List<Item> items;

	public Inventory() {
		this.items = new ArrayList<>();
	}

	public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item) throws UnknownItemException {
        if (!this.items.contains(item))
           throw new UnknownItemException("Item inconnu");
        
        this.inventory.remove(item);
    }

    public List<Item> getItem(int index) {
    	return this.items.get(index);
    }

    public List<Item> getItems() {
    	return this.items;
    }

    public String toString() {
    	String str = "Vous avez dans votre inventaire :\n";
    	
    	for (int i = 0; i < this.items.size(); ++i)
    		str += i + " - " + item + "\n";

    	return str;
    }
}
