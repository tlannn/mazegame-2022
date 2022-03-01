package game.character;

import java.util.*;

import game.item.*;

public class Player extends Character {

    private int gold;
    private List<Item> inventory;
    
    public Player(String name,int gold){
        super(name);
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
        if (amount >= this.gold){
            this.gold = 0;
        }
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
        try{
            this.removeItem(i);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    public List<Item> getInventoryItems() {
        return this.inventory;

    }

}
