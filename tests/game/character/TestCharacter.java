package game.character;

import org.junit.*;
import static org.junit.Assert.*;

import game.character.*;
import game.enigma.*;
import game.hint.DynamicHint;
import game.hint.FixedHint;
import game.hint.ItemPositionHint;
import game.item.Jewel;
import game.item.JewelRarity;
import game.maze.*;
import game.item.*;

import java.util.*;


public class TestCharacter{

    @Test
    public void TestAltruist(){
        Cell start = new Cell(0,0);
        Altruist pers = new Altruist(start);
        assertEquals(start, pers.getCurrentCell());
        assertEquals("l'altruiste", pers.getName());
        assertTrue(pers.isMovable());
    }

    @Test
    public void TestFool(){
        Cell start = new Cell(0,0);
        Fool pers = new Fool(start);
        assertEquals(start, pers.getCurrentCell());
        assertEquals("le fou", pers.getName());
        assertTrue(pers.isMovable());
    }

    @Test
    public void TestSphinx(){
        Cell start = new Cell(0,0);

        Enigma enigme = new AnswerEnigma("Question1", "Answer1");
        List<Enigma> enigmas = new ArrayList<>();
        enigmas.add(enigme);

        EnigmaManager enigmaManager = new EnigmaManager(enigmas);

        Sphinx pers = new Sphinx(start, enigmaManager);

        assertNull(pers.getHint());

        Item item = new Jewel(JewelRarity.BLUE);
        Item item2 = new Jewel(JewelRarity.GREEN);

        ItemPositionHint hint = new ItemPositionHint(item);
        ItemPositionHint hint2 = new ItemPositionHint(item2);

        pers.setHint(hint);

        assertEquals(hint, pers.getHint());
        pers.setHint(hint2);

        assertFalse(hint2.equals(pers.getHint()));

        assertEquals(start, pers.getCurrentCell());
        assertEquals("le sphinx", pers.getName());
        assertFalse(pers.isMovable());
        assertFalse(pers.hasGivenHint());

        pers.markHintGiven();

        assertTrue(pers.hasGivenHint());
    }

    @Test
    public void TestTrader(){
        Cell start = new Cell(0,0);
        Trader pers = new Trader(start, 1, 2);
        assertEquals(start, pers.getCurrentCell());
        assertEquals("le marchand", pers.getName());
        assertTrue(pers.isMovable());

        assertEquals(1, pers.getParchmentCost());
        pers.increaseParchmentCost(pers.getpriceMultiplicator());
        assertEquals(2, pers.getParchmentCost());

        Item item = new Jewel(JewelRarity.BLUE);
        ItemPositionHint hint = new ItemPositionHint(item);
        Parchment parchemin = new Parchment(hint);

        assertEquals(0, pers.getParchments().size());
        pers.addParchment(parchemin);
        assertEquals(1, pers.getParchments().size());
        pers.removeParchment(parchemin);
        assertEquals(0, pers.getParchments().size());
    }




}
