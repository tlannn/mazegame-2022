package game.character;

import java.util.*;

import game.Game;
import game.Level;
import game.character.state.StartTurnState;
import game.character.action.Action;
import game.hint.*;
import game.item.*;
import game.maze.*;
import game.character.state.BaseState;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;
import game.observer.ObservableEvent;



public class Player extends Character {

    private int gold;
    private Inventory inventory;
    private Stack<BaseState> state;

    public Player(String name) {
        this(name, null);
    }

    public Player(String name, Cell startingCell) {
        super(name, startingCell);
        this.gold = 0;
        this.inventory = new Inventory();
        this.hintsSeen = new ArrayList<Hint>();
        this.state = new Stack<>();
        this.state.push(new StartTurnState());
    }

    @Override
    public void update(Level level) {
        Game.getGraphicsSystem().displayGameStatus(level, this);
        boolean hasMadeAction = false; // Will be true if the player has done the unique action he can do during his turn

        while (!hasMadeAction) {
            BaseState currentState = this.state.peek(); // Look the current state of the player in the stack

            // Enter the state and print on the screen some info about the player state
            if (currentState.enter(this)) {
                Action action = currentState.handleInput(this); // Gather input from the player

                if (action != null) {
                    hasMadeAction = action.execute(level, this); // Can be false if the action doesn't correspond to a complete action (like moving)
                    //graphicsSystem.displayText(action.toString()); // Print a feedback describing the action that has been done
                }
                else
                    Game.getGraphicsSystem().displayText("Je n'ai pas compris votre intention.");

                Game.getGraphicsSystem().displayText(""); // Print an empty line
            }

            // If the player can't be in the state he is currently, go back to his previous state
            else
                this.state.pop();
        }

        // Return to StartTurnState for the next turn
        this.state.push(new StartTurnState());
    }

    public int getGold(){
        return this.gold;
    }

    public void addGold(int amount){
        this.gold += amount;
        this.notify(this,ObservableEvent.EVENT_PICK_UP_GOLD);

    }

    public void removeGold(int amount) throws NotEnoughGoldException {
        if (amount >= this.gold)
            throw new NotEnoughGoldException("Vous n'avez pas assez de gold.");

        this.gold -= amount;
        this.notify(this,ObservableEvent.EVENT_SPEND_GOLD);

    }

    public void setState(BaseState state) {
        this.state.push(state);
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
}
