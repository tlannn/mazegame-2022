package game.item;

import game.hint.*;
import game.maze.Cell;
import game.character.*;

public class Parchment extends Item {

    private Hint hint;

    public Parchment(Hint hint) {
        super(null);
        this.hint = hint;
    }

    public Parchment(Hint hint, Cell currentCell) {
        super(currentCell);
        this.hint = hint;
    }

    public void use(Player player){
        System.out.println("Vous découvrez un indice :");
        System.out.println(this.hint);
        player.addHint(this.hint);
        try{
            player.getInventory().removeItem(this);
        }
        catch(UnknownItemException e){
            System.out.println(e.getMessage());
        }
    }

    public String toString() {
        return "un parchemin";
    }
}
