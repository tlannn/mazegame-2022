package game.item;
import java.util.*;


public class Player {
    private String name;
    private int gold;
    private List<Item> inventory;
    
    public Player(String name,int gold){
        this.name = name;
        this.gold = gold;
        this.inventory = new ArrayList<Item>();
    }

    public int getGold(){
        return this.gold;
    }

    public void addGold(int amount){
        this.gold += amount; 
    }

    public void removeGold(int amount){
        this.gold -= amount;
    }

    public void addItem(Item i){
        this.inventory.add(i);
    }

    public void removeItem(Item i) throws UnknownItemsException {
        if (!this.inventory.contains(i)) 
           throw new UnknownItemsException("Item inconnue");
        this.inventory.remove(i);
    }
    
    public void useItem(Item i){
        i.use();
        this.removeItem(i);
    }

    public list<Item> getInventoryItems() {
        return this.inventory

    }

}
