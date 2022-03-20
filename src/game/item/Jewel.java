package game.item;

import game.maze.*;
import game.character.*;

public class Jewel extends Item {

    private JewelRarity rarity;

    public Jewel(Cell cell, JewelRarity rarity){
        super(cell);
        this.rarity = rarity;
    }

    public void use(Player player){
        System.out.println("Vous utilisez un joyau de valeurs : " + this.rarity.getGoldValue());
        player.addGold(this.rarity.getGoldValue());
        try{
            player.removeItem(this);
        }
        catch(UnknownItemException e){
            System.out.println(e.getMessage());
        }
    }

    
}
