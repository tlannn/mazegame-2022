package game.enigma;


public class WinningCellCoordinatesHint extends FixedHint{




    public WinningCellCoordinatesHint(Cell winningCell, boolean abcisse, boolean ordonne){
        super();
        if (abcisse && ordonne){
            this.statement="Pour gagner il faut aller à la case ("+winningCell.getX()+","+winningCell.getY()+"?)";
        }
        else if (abcisse){
            this.statement="Pour gagner il faut aller à la case ("+str(winningCell.getX()+",?)";
        }
        else if (ordonne) {
            this.statement="Pour gagner il faut aller à la case (?,"+str(winningCell.getY()+")";
        }


    }

    public String toString(){
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
