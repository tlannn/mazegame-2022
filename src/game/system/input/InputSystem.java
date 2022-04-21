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

	/**
	 * Ask the player to enter a letter, and convert it to an integer
	 * @return 0 if A is entered, 1 if B is entered, etc
	 */
	public int getIntegerFromLetter();
}
