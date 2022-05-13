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

public abstract class Character extends Entity {

    protected Cell currentCell;
    protected String name;
    protected boolean movable;

    /**
     * class constructor
     * @param name
     * @param startingCell
     */
    public Character(String name, Cell startingCell) {
        this.name = name;
        this.currentCell = startingCell;
        this.movable = true;

        // Inform the cell that it contains the character
        if (currentCell != null)
            this.currentCell.addCharacter(this);
    }

    public abstract void update(Level level);

    /**
     * getter for attribute currentCell
     * @return the value of attribute
     */
    public Cell getCurrentCell(){
        return this.currentCell;
    }

    /**
     * getter for attribute name
     * @return the value of attribute
     */
    public String getName(){
        return this.name;
    }

    /**
     * change the value of attribute currentCell
     * @param cell new cell
     */
    public void setCurrentCell(Cell cell){
        this.currentCell = cell;
    }

    /**
     * return true if the charcter can move
     * @return movables
     */
    public boolean isMovable(){
        return this.movable;
    }

    public String toString(){
        return this.name;
    }
}
