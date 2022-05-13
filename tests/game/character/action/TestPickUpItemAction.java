package game.character.action;

import game.character.action.LookDiscoveredHintAction;
import game.character.Player;
import game.maze.Cell;
import game.maze.*;
import game.quest.QuestCondition;
import game.character.NonPlayerCharacter;
import game.item.Item;
import game.hint.Hint;
import game.quest.*;
import game.Level;
import game.GameGraphicsMode;
import game.Game;
import game.item.*;

import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class TestPickUpItemAction extends Tester {
  @Test
  public void TestExecute(){
    Player player = new Player ("Sasuke", new Cell(3,2));
    Maze maze = new DepthFirstSearchMaze(10,10);
    Cell winningCell = new Cell (2,2);
    List<QuestCondition> winningConditions = new ArrayList();
    Quest quest = new Quest(winningCell, winningConditions);
    List<NonPlayerCharacter> NPCs = new ArrayList();
    List<Item> items = new ArrayList();
    List<Hint> hints = new ArrayList();
    Item jewel = new Jewel(JewelRarity.GREEN, player.getCurrentCell());
    items.add (jewel);
    player.getCurrentCell().addItem(jewel);
    Level level = new Level (player, maze, quest, NPCs, items,  hints);

    PickUpItemAction action = new PickUpItemAction(jewel);
    assertEquals(action.execute(level, player),true);
    assertEquals(action.toString(),"Vous ramassez un joyau vert");
  }
}
