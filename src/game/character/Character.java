package game.character;

import game.maze.*;

public abstract class Character {
    
    private Cell currentCell;
    protected String name;

    public Character(String name){
        this.name = name;
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public void move(Cell cell){
        this.currentCell = cell;
    }

    public String getName(){
        return this.name;
    }
}
