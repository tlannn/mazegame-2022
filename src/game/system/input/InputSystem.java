package game.system.input;

/**
 * Interface for getting input from the player
 */
public interface InputSystem {
	/**
	 * Ask the player to enter a message
	 * @return the message
	 */
	public String getMessage();

	/**
	 * Ask the player to enter an integer
	 * @return
	 */
	public int getInteger();

	/**
	 * Ask the player to enter a letter
	 * @return
	 */
	public char getLetter();
}
