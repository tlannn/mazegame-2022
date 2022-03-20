package game.character;

import game.maze.*;

/**
 * Class NonPlayerCharacter that represents any character the player doesn't control,
 * but can interact with
 */
public abstract class NonPlayerCharacter extends Character {

	/**
	 * Class constructor
	 */
	public NonPlayerCharacter(String name, Cell startingCell) {
		super(name, startingCell);
	}

	public abstract void talk();
}
