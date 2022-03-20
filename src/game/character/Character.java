package game.character;

import game.maze.*;
import java.util.*;

public abstract class Character {

    protected Cell currentCell;
    protected String name;


    public Character(String name, Maze maze){
        this.name = name;
        int x=new Random().nextInt(maze.getLength());
        int y=new Random().nextInt(maze.getHeight());
        this.currentCell=new Cell(x,y);
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
