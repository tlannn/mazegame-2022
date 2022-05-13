package game.character.dialog;

import game.Level;
import game.character.Altruist;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class TestGiveHintDialog extends Tester {

    private Level level;
    private Maze maze;
    private Player player;
    private Altruist altruist;
    private GiveHintDialog dialog;
    private WinningCellCoordinatesHint hint;

    @Before
    public void before() {
        this.maze = new KruskalMaze(3, 3);
        this.player = new Player("Damien");
        this.altruist = new Altruist(maze.getCell(0, 0));
        this.hint = new WinningCellCoordinatesHint(this.maze.getCell(2, 0), true, true);

        this.dialog = new GiveHintDialog(hint);

        List<NonPlayerCharacter> NPCs = new ArrayList<>();
        NPCs.add(this.altruist);
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
    public void giveHintDialog(){
        this.altruist.setHint(hint);
        this.dialog.start(player);
        assertTrue(this.level.getHints().contains(hint));
    }
}
