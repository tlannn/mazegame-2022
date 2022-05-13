package game.character.state;

import game.Game;
import game.GameGraphicsMode;
import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.character.action.LookDiscoveredHintAction;
import game.character.action.MoveAction;
import game.item.Jewel;
import game.item.JewelRarity;
import game.maze.Orientation;
import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

public class TestStartTurnState extends Tester {
    private Player player;
    private StartTurnState state;

    @Before
    public void before() {
        this.player = new Player("Sig");
        this.state = new StartTurnState();

        Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE);
    }

    @Test
    public void testEnterReturnTrue() {
        assertTrue(this.state.enter(this.player));
    }

    @Test
    public void testKeyHReturnsAskHelpState() {
        this.provideInput("H");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof ChangeStateAction);

        BaseState nextState = ((ChangeStateAction) action).getNextState();
        assertTrue(nextState instanceof AskHelpState);
    }

    @Test
    public void testKeyZReturnsMoveActionToNorth() {
        this.provideInput("Z");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof MoveAction);

        Orientation orientation = ((MoveAction) action).getOrientation();
        assertEquals(Orientation.NORTH, orientation);
    }

    @Test
    public void testKeySReturnsMoveActionToSouth() {
        this.provideInput("S");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof MoveAction);

        Orientation orientation = ((MoveAction) action).getOrientation();
        assertEquals(Orientation.SOUTH, orientation);
    }

    @Test
    public void testKeyQReturnsMoveActionToWest() {
        this.provideInput("Q");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof MoveAction);

        Orientation orientation = ((MoveAction) action).getOrientation();
        assertEquals(Orientation.WEST, orientation);
    }

    @Test
    public void testKeyDReturnsMoveActionToEast() {
        this.provideInput("D");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof MoveAction);

        Orientation orientation = ((MoveAction) action).getOrientation();
        assertEquals(Orientation.EAST, orientation);
    }

    @Test
    public void testKeyPReturnsChooseNPCToTalkState() {
        this.provideInput("P");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof ChangeStateAction);

        BaseState nextState = ((ChangeStateAction) action).getNextState();
        assertTrue(nextState instanceof ChooseNPCToTalkState);
    }

    @Test
    public void testKeyRReturnsChooseItemToPickUpState() {
        this.provideInput("R");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof ChangeStateAction);

        BaseState nextState = ((ChangeStateAction) action).getNextState();
        assertTrue(nextState instanceof ChooseItemToPickUpState);
    }

    @Test
    public void testKeyIReturnsLookingInventoryState() {
        this.provideInput("I");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof ChangeStateAction);

        BaseState nextState = ((ChangeStateAction) action).getNextState();
        assertTrue(nextState instanceof LookingInventoryState);
    }

    @Test
    public void testKeyVReturnsLookDiscoveredHintAction() {
        this.provideInput("V");

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof LookDiscoveredHintAction);
    }

    @Test
    public void testInvalidKeyReturnsNull() {
        this.provideInput("X");
        assertNull(this.state.handleInput(this.player));
    }
}
