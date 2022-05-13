package game.character.dialog;

import game.Level;
import game.character.Trader;
import game.character.Character;
import game.character.NonPlayerCharacter;
import game.character.Player;
import game.hint.Hint;
import game.hint.ItemPositionHint;
import game.hint.WinningCellCoordinatesHint;
import game.item.Item;

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


public class TestDefaultDialog {

    private Level level;
    private Maze maze;
    private Player player;
    private Trader trader;
    private TraderDialog dialog;
    private WinningCellCoordinatesHint hint;
    private Parchment parchemin;


    @Before
    public void before() {
        Game game = new Game(GameGraphicsMode.CONSOLE);
        ConsoleInputSystem system = new ConsoleInputSystem();
        this.maze = new KruskalMaze(3, 3);
        this.player = new Player("Damien");
        this.trader = new Trader(maze.getCell(0, 0),1,2);
        this.dialog = new TraderDialog();
    
        List<NonPlayerCharacter> NPCs = new ArrayList<>();
        NPCs.add(this.trader);
        List<Item> items = new ArrayList<>();

        this.level = new Level(
                this.player,
                this.maze,
                new Quest(maze.getCell(1, 1), new ArrayList<QuestCondition>()),
                NPCs,
                items,
                new ArrayList<Hint>()
        );        
    }
    @Test
    public void TestParchmentAddInInventory(){
        this.hint = new WinningCellCoordinatesHint(this.maze.getCell(2, 0), true, true);
        this.parchemin = new Parchment(this.hint);
        this.trader.addParchment(parchemin);
        this.player.addGold(1);
        assertEquals(0,this.player.getInventory().size());
        this.provideInput("O");
        this.dialog.start(player);
        assertEquals(1, this.player.getInventory().size());
    }
    
    @Test
    public void TestNotEnoughMoney(){
        this.hint = new WinningCellCoordinatesHint(this.maze.getCell(2, 0), true, true);
        this.parchemin = new Parchment(this.hint);
        this.trader.addParchment(parchemin);
        this.provideInput("O");
        this.dialog.start(player);
        assertEquals(0,this.player.getInventory().size());
    }

    
}
