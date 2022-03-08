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
        System.out.println("Vous utilisez un joyau de valeurs : " + this.rarity.getNb_gold());
        player.addGold(this.rarity.getNb_gold());
        try{
            player.removeItem(this);
        }
        catch(UnknownItemsException e){
            System.out.println(e.getMessage());
        }
    }

    
}
