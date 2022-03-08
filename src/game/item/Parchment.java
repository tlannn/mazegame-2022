package game.item;

import game.enigma.*;
import game.maze.Cell;
import game.character.*;

public class Parchment extends Item {

    private Hint hint;

    public Parchment(Cell currentCell, Hint hint){
        super(currentCell);
        this.hint = hint;
    }

    public void use(Player player){
        System.out.println("Vous découvrez un indice :");
        System.out.println(this.hint);
        player.addHint(this.hint);
        try{
            player.removeItem(this);
        }
        catch(UnknownItemsException e){
            System.out.println(e.getMessage());
        }
    }

    public String toString() {
        return "un parchemin";
    }
}
