package game.item;

import game.maze.*;
import game.character.*;

public abstract class Item {

    protected Cell currentCell;

    public Item(Cell cell){
        this.currentCell = cell;
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public void setCurrentCell(Cell cell){
        this.currentCell = cell;
    }

    public abstract void use(Player player);
    
}
