package game.character.state;

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
    @Test
    public void testEnterReturnFalseWhenNoItemInCell() {
        Maze maze = new KruskalMaze(1, 1);
        Cell cell = maze.getCell(0, 0);
        Player player = new Player("Sig", cell);

        ChooseItemToPickUpState state = new ChooseItemToPickUpState();
        assertFalse(state.enter(player));
    }

    @Test
    public void testEnterReturnTrueWhenItemsInCell() {
        Maze maze = new KruskalMaze(1, 1);
        Cell cell = maze.getCell(0, 0);
        Player player = new Player("Sig", cell);
        cell.addItem(new Jewel(JewelRarity.BLUE));

        ChooseItemToPickUpState state = new ChooseItemToPickUpState();
        assertTrue(state.enter(player));
    }

    @Test
    public void testPickUpActionForRightItemChosen() {
        Maze maze = new KruskalMaze(1, 1);
        Cell cell = maze.getCell(0, 0);
        Player player = new Player("Sig", cell);
        cell.addItem(new Jewel(JewelRarity.GREEN)); // Index 0 : letter A to choose
        cell.addItem(new Jewel(JewelRarity.BLUE)); // Index 1 : letter B to choose

        this.provideInput("A");
        ChooseItemToPickUpState state = new ChooseItemToPickUpState();
        Action action = state.handleInput(player);
        assertTrue(action instanceof PickUpItemAction);

        Item itemToPickUp = ((PickUpItemAction) action).getItem();
        assertEquals(cell.getItemsInCell().get(0), itemToPickUp);

        this.provideInput("B");
        action = state.handleInput(player);
        assertTrue(action instanceof PickUpItemAction);

        itemToPickUp = ((PickUpItemAction) action).getItem();
        assertEquals(cell.getItemsInCell().get(1), itemToPickUp);
    }

    @Test
    public void testPickUpActionForSingleItemInCell() {
        Maze maze = new KruskalMaze(1, 1);
        Cell cell = maze.getCell(0, 0);
        Player player = new Player("Sig", cell);
        cell.addItem(new Jewel(JewelRarity.GREEN));

        ChooseItemToPickUpState state = new ChooseItemToPickUpState();
        Action action = state.handleInput(player);
        assertTrue(action instanceof PickUpItemAction);

        Item itemToPickUp = ((PickUpItemAction) action).getItem();
        assertEquals(cell.getItemsInCell().get(0), itemToPickUp);
    }

    @Test
    public void testStartTurnStateWhenNoItemInCell() {
        Maze maze = new KruskalMaze(1, 1);
        Cell cell = maze.getCell(0, 0);
        Player player = new Player("Sig", cell);

        ChooseItemToPickUpState state = new ChooseItemToPickUpState();
        Action action = state.handleInput(player);

        assertTrue(action instanceof ChangeStateAction);
        BaseState nextState = ((ChangeStateAction) action).getNextState();
        assertTrue(nextState instanceof StartTurnState);
    }
}
