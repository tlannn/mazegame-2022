package game.character.dialog;

import game.character.Player;
import game.system.graphics.GraphicsSystem;

/**
 * Represents a conversation with a player
 */
public abstract class Dialog {
    /**
     * Start a conversation with the player
     * @param player the player with whom we interact
     */
    public abstract void start(Player player);
}
