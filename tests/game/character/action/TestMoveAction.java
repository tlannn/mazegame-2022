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


import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class TestMoveAction extends Tester {

  @Test
  public void TestExecute(){
    Maze maze = new DepthFirstSearchMaze(10,10);
    Cell cell = maze.getCell(3,2);
    Player player = new Player ("Sasuke",  cell);
    Cell winningCell = maze.getCell (2,2);
    List<QuestCondition> winningConditions = new ArrayList();
    Quest quest = new Quest(winningCell, winningConditions);
    List<NonPlayerCharacter> NPCs = new ArrayList();
    List<Item> items = new ArrayList();
    List<Hint> hints = new ArrayList();
    Level level = new Level (player, maze, quest, NPCs, items,  hints);//met le cell en (0,0)
    Game.setGameGraphicsMode(GameGraphicsMode.CONSOLE);

    ArrayList<Orientation> orientation = new ArrayList<Orientation>();
    orientation.add(Orientation.NORTH);
    orientation.add(Orientation.SOUTH);
    orientation.add(Orientation.EAST);
    orientation.add(Orientation.WEST);

    for (int i = 0; i<orientation.size(); i++){
      MoveAction action = new MoveAction(orientation.get(i));
      if(player.getCurrentCell().possibleOrientations().contains(orientation.get(i))){
        assertEquals(action.execute(level, player),true);
        String direction="";
        if (i == 0){
          direction = "en haut.";
        }
        if (i == 1){
          direction = "en bas.";
        }
        if (i == 2){
          direction = "à droite.";
        }
        if (i == 3){
          direction = "à gauche.";
        }
        assertEquals(action.toString(),"Vous vous déplacez "+direction);
      }
      else{
        assertEquals(action.execute(level, player),false);
      }
    }
  }
}
