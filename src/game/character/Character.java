package game.character;

import game.Level;
import game.maze.*;
import game.observer.Entity;
import game.observer.Observable;
import game.observer.ObservableEvent;
import game.observer.Observer;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity representing a character
 */
public abstract class Character extends Entity {

    protected Cell currentCell;
    protected String name;
    protected boolean movable;

    /**
     * Class constructor
     * @param name the name of the character
     * @param startingCell the starting cell of the character
     */
    public Character(String name, Cell startingCell) {
        this.name = name;
        this.currentCell = startingCell;
        this.movable = true;

        // Inform the cell that it contains the character
        if (currentCell != null)
            this.currentCell.addCharacter(this);
    }

    /**
     * Update the character. During update, the character makes an action corresponding to a turn in the game
     * @param level the current level in game
     */
    public abstract void update(Level level);

    /**
     * Getter for attribute currentCell
     * @return the value of attribute
     */
    public Cell getCurrentCell(){
        return this.currentCell;
    }

    /**
     * Getter for attribute name
     * @return the value of attribute
     */
    public String getName(){
        return this.name;
    }

    /**
     * Change the value of attribute currentCell
     * @param cell the new cell where the character is
     */
    public void setCurrentCell(Cell cell){
        this.currentCell = cell;
    }

    /**
     * Return whether the character is able to move or not
     * @return true if the character can move
     */
    public boolean isMovable(){
        return this.movable;
    }

    public String toString(){
        return this.name;
    }
}
