package game.hint;

import game.maze.*;
import game.character.*;


/**
*Represents a hint that says the orientation of the cell
*/
public class WinningCellOrientationHint extends DynamicHint {

    private Player player;
    private Cell winningCell;

    /**
    * Constructor
    * @param winningCell the cell that you have to go to win.
    * @param player the player that play at the game
    */
    public WinningCellOrientationHint(Cell winningCell, Player player){
        super();
        this.player=player;
        this.winningCell = winningCell;
    }

    /**
    *
    *display the orientation of the cell related to player
    *
    */
    public String toString(){
        int playerX = this.player.getCurrentCell().getX();
        int playerY = this.player.getCurrentCell().getY();
        int winningX = this.winningCell.getX();
        int winningY = this.winningCell.getY();
        String res = "";

        if(playerX == winningX && playerY == winningY){
            return "Tu es sur la case gagnante";
        }

        if(playerY > winningY){
            res = res + "Nord";
        }

        else if(playerY < winningY){
            res = res + "Sud";
        }

        if(playerX > winningX){
            if(res.length() != 0){
                res += "-";
            }
            res = res + "Ouest";
        }

        else if(playerX < winningX){
            if(res.length() != 0){
                res += "-";
            }
            res = res + "Est";
        }

        if (res.equals("Est") || res.equals("Ouest")){
            return "La case gagante se situe à l'" + res;
        }
        return "La case gagante se situe au " + res;
    }
}
