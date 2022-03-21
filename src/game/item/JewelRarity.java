package game.item;

public enum JewelRarity {

    GREEN(5), BLUE(10), PURPLE(20);

    private int goldValue;

    private JewelRarity(int goldValue){
        this.goldValue = goldValue;
    }

    public int getGoldValue(){
        return this.goldValue;
    }

    
}
