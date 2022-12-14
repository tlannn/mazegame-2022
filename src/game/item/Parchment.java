package game.item;

import game.hint.*;
import game.maze.Cell;
import game.character.*;
import static game.observer.ObservableEvent.EVENT_HINT_DISCOVERED;

/**
*
*Represents the Parchment which is an item
*
*/
public class Parchment extends Item {

    private Hint hint;

    /**
    * class constructor
    *@param hint the hint of the Parchment
    */
    public Parchment(Hint hint) {
        super(null);
        this.hint = hint;
    }

    /**
    * class constructor
    *@param hint the hint of the Parchment
    *@param currentCell the cell where the hint is located
    */
    public Parchment(Hint hint, Cell currentCell) {
        super(currentCell);
        this.hint = hint;
    }

//on affiche le parchemin mais on ne le supprime pas de l'inventaire. Ce n'est pas un usage unique.
    public void use(Player player){
        System.out.println("Tu découvres un indice :");
        System.out.println(this.hint);
        try{
            player.getInventory().removeItem(this);
        }
        catch(UnknownItemException e){
            System.out.println("erreur tu ne peux pas enlever l'item "+this+" de ta liste.");
        }
        hint.notify(hint, EVENT_HINT_DISCOVERED);
    }

    public String toString() {
        return "un parchemin";
    }
}
