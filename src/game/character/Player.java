package game.character;

import java.util.*;

import game.Level;
import game.character.state.StartTurnState;
import game.character.action.Action;
import game.hint.*;
import game.item.*;
import game.maze.*;
import game.character.state.BaseState;
import game.system.input.InputSystem;
import game.system.output.GraphicsSystem;


public class Player extends Character {

    private int gold;
    private Inventory inventory;
    private List<Hint> hintsSeen;
    private BaseState state;

    public Player(String name) {
        this(name, null);
    }

    public Player(String name, Cell startingCell) {
        super(name, startingCell);
        this.gold = 0;
        this.inventory = new Inventory();
        this.hintsSeen = new ArrayList<Hint>();
        this.state = new StartTurnState();
    }

    public void update(Level level, InputSystem inputSystem, GraphicsSystem graphicsSystem) {
        graphicsSystem.displayGameStatus(level, this);
        boolean hasMadeAction = false;

        while (!hasMadeAction) {
            this.state.enter(this, graphicsSystem);
            Action action = this.state.handleInput(this, inputSystem);

            if (action != null)
                hasMadeAction = action.execute(level, this);
            else
                graphicsSystem.displayText("Je n'ai pas compris votre intention.");

            graphicsSystem.displayText(""); // Print an empty line
        }

        // Return to StartTurnState for the next turn
        this.state = new StartTurnState();
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

    public void setState(BaseState state) {
        this.state = state;
    }

    public void useItem(Item item){
        item.use(this);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    //return true if there is an item or a character on the cell. else false.
    public boolean look(){
        int i=0;
        List<Item> items = this.currentCell.getItemsInCell();
        List<NonPlayerCharacter> characters = this.currentCell.getNonPlayerCharactersInCell();
        if (items.isEmpty() && characters.isEmpty()){
            System.out.println("Il n'y a rien sur cette case");
            return false;
        }
        else{
            System.out.print("Sur cette case se trouve : \n");
            for (i=0; i<items.size(); i++){
                System.out.println(i+"-"+items.get(i));
            }
            for(int j=i; j< characters.size(); j++){
                System.out.println(j+"-"+characters.get(j));
            }
            return true;
        }
    }

    public List<Hint> getHints(){
        return this.hintsSeen;
    }
}
