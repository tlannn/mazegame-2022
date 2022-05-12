package game.character.state;

import game.Game;
import game.GameGraphicsMode;
import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.character.action.PickUpItemAction;
import game.character.action.UseItemAction;
import game.item.Item;
import game.item.Jewel;
import game.item.JewelRarity;
import game.maze.Cell;
import game.maze.KruskalMaze;
import game.maze.Maze;
import org.junit.*;
import utils.GameInputTester;

import static org.junit.Assert.*;

public class TestChooseItemToUseState extends GameInputTester {
    private Player player;
    private ChooseItemToUseState state;

    @Before
    public void before() {
        this.player = new Player("Sig");
        this.state = new ChooseItemToUseState();

        Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE);
    }

    @Test
    public void testEnterReturnFalseWhenNoItemInInventory() {
        assertFalse(this.state.enter(this.player));
    }

    @Test
    public void testEnterReturnTrueWhenItemsInInventory() {
        this.player.getInventory().addItem(new Jewel(JewelRarity.BLUE));
        this.player.getInventory().addItem(new Jewel(JewelRarity.BLUE));

        assertTrue(this.state.enter(this.player));
    }

    @Test
    public void testUseActionForRightItemChosen() {
        this.player.getInventory().addItem(new Jewel(JewelRarity.GREEN)); // Index 0 : letter A to choose
        this.player.getInventory().addItem(new Jewel(JewelRarity.BLUE)); // Index 1 : letter B to choose

        this.provideInput("A");
        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof UseItemAction);

        Item itemToUse = ((UseItemAction) action).getItem();
        assertEquals(this.player.getInventory().getItem(0), itemToUse);

        this.provideInput("B");
        action = this.state.handleInput(this.player);
        assertTrue(action instanceof UseItemAction);

        itemToUse = ((UseItemAction) action).getItem();
        assertEquals(this.player.getInventory().getItem(1), itemToUse);
    }

    @Test
    public void testUseActionForSingleItemInInventory() {
        this.player.getInventory().addItem(new Jewel(JewelRarity.GREEN));

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof UseItemAction);

        Item itemToUse = ((UseItemAction) action).getItem();
        assertEquals(this.player.getInventory().getItem(0), itemToUse);
    }

    @Test
    public void testStartTurnStateWhenNoItemInCell() {
        Action action = state.handleInput(player);

        assertTrue(action instanceof ChangeStateAction);
        BaseState nextState = ((ChangeStateAction) action).getNextState();
        assertTrue(nextState instanceof StartTurnState);
    }
}
