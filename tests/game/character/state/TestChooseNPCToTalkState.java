package game.character.state;

import game.Game;
import game.GameGraphicsMode;
import game.character.Altruist;
import game.character.Fool;
import game.character.NonPlayerCharacter;
import game.character.Player;
import game.character.action.Action;
import game.character.action.TalkAction;
import game.maze.Cell;
import game.maze.KruskalMaze;
import game.maze.Maze;
import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

public class TestChooseNPCToTalkState extends Tester {
    private Cell cell;
    private Player player;
    private ChooseNPCToTalkState state;

    @Before
    public void before() {
        Maze maze = new KruskalMaze(1, 1);
        this.cell = maze.getCell(0, 0);
        this.player = new Player("Sig", this.cell);
        this.state = new ChooseNPCToTalkState();

        Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE);
    }

    @Test
    public void testEnterReturnFalseWhenNoNPCInCell() {
        assertFalse(this.state.enter(this.player));
    }

    @Test
    public void testEnterReturnTrueWhenItemsInCell() {
        // NPCs are added to the cell
        new Altruist(this.cell);
        new Fool(this.cell);

        assertTrue(this.state.enter(this.player));
    }

    @Test
    public void testTalkActionForRightItemChosen() {
        // NPCs are added to the cell
        new Altruist(this.cell); // Index 0 : letter A to choose
        new Fool(this.cell); // Index 1 : letter B to choose

        this.provideInput("A");
        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof TalkAction);

        NonPlayerCharacter npcToTalk = ((TalkAction) action).getNPC();
        assertEquals(this.cell.getNonPlayerCharactersInCell().get(0), npcToTalk);

        this.provideInput("B");
        action = this.state.handleInput(this.player);
        assertTrue(action instanceof TalkAction);

        npcToTalk = ((TalkAction) action).getNPC();
        assertEquals(this.cell.getNonPlayerCharactersInCell().get(1), npcToTalk);
    }

    @Test
    public void testTalkActionForSingleNPCInCell() {
        new Altruist(this.cell);

        Action action = this.state.handleInput(this.player);
        assertTrue(action instanceof TalkAction);

        NonPlayerCharacter npcToTalk = ((TalkAction) action).getNPC();
        assertEquals(this.cell.getNonPlayerCharactersInCell().get(0), npcToTalk);
    }

    @Test
    public void testReturnNullWhenNoNPCInCell() {
        assertNull(this.state.handleInput(this.player));
    }
}
