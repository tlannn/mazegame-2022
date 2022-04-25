package game.character.action;

import game.Level;
import game.character.Player;
import game.maze.Orientation;

import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

/**
 * Represents the action of moving a player in the level
 */
public class MoveAction implements Action {
    private Orientation orientation;

    /**
     * Class constructor
     * @param orientation the orientation where the player must move
     */
    public MoveAction(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public boolean execute(Level level, Player player, InputSystem inputSystem, GraphicsSystem graphicsSystem){
            level.move(player, this.orientation);
        return true;
    }

    @Override
    public String toString() {
        String string = "Vous vous déplacez ";
        switch (this.orientation) {
            case NORTH:
                string += "en haut";
                break;
            case SOUTH:
                string += "en bas";
                break;
            case WEST:
                string += "à gauche";
                break;
            case EAST:
                string += "à droite";
                break;
        }

        return string;
    }
}
