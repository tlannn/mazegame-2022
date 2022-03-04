package game.enigma;

import game.maze.Cell;

public class WinningCellCoordinatesHint extends FixedHint{

    public WinningCellCoordinatesHint(Cell winningCell, boolean abscissa, boolean ordinate){
        super();

        if (abscissa && ordinate){
            this.statement = "Pour gagner il faut aller à la case (" + winningCell.getX() + "," + winningCell.getY() + ")";
        }
        else if (abscissa){
            this.statement = "Pour gagner il faut aller à la case (" + winningCell.getX() + ",?)";
        }
        else if (ordinate) {
            this.statement = "Pour gagner il faut aller à la case (?," + winningCell.getY() + ")";
        }
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
