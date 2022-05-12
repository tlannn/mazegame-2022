package game.character.state;

import game.Game;
import game.GameGraphicsMode;
import game.character.Inventory;
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

public class TestLookingInventoryState extends GameInputTester {
    private Player player;
    private Inventory inventory;
    private LookingInventoryState state;

    @Before
    public void before() {
        this.player = new Player("Sig");
        this.inventory = this.player.getInventory();
        this.state = new LookingInventoryState();

        Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE);
    }

    @Test
    public void testEnterReturnFalseWhenNoItemInInventory() {
        assertFalse(this.state.enter(this.player));
    }

    @Test
    public void testEnterReturnTrueWhenItemsInInventory() {
        this.inventory.addItem(new Jewel(JewelRarity.BLUE));
        this.inventory.addItem(new Jewel(JewelRarity.BLUE));

        assertTrue(this.state.enter(this.player));
    }

    @Test
    public void testChooseItemToUseStateWhenInputYes() {
        this.provideInput("O");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof ChangeStateAction);

        BaseState nextState = ((ChangeStateAction) action).getNextState();
        assertTrue(nextState instanceof ChooseItemToUseState);
    }

    @Test
    public void testStartTurnStateWhenInputNo() {
        this.provideInput("N");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof ChangeStateAction);

        BaseState nextState = ((ChangeStateAction) action).getNextState();
        assertTrue(nextState instanceof StartTurnState);
    }

    @Test
    public void testReturnNullWhenWrongInput() {
        this.provideInput("A");

        assertNull(this.state.handleInput(this.player));
    }
}
