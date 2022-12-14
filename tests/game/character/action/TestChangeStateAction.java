package game.character.action;

import game.character.state.LookingInventoryState;
import game.character.Player;
import game.maze.Cell;
import game.maze.*;
import game.quest.QuestCondition;
import game.character.NonPlayerCharacter;
import game.item.Item;
import game.hint.Hint;
import game.quest.*;
import game.Level;

import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class TestChangeStateAction extends Tester {

  @Test
  public void TestExecute(){
    Player player = new Player ("Sasuke", new Cell(3,2));
    Maze maze = new DepthFirstSearchMaze(10,10);
    Cell winningCell = new Cell (2,2);
    List<QuestCondition> winningConditions = new ArrayList<>();
    Quest quest = new Quest(winningCell, winningConditions);
    List<NonPlayerCharacter> NPCs = new ArrayList<>();
    List<Item> items = new ArrayList<>();
    List<Hint> hints = new ArrayList<>();

    Level level = new Level (player, maze, quest, NPCs, items,  hints);

    LookingInventoryState nextState = new LookingInventoryState();
    ChangeStateAction action = new ChangeStateAction(nextState);
    assertFalse(action.execute(level, player));
    assertEquals(player.getState(), nextState);
  }
}
