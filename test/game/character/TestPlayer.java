package game.character;

import java.beans.Transient;

import game.character.*;
import game.item.*;
import game.maze.*;


import game.item.Parchment;

public class TestPlayer{

    @Test
    public void testConstuct(){
        Player player = new Player("Rayan", 0);
        assertEquals("Rayan", player.getName());
        assertEquals(0, player.getGold());
        assertequals(0, player.getInventoryItems().lenght());
    }

    @Test
    public void testAddGoldAndRemoveGold(){
        Player player = new Player("Damien", 2);
        assertEquals(2, player.getGold());
        player.addGold(1);
        assertEquals(3, player.getGold());
        player.removeGold(3);
        assertEquals(0, player.getGold());
    }

    @Test
    public void testaddItem(){
        Player player = new Player("Rayan", 5);
        Hint hint = new Hint("Avance à gauche akhi");
        Item parchment = new Parchment(hint);
        assertequals(0, player.getInventoryItems().lenght());
        player.addItem(parchment);
        assertequals(1, player.getInventoryItems().lenght());
    }
    
    @Test(expected=UnknownItemsException.class)
    public void testUnknownItemsException() throws Exception{
        Player player = new Player("Rayan", 5);
        Hint hint = new Hint("Avance à gauche akhi");
        Item parchment = new Parchment(hint);
        player.removeItem(parchment);
    }

    @Test
    public void testremoveIem() throws Exception{
        Player player = new Player("Rayan", 5);
        Hint hint = new Hint("Avance à gauche akhi");
        Item parchment = new Parchment(hint);
        player.addItem(parchment);
        assertequals(1, player.getInventoryItems().lenght());
        player.removeItem(parchment);
        assertequals(0, player.getInventoryItems().lenght());
    }

    
}
