package game.hint;
import org.junit.*;
import static org.junit.Assert.*;
import game.hint.*;
import game.maze.Cell;
import game.character.Player;
import game.item.Jewel;
import game.item.JewelRarity;
import game.quest.EarnGoldCondition;
import game.quest.QuestCondition;
import game.quest.MeetSpecificCharacterCondition;
import game.character.Altruist;



//Comment tester le Hint normal ?


public class TestHint{
    /*
    *Test que l'indice WinningCellCoordinates donne les bons coordonnés.

    */
    @Test
    public void TestWinningCellCoordinatesHint(){
        Cell cell = new Cell(5,7);
        WinningCellCoordinatesHint hint = new WinningCellCoordinatesHint(cell, true, true);
        assertEquals(hint.toString(),"Pour gagner il faut aller à la case (5,7)");
        WinningCellCoordinatesHint hint2 = new WinningCellCoordinatesHint(cell, false, true);
        assertEquals(hint2.toString(),"Pour gagner il faut aller à la case (?,7)");
        WinningCellCoordinatesHint hint3 = new WinningCellCoordinatesHint(cell, true, false);
        assertEquals(hint3.toString(),"Pour gagner il faut aller à la case (5,?)");
    }

    @Test
    public void TestDistanceFromWinningCell(){
      Cell winningCell = new Cell(2,2);
      Cell startingCell = new Cell(1,1);
      Player player = new Player ("Théo", startingCell);
      DistanceFromWinningCellHint hint = new DistanceFromWinningCellHint(winningCell, player);
      assertEquals(hint.toString(),"La case gagnante se trouve à une distance de 2 cases.");
      winningCell = new Cell(1,1);
      hint = new DistanceFromWinningCellHint(winningCell, player);
      assertEquals(hint.toString(),"La case gagnante se trouve à une distance de 0 cases.");
    }

    @Test
    public void TestItemPositionHint(){
      Jewel jewel = new Jewel(JewelRarity.GREEN, new Cell (7,14));
      ItemPositionHint hint = new ItemPositionHint(jewel);
      assertEquals(hint.toString(),"Il y a un joyau vert à la case (7,14)");
    }

    @Test
    public void TestQuestConditionHint(){
      Cell startingCell = new Cell(5,1);
      Player player = new Player ("Théo", startingCell);
      QuestCondition condition = new EarnGoldCondition(player, 5);
      QuestConditionHint hint = new QuestConditionHint(condition);
      assertEquals(hint.toString(),"Tu dois récupérer 5 golds pour valider la quête.");
      MeetSpecificCharacterCondition condition2 = new MeetSpecificCharacterCondition(new Altruist(new Cell(87,1)));
      hint = new QuestConditionHint(condition2);
      assertEquals(hint.toString(),"Tu dois absolument voir l'altruiste pour valider ta quête.");


    }



    /*
    *Test que l'indice WinningCellOrientation donne les bons coordonnés.

    */
    @Test
    public void TestWinningCellOrientationHint(){
        Cell winningCell = new Cell(2,2);
        Cell startingCell = new Cell(1,1);
        Player player = new Player ("Théo", startingCell);
        WinningCellOrientationHint hint = new WinningCellOrientationHint(winningCell, player);
        assertEquals(hint.toString(),"La case gagante se situe au Sud-Est");
        winningCell = new Cell(0,2);
        hint = new WinningCellOrientationHint(winningCell, player);
        assertEquals(hint.toString(),"La case gagante se situe au Sud-Ouest");
        winningCell = new Cell(1,2);
        hint = new WinningCellOrientationHint(winningCell, player);
        assertEquals(hint.toString(),"La case gagante se situe au Sud");
        winningCell = new Cell(1,0);
        hint = new WinningCellOrientationHint(winningCell, player);
        assertEquals(hint.toString(),"La case gagante se situe au Nord");
        winningCell = new Cell(0,0);
        hint = new WinningCellOrientationHint(winningCell, player);
        assertEquals(hint.toString(),"La case gagante se situe au Nord-Ouest");
        winningCell = new Cell(2,0);
        hint = new WinningCellOrientationHint(winningCell, player);
        assertEquals(hint.toString(),"La case gagante se situe au Nord-Est");
        winningCell = new Cell(0,1);
        hint = new WinningCellOrientationHint(winningCell, player);
        assertEquals(hint.toString(),"La case gagante se situe à l'Ouest");
        winningCell = new Cell(2,1);
        hint = new WinningCellOrientationHint(winningCell, player);
        assertEquals(hint.toString(),"La case gagante se situe à l'Est");
        winningCell = new Cell(1,1);
        hint = new WinningCellOrientationHint(winningCell, player);
        assertEquals(hint.toString(),"Vous êtes sur la case gagnante");
    }


}
