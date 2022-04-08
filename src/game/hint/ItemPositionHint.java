package game.hint;

import game.item.Item;

public class ItemPositionHint extends FixedHint {

    public ItemPositionHint(Item item){
        super();

        this.statement = "Il y a " + item + " à la case " + item.getCurrentCell();
    }
}
