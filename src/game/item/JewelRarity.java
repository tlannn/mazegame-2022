package game.item;

public enum JewelRarity {

    GREEN(5), BLUE(10), PURPLE(20);

    private int nb_gold;

    private JewelRarity(int nb_gold){
        this.nb_gold = nb_gold;
    }

    public int getNb_gold(){
        return this.nb_gold;
    }

    
}
