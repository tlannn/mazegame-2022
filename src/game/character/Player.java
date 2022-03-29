package game.character;

import java.util.*;

import game.enigma.*;
import game.item.*;
import game.maze.*;

public class Player extends Character {

    private int gold;
    private Inventory inventory;
    private List<Hint> hintsSeen;

    public Player(String name, Cell startingCell){
        super(name, startingCell);
        this.gold = 0;
        this.inventory = new Inventory();
        this.hintsSeen = new ArrayList<Hint>();
    }

    public int getGold(){
        return this.gold;
    }

    public void addGold(int amount){
        this.gold += amount;
    }

    public void removeGold(int amount) throws NotEnoughGoldException {
        if (amount >= this.gold)
            throw new NotEnoughGoldException("Vous n'avez pas assez de gold");

        this.gold -= amount;
    }

    public void addHint(Hint h){
        this.hintsSeen.add(h);
    }

    public void useItem(Item item){
        item.use(this);
    }

    public inventory getInventory() {
        return this.inventory;
    }

    //return true if there is an item on the cell.
    public boolean look(){
        List<Item> items = this.currentCell.getItemsInCell();
        if (items.isEmpty()){
            System.out.println("Il n'y a rien sur cette case.");
            return false;
        }

        else{
            System.out.print("Sur cette case se trouve :");
            for (int i=0; i<items.size(); i++){
                System.out.println(i+"-"+item);
            }
            return true;
        }
    }

    public List<Hint> getHints(){
        return this.hintsSeen;
    }
}
