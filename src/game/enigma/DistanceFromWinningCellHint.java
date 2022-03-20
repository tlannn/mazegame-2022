package game.enigma;
import java.lang.Math;
import game.maze.Cell;
import game.character.*;

public class DistanceFromWinningCellHint extends DynamicHint{

  private Player player;
  private Cell winningCell;

    public DistanceFromWinningCellHint(Cell winningCell, Player player){
        super();
        this.player=player;
        this.winningCell = winningCell;
    }

    public String toString(){
      int distanceX=Math.abs(winningCell.getX()-player.getCurrentCell().getX());
      int distanceY= Math.abs(winningCell.getY()-player.getCurrentCell().getY());
      int distance= distanceX+distanceY;

      this.statement= "La case gagnante se trouve à une distance de "+distance+" cases.";
      return this.statement;
    }
}






/*
CoordinateHint indice = new CoordinateHint(winningCell)
indice.toString()


Game {
    listeIndices = []

    constructeur() {
        CoordinateHint indice = new CoordinateHint(winningCell, abscisse)
        ItemPositionHint indice2 = new ItemPositionHint(position, item)
    }
}*/
