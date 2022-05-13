package game.character;

import org.junit.*;
import static org.junit.Assert.*;
import game.character.*;
import game.hint.fake.FakeHint;
import game.maze.*;
import utils.Tester;


public class TestFool extends Tester {

    private Fool fool;
    private Cell startCell;
    private FakeHint fakehint;
    private FakeHint fakehint2;


    @Before
    public void before() {
        this.startCell = new Cell(0,0);
        this.fool = new Fool(startCell);
        this.fakehint = new FakeHint();
        this.fakehint2 = new FakeHint();
    }

    @Test
    public void foolIsGood(){
        Cell start = new Cell(0,0);
        Fool pers = new Fool(start);
        assertEquals(start, pers.getCurrentCell());
        assertEquals("le fou", pers.getName());
        assertTrue(pers.isMovable());
    }

    @Test
    public void addHintWhenherHintIsNull(){
        assertNull(this.fool.getFakeHint());
        this.fool.setHint(this.fakehint);
        assertEquals(this.fakehint, this.fool.getFakeHint());
        this.fool.setHint(this.fakehint2);
        assertFalse(this.fakehint2.equals(this.fool.getFakeHint()));
    }
}
