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

    /**
     * class construstor
     * @param name the name of player
     * @param startingCell the start cell of player
     */
    public Player(String name, Cell startingCell) {
        super(name, startingCell);
        this.gold = 0;
        this.inventory = new Inventory();
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

    /**
     * getter for attribute gold
     * @return the value of attribute
     */
    public int getGold(){
        return this.gold;
    }

    /**
     * added the amount with the current gold
     * @param amount the amount to add 
     */
    public void addGold(int amount){
        this.gold += amount;
        this.notify(this,ObservableEvent.EVENT_PICK_UP_GOLD);

    }

    /**
     * subtracted the maount whith the current gold if this gold is more than this amount
     * @param amount the amount to subtract
     * @throws NotEnoughGoldException when the amount is more than this gold
     */
    public void removeGold(int amount) throws NotEnoughGoldException {
        if (amount > this.gold)
            throw new NotEnoughGoldException("Vous n'avez pas assez de gold.");

        this.gold -= amount;
        this.notify(this,ObservableEvent.EVENT_SPEND_GOLD);

    }

    /**
     * add the new state in the attribute stack state
     * @param state the state to add in stack
     */
    public void setState(BaseState state) {
        this.state.push(state);
    }

    /**
     * getter for attribute state
     * @return the stack
     */
    public BaseState getState(){
      return this.state.peek();
    }

    /**
     * getter for attribute inventory
     * @return the value of attribute
     */
    public Inventory getInventory() {
        return this.inventory;
    }
}
