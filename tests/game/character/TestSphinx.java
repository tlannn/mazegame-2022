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


public class TestSphinx{

    private List<Enigma> enigmas;
    private Enigma enigme;
    private EnigmaManager enigmaManager;
    private Sphinx sphinx;
    private Cell startCell;
    private Item item;
    private Item item2;
    private ItemPositionHint hint;
    private ItemPositionHint hint2;
    private Parchment parchemin;


    @Before
    public void before() {
        this.enigmas = new ArrayList<>();
        this.enigme = new AnswerEnigma("Question1", "Answer1");
        this.enigmas.add(this.enigme);
        this.enigmaManager = new EnigmaManager(this.enigmas);

        this.item = new Jewel(JewelRarity.BLUE);
        this.item2 = new Jewel(JewelRarity.GREEN); 
        this.hint = new ItemPositionHint(this.item);
        this.hint2 = new ItemPositionHint(this.item2);

        this.startCell = new Cell(0,0);
        this.sphinx = new Sphinx(this.startCell, this.enigmaManager);
        this.item = new Jewel(JewelRarity.BLUE);
        this.hint = new ItemPositionHint(this.item);
        this.parchemin = new Parchment(this.hint);
    }

    @Test
    public void sphinxIsGood(){
        assertEquals(this.startCell, this.sphinx.getCurrentCell());
        assertEquals("le sphinx", this.sphinx.getName());
        assertFalse(this.sphinx.isMovable());
        assertFalse(this.sphinx.hasGivenHint());
    }

    @Test
    public void addHintWhenherHintIsNull(){
        assertNull(this.sphinx.getHint());
        this.sphinx.setHint(hint);
        assertEquals(hint, this.sphinx.getHint());
        this.sphinx.setHint(hint2);
        assertFalse(hint2.equals(this.sphinx.getHint()));
    }

    @Test
    public void markHintGivenWhenSphinxGiveherHint(){
        this.sphinx.markHintGiven();
        assertTrue(this.sphinx.hasGivenHint());
    }

}
