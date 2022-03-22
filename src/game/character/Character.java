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

    public void move_orientation(Orientation orientation){
        int x = this.currentCell.getX();
        int y = this.currentCell.getY();
        if (orientation == Orientation.EAST){
            this.currentCell = new Cell(x+1, y);
        }
        if (orientation == Orientation.WEST){
            this.currentCell = new Cell(x-1, y);
        }
        if (orientation == Orientation.NORTH){
            this.currentCell = new Cell(x, y-1);
        }
        if (orientation == Orientation.SOUTH){
            this.currentCell = new Cell(x, y+1);
        }
    }

    public void move(Orientation orientation){
        if (this.movable && this.currentCell.possibleOrientations().contains(orientation) ){
            this.move_orientation(orientation);
        }
    }

    public String toString(){
        return this.name;
    }
}
