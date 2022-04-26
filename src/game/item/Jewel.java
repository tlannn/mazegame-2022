package game.item;

import game.maze.*;
import game.character.*;

public class Jewel extends Item {

    private JewelRarity rarity;

    public Jewel(JewelRarity rarity){
        super(null);
        this.rarity = rarity;
    }

    public Jewel(JewelRarity rarity, Cell currentCell){
        super(currentCell);
        this.rarity = rarity;
    }

    public void use(Player player){
        System.out.println("Vous utilisez un joyau de valeur : " + this.rarity.getGoldValue() + " golds");
        player.addGold(this.rarity.getGoldValue());
        try{
            player.getInventory().removeItem(this);
        }
        catch(UnknownItemException e){
            System.out.println(e.getMessage());
        }
    }

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
