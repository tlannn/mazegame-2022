package game.character.action;

import game.Level;
import game.character.Player;

//changement Emma Rayan Damien
import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;


public interface Action {
    /**
     * Execute the action
     * @param level the level of the game
     * @param player the player that does the action
     * @return true if this Action count as an action done, false if this Action is not significant
     */
    public boolean execute(Level level, Player player);

    public String toString();
}
