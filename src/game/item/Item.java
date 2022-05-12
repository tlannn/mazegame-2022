package game.item;

import game.maze.*;
import game.character.*;
import game.observer.Entity;
import game.observer.Observable;
import game.observer.ObservableEvent;
import game.observer.Observer;

import java.util.List;
import java.util.ArrayList;

public abstract class Item extends Entity {

    protected Cell currentCell;

    public Item(Cell cell){
        this.currentCell = cell;

        // Inform the cell that it contains the item
        if (currentCell != null)
            this.currentCell.addItem(this);
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public void setCurrentCell(Cell cell){
        this.currentCell = cell;
    }

    public abstract void use(Player player);
}
