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

/**
 * Represent the character that the player controls
 */
public class Player extends Character {

    private int gold;
    private Inventory inventory;
    private Stack<BaseState> state;

    /**
     * Class constructor
     * @param name the name of the player
     */
    public Player(String name) {
        this(name, null);
    }

    /**
     * Class constructor
     * @param name the name of the player
     * @param startingCell the start cell of the player
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
     * Getter for attribute gold
     * @return the value of attribute
     */
    public int getGold(){
        return this.gold;
    }

    /**
     * Add an amount of gold to the current gold carried by the player
     * @param amount the amount of gold to add
     */
    public void addGold(int amount){
        this.gold += amount;
        this.notify(this,ObservableEvent.EVENT_PICK_UP_GOLD);

    }

    /**
     * Subtract an amount of gold to the gold carried by the player
     * @param amount the amount to subtract
     * @throws NotEnoughGoldException when trying to subtract more gold than the player has
     */
    public void removeGold(int amount) throws NotEnoughGoldException {
        if (amount > this.gold)
            throw new NotEnoughGoldException("Vous n'avez pas assez de gold.");

        this.gold -= amount;
        this.notify(this,ObservableEvent.EVENT_SPEND_GOLD);

    }

    /**
     * Add a new state in the stack attribute state
     * @param state the state to add in stack
     */
    public void setState(BaseState state) {
        this.state.push(state);
    }

    /**
     * Getter for top of stack attribute state
     * @return the stack
     */
    public BaseState getState(){
      return this.state.peek();
    }

    /**
     * Getter for attribute inventory
     * @return the value of attribute
     */
    public Inventory getInventory() {
        return this.inventory;
    }
}
