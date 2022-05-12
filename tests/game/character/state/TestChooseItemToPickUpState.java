package game.character.state;

import game.Game;
import game.GameGraphicsMode;
import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.character.action.PickUpItemAction;
import game.item.Item;
import game.item.Jewel;
import game.item.JewelRarity;
import game.maze.Cell;
import game.maze.KruskalMaze;
import game.maze.Maze;
import org.junit.*;
import utils.GameInputTester;

import static org.junit.Assert.*;

public class TestChooseItemToPickUpState extends GameInputTester {
    private Cell cell;
    private Player player;
    private ChooseItemToPickUpState state;

    @Before
    public void before() {
        Maze maze = new KruskalMaze(1, 1);
        this.cell = maze.getCell(0, 0);
        this.player = new Player("Sig", this.cell);
        this.state = new ChooseItemToPickUpState();

        Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE);
    }

    @Test
    public void testEnterReturnFalseWhenNoItemInCell() {
        assertFalse(this.state.enter(this.player));
    }

    @Test
    public void testEnterReturnTrueWhenItemsInCell() {
        this.cell.addItem(new Jewel(JewelRarity.BLUE));

        assertTrue(this.state.enter(this.player));
    }

    @Test
    public void testPickUpActionForRightItemChosen() {
        this.cell.addItem(new Jewel(JewelRarity.GREEN)); // Index 0 : letter A to choose
        this.cell.addItem(new Jewel(JewelRarity.BLUE)); // Index 1 : letter B to choose

        this.provideInput("A");
        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof PickUpItemAction);

        Item itemToPickUp = ((PickUpItemAction) action).getItem();
        assertEquals(this.cell.getItemsInCell().get(0), itemToPickUp);

        this.provideInput("B");
        action = this.state.handleInput(this.player);
        assertTrue(action instanceof PickUpItemAction);

        itemToPickUp = ((PickUpItemAction) action).getItem();
        assertEquals(this.cell.getItemsInCell().get(1), itemToPickUp);
    }

    @Test
    public void testPickUpActionForSingleItemInCell() {
        this.cell.addItem(new Jewel(JewelRarity.GREEN));

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof PickUpItemAction);

        Item itemToPickUp = ((PickUpItemAction) action).getItem();
        assertEquals(this.cell.getItemsInCell().get(0), itemToPickUp);
    }

    @Test
    public void testStartTurnStateWhenNoItemInCell() {
        Action action = this.state.handleInput(this.player);

        assertTrue(action instanceof ChangeStateAction);
        BaseState nextState = ((ChangeStateAction) action).getNextState();
        assertTrue(nextState instanceof StartTurnState);
    }
}
