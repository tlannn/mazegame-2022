package game.hint;

import game.maze.Cell;

public class WinningCellCoordinatesHint extends FixedHint{
    /**
    * Constructor
    * @param winningCell the cell that you have to go to win.
    * @param abscissa if this is worth true we tell the abcissa of the winning cell
    * @param ordinate if this is worth true we tell the ordinate of the winning cell
    */
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
