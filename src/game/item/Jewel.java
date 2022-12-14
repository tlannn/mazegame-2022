package game.item;

import game.maze.*;
import game.character.*;

/**
 * Represents an Jewel which is an item. The jewel is worth gold.
 */
public class Jewel extends Item {

    private JewelRarity rarity;

    /**
    * class constructor
    *@param rarity the value of the jewel in gold.
    *
    */
    public Jewel(JewelRarity rarity){
        super(null);
        this.rarity = rarity;
    }

    /**
    * class constructor
    *@param rarity the value of the jewel in gold.
    *@param currentCell the cell where the jewel are located
    */
    public Jewel(JewelRarity rarity, Cell currentCell){
        super(currentCell);
        this.rarity = rarity;
    }

    public void use(Player player){
        System.out.println("Tu utilises un joyau de valeur : " + this.rarity.getGoldValue() + " galons d'or.");
        player.addGold(this.rarity.getGoldValue());
        try{
            player.getInventory().removeItem(this);
        }
        catch(UnknownItemException e){
            System.out.println(e.getMessage());
        }
    }
    /**
    *
    *@return the rarity of the jewel
    *
    */
    public JewelRarity getRarity(){
        return this.rarity;
    }

    /**
    * display the jewel and his color
    *
    */
    public String toString() {
        if(this.rarity.getGoldValue()==5){
            return "un joyau vert";
        }
        else if(this.rarity.getGoldValue()==10){
            return "un joyau bleu";
        }
        else{
            return "un joyau violet";
        }
    }
}
