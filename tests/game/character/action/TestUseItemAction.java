package game.character.action;

import game.character.action.LookDiscoveredHintAction;
import game.character.Player;
import game.maze.Cell;
import game.maze.*;
import game.quest.QuestCondition;
import game.character.NonPlayerCharacter;
import game.item.*;
import game.hint.Hint;
import game.quest.*;
import game.Level;
import game.GameGraphicsMode;
import game.Game;

import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class TestUseItemAction extends Tester {

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
    Item jewel = new Jewel(JewelRarity.PURPLE, player.getCurrentCell());
    items.add (jewel);
    player.getCurrentCell().addItem(jewel);
    Level level = new Level (player, maze, quest, NPCs, items,  hints);

    Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE);
    UseItemAction action = new UseItemAction(jewel);
    assertTrue(action.execute(level, player));
  }
}
