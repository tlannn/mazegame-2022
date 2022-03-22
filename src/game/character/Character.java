package game.character;

import game.maze.*;
import game.util.*;

public abstract class Character {

    protected Cell currentCell;
    protected String name;
    protected boolean movable;


    public Character(String name, Cell startingCell) {
        this.name = name;
        this.currentCell = startingCell;
        this.movable = true;
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public void setCurrentCell(Cell cell){
        this.currentCell = cell;
    }

    public boolean isMovable(){
        return this.movable;
    }

    public String toString(){
        return this.name;
    }

}
