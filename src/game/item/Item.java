package game.item;

import game.maze.*;
import game.character.*;
import game.observer.Entity;
import game.observer.Observable;
import game.observer.ObservableEvent;
import game.observer.Observer;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents an item
 */
public abstract class Item extends Entity {

    protected Cell currentCell;
    /**
    * class constructor
    *@param cell the cell where the item is located
    *
    */
    public Item(Cell cell){
        this.currentCell = cell;

        // Inform the cell that it contains the item
        if (currentCell != null)
            this.currentCell.addItem(this);
    }

    /**
    *
    *@return the current cell of the item
    */
    public Cell getCurrentCell(){
        return this.currentCell;
    }

    /**
    *
    *@param cell the new location of the cell
    */
    public void setCurrentCell(Cell cell){
        this.currentCell = cell;
    }

    /**
    * allows the player to use an item
    *@param player the player who use the item
    *
    */
    public abstract void use(Player player);
}
