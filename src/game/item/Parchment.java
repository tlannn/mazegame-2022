package game.item;

import game.enigma.*;

public class Parchment extends Item {

    private Hint hint;

    public Parchment(int x, int y, Hint hint){
        super(x, y);
        this.hint = hint;
    }

    public void use(){
        this.hint.toString();
    }

    public String to_String(){
        return "un parchemin";
    }
    
}
