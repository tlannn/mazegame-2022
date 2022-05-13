package game.character.dialog;

import game.Game;
import game.GameGraphicsMode;
import game.Level;
import game.character.Sphinx;
import game.character.Character;
import game.character.NonPlayerCharacter;
import game.character.Player;
import game.enigma.AnswerEnigma;
import game.enigma.Enigma;
import game.enigma.EnigmaManager;
import game.hint.Hint;
import game.hint.ItemPositionHint;
import game.hint.WinningCellCoordinatesHint;
import game.item.Item;

import game.item.Parchment;
import game.maze.Cell;
import game.maze.KruskalMaze;
import game.maze.Maze;
import game.maze.Orientation;
import game.observer.ObservableEvent;
import game.quest.Quest;
import game.quest.QuestCondition;
import org.junit.*;
import utils.Tester;

import org.junit.*;

import game.system.input.ConsoleInputSystem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class TestEnigmaDialog extends Tester {

    private Level level;
    private Maze maze;
    private Player player;
    private Sphinx sphinx;
    private EnigmaDialog dialog;
    private WinningCellCoordinatesHint hint;
    private Parchment parchemin;
    private List<Enigma> enigmas;
    private Enigma enigme;
    private EnigmaManager enigmaManager;


    @Before
    public void before() {
        Game game = new Game(GameGraphicsMode.CONSOLE);
        ConsoleInputSystem system = new ConsoleInputSystem();
        this.maze = new KruskalMaze(3, 3);
        this.player = new Player("Damien");
    
        this.enigmas = new ArrayList<>();
        this.enigme = new AnswerEnigma("Question1", "Answer1");
        this.enigmas.add(this.enigme);
        this.enigmaManager = new EnigmaManager(this.enigmas);
        this.hint = new WinningCellCoordinatesHint(this.maze.getCell(2, 0), true, true);


        this.sphinx = new Sphinx(maze.getCell(0, 0),this.enigmaManager);
        this.dialog = new EnigmaDialog(this.sphinx);

        List<NonPlayerCharacter> NPCs = new ArrayList<>();
        NPCs.add(this.sphinx);
        List<Item> items = new ArrayList<>();

        this.level = new Level(
                this.player,
                this.maze,
                new Quest(maze.getCell(1, 1), new ArrayList<QuestCondition>()),
                NPCs,
                items,
                new ArrayList<Hint>()
        );        
        this.hint.addObserver(level);

    }
    @Test
    public void TestParchmentAddInInventory(){
        this.sphinx.setHint(hint);
        this.provideInput("Answer1");
        this.dialog.start(player);
        assertTrue(this.level.getHints().contains(hint));
        this.dialog.start(player);
        assertEquals(this.level.getHints().size(),1);
    }
    
    @Test
    public void TestWrongAnswer(){
        this.sphinx.setHint(hint);
        this.provideInput("WrongAnswer");
        this.dialog.start(player);
        assertFalse(this.level.getHints().contains(hint));
    }
    
}
