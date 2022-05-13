package game.hint;
import java.lang.Math;
import game.maze.Cell;
import game.character.*;

/**
* Represents a hint which tell where the winning cell is located
*/
public class DistanceFromWinningCellHint extends DynamicHint{

  private Player player;
  private Cell winningCell;
  /**
  * constructor
  * @param winningCell : the cell where you habe to be to win the game
  * @param player : the player who play at the game 'blond maze'
  */
    public DistanceFromWinningCellHint(Cell winningCell, Player player){
        super();
        this.player = player;
        this.winningCell = winningCell;
    }
    /**
    * @return the distance bitween the player and the winningCell.
    */
    private int distanceFromCell() {
        int distanceX = Math.abs(winningCell.getX() - player.getCurrentCell().getX());
        int distanceY = Math.abs(winningCell.getY() - player.getCurrentCell().getY());
        return distanceX + distanceY;
    }

    /**
    * Display at how cell the winning cell is.
    */
    public String toString(){
        return "La case gagnante se trouve à une distance de " + this.distanceFromCell() + " cases.";
    }
}
