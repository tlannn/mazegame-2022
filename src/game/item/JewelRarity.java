package game.item;

/**
*
*Represents the rarity of the jewel
*
*/
public enum JewelRarity {

    GREEN(5), BLUE(10), PURPLE(20);

    private int goldValue;

    /**
    * constructor of the enum JewelRarity
    *
    */
    private JewelRarity(int goldValue){
        this.goldValue = goldValue;
    }

    /**
    *@return the jewel value.
    *
    */
    public int getGoldValue(){
        return this.goldValue;
    }
}
