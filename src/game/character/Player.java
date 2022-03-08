package game.character;

import java.util.*;
import game.enigma.*;
import game.item.*;

public class Player extends Character {

    private int gold;
    private List<Item> inventory;
    private List<Character> charactersMet;
    private List<Hint> hintsSeen;

    public Player(String name){
        super(name);
        this.gold = 0;
        this.inventory = new ArrayList<Item>();
        this.charactersMet = new ArrayList<Character>();
    }

    public int getGold(){
        return this.gold;
    }

    public void addGold(int amount){
        this.gold += amount;
    }

    public void removeGold(int amount) throws NotEnoughGoldException {
        if (amount >= this.gold)
            throw new NotEnoughGoldException("You do not have enough gold");

        this.gold -= amount;
    }

    public void addItem(Item i){
        this.inventory.add(i);
    }
    
    public void addHint(Hint h){
        this.hintsSeen.add(h);
    }

    public void addCharacterMet(Character c){
        this.charactersMet.add(c);
    }

    public int getNumbersCharactersMet(){
        return this.charactersMet.size();
    }

    public void removeItem(Item i) throws UnknownItemsException {
        if (!this.inventory.contains(i)) 
           throw new UnknownItemsException("Item inconnue");
        this.inventory.remove(i);
    }
    
    public void useItem(Item i){
        i.use(this);
    }

    public List<Item> getInventoryItems() {
        return this.inventory;

    }
}
