package game.hint;

import game.item.Item;
/**
* Represents a  hint which tell the positon of an item
*/
public class ItemPositionHint extends FixedHint {

    /**
    * Constructor
    * @param item the hint say where this item is located
    */
    public ItemPositionHint(Item item){
        super();

        this.statement = "Il y a " + item + " à la case " + item.getCurrentCell();
    }
}
