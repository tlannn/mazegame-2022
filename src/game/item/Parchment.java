package game.item;

import game.enigma.*;

public class Parchment extends Item {

    private Hint hint;

    public Parchment(Hint hint){
        super();
        this.hint = hint;
    }

    public void use(){
        this.hint.toString();
    }
    
}
