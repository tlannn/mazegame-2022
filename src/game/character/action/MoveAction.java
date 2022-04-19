package game.character.action;

import game.Level;
import game.character.Player;
import game.maze.Orientation;

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
    public boolean execute(Level level, Player player) {
        level.move(player, this.orientation);
        return true;
    }
}
