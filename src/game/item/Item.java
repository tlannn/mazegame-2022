package game.item;

import game.maze.*;

public abstract class Item {

    protected Cell actualCell;

    public Item(Cell cell){
        this.actualCell = cell;
    }

    public Cell getCell(){
        return this.actualCell;
    }

    public void setCell(Cell cell){
        this.actualCell = cell;
    }


    public abstract void use();


    public abstract String to_String();



}
