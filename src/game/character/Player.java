package game.character;

import java.util.*;

import game.enigma.*;
import game.item.*;
import game.maze.*;
import game.util.Event;
import game.util.Observer;

public class Player extends Character {

    private int gold;
    private List<Item> inventory;
    private List<Character> charactersMet;
    private List<Hint> hintsSeen;
    private List<Observer> observers;

    public Player(String name, Cell startingCell){
        super(name, startingCell);
        this.gold = 0;
        this.inventory = new ArrayList<Item>();
        this.charactersMet = new ArrayList<Character>();
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

    public void removeItem(Item i) throws UnknownItemException {
        if (!this.inventory.contains(i))
           throw new UnknownItemException("Item inconnu");
        this.inventory.remove(i);
    }

    public void useItem(Item i){
        i.use(this);
    }

    public List<Item> getInventoryItems() {
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

    public List<Character> getCharactersMet(){
        return this.charactersMet;
    }

    public List<Hint> getHints(){
        return this.hintsSeen;
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notify(Character character, Event event) {
        for (Observer observer : this.observers) {
            observer.onNotify(character, event);
        }
    }
}
