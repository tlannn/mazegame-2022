package game.enigma;
import java.lang.Math;
import game.maze.Cell;

public class DistanceFromWinningCellHint extends DynamicHint{
  private Player player;

    public DistanceFromWinningCellHint(Cell winningCell, Player player){
        super();
        this.player=player;
    }

    @overiding
    public void toString(){
      int distanceX=Math.abs(winningCell.getX()-player.getCurrentCell().getX());
      int distanceY= Math.abs(winningCell.getY()-player.getCurrentCell().gety());
      int distance= distanceX+distanceY;

      this.statement= "La case gagnante se trouve à une distance de "+distance+" cases.";
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
