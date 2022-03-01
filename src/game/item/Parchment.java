package game.item;

public class Parchment extends Item {

    private Hint hint;

    public Parchement(Hint hint){
        super();
        this.hint = hint;
    }

    public void use(){
        this.hint.toString();
    }
    
}
