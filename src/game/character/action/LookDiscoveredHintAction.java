package game.character.action;

import game.character.action.Action;
import game.Game;
import game.Level;
import game.system.graphics.GraphicsSystem;
import game.character.Player;



public class LookDiscoveredHintAction implements Action{
    /**
     * Execute the action
     * @param level the level of the game
     * @param player the player that does the action
     * @return true if this Action count as an action done, false if this Action is not significant
     */
    public boolean execute(Level level, Player player){
        Game.getGraphicsSystem().displayHint(level);
        return false;
    }

    public String toString(){
        return "";
    }
}
