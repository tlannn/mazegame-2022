package game.character;

import game.maze.*;
import game.util.*;

public abstract class Character {

    protected Cell currentCell;
    protected String name;


    public Character(String name, Cell startingCell) {
        this.name = name;
        this.currentCell = startingCell;
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public void move(Cell cell){
        this.currentCell = cell;
    }

    public String toString(){
        return this.name;
    }
}
