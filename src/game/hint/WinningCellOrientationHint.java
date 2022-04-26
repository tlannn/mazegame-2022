package game.hint;

import game.maze.*;
import game.character.*;

public class WinningCellOrientationHint extends DynamicHint {

    private Player player;
    private Cell winningCell;

    public WinningCellOrientationHint(Cell winningCell, Player player){
        super();
        this.player=player;
        this.winningCell = winningCell;
    }

    public String toString(){
        int playerX = this.player.getCurrentCell().getX();
        int playerY = this.player.getCurrentCell().getY();
        int winningX = this.winningCell.getX();
        int winningY = this.winningCell.getY();
        String res = "";

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
