package game.system.output;

import game.Level;
import game.character.Player;

/**
 * Interface for displaying output on the screen
 */
public interface GraphicsSystem {
	/**
	 * Display a message
	 * @param message the message to display
	 */
	public void displayText(String message);

	/**
	 * Display an error
	 * @param error the error message
	 */
	public void displayError(String error);

	/**
	 * Display the game title
	 * @param title the title of the game
	 */
	public void displayGameTitle(String title);

	/**
	 * Display the current status of the game
	 * @param level the level of the game
	 * @param player the player
	 */
	public void displayGameStatus(Level level, Player player);

	/**
	 * Display the level
	 * @param level the level of the game
	 */
	public void displayLevel(Level level);

	/**
	 * Display the inventory of the player
	 * @param player the player
	 */
	public void displayInventory(Player player);

	/**
	 * Display all commands available
	 */
	public void displayHelp();
}
