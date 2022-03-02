package game.enigma;

import game.item.*;

public class ItemPositionHint extends FixedHint {


    public ItemPositionHint(Item item){
        super();

        this.statement="Il y a "+item.toString()+" à la case "+item.getCell().toString();
    }

    public String toString(){
        return this.statement
    }
}
