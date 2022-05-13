package game.hint.fake;

import game.maze.Cell;
import game.util.Random;

/**
* Represents a fake hint which tell where the winning cell is located
*/
public class FakeWinningCellCoordinatesHint extends FakeHint {
    public FakeWinningCellCoordinatesHint(int mazeLength, int mazeHeight, Cell winningCell) {
        super();
        int x, y;

        do {
            x = Random.randInt(0, mazeLength-1);
            y = Random.randInt(0, mazeHeight-1);
        } while(x == winningCell.getX() && y == winningCell.getY());

        this.statement = "Pour gagner vous devez aller à la case (" + x + "," + y + ")";
    }
}
