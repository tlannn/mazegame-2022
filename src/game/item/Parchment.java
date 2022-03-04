package game.item;

import game.enigma.*;
import game.maze.Cell;

public class Parchment extends Item {

    private Hint hint;

    public Parchment(Cell currentCell, Hint hint){
        super(currentCell);
        this.hint = hint;
    }

    public void use(){
        System.out.println("Vous découvrez un indice :");
        System.out.println(this.hint);
    }

    public String toString() {
        return "un parchemin";
    }
}
