package game.enigma;
from Math import abs;
import game.maze.Cell;

public class DistanceFromWinningCellHint extends DynamicHint{

    public DistanceFromWinningCellHint(Cell winningCell, Cell playerCell){
        super();
        int distanceX=abs(winningCell.getX-playerCell.getX);
        int distanceY= abs((winningCell.getY-playerCell.gety);
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
