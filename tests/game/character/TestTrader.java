package game.character;

import org.junit.*;
import static org.junit.Assert.*;

import game.hint.ItemPositionHint;
import game.item.Jewel;
import game.item.JewelRarity;
import game.maze.*;
import game.item.*;
import utils.Tester;

import java.util.*;


public class TestTrader extends Tester {

    private Trader marchand;
    private Cell startCell;
    private Item item;
    private ItemPositionHint hint;
    private Parchment parchemin;


    @Before
    public void before() {
        this.startCell = new Cell(0,0);
        this.marchand = new Trader(this.startCell, 1, 2);
        this.item = new Jewel(JewelRarity.BLUE);
        this.hint = new ItemPositionHint(this.item);
        this.parchemin = new Parchment(this.hint);
    }

    @Test
    public void traderIsGood(){
        assertEquals(this.startCell, this.marchand.getCurrentCell());
        assertEquals("le marchand", this.marchand.getName());
        assertTrue(this.marchand.isMovable());
    }


    @Test
    public void priceChange(){
        assertEquals(1, this.marchand.getParchmentCost());
        this.marchand.increaseParchmentCost();
        assertEquals(2, this.marchand.getParchmentCost());
    }

    @Test
    public void removeAndAddParchement() throws NoSuchElementException{
        assertEquals(0, this.marchand.getParchments().size());
        this.marchand.addParchment(this.parchemin);
        assertEquals(1, this.marchand.getParchments().size());
        this.marchand.removeParchment(this.parchemin);
        assertEquals(0, this.marchand.getParchments().size());
    }

    @Test(expected=NoSuchElementException.class)
    public void removeParchementnoContainsinList() throws NoSuchElementException{
        assertEquals(0, this.marchand.getParchments().size());
        this.marchand.removeParchment(this.parchemin);
    }





}
