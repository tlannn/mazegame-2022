package game.character;

/**
 * Class NonPlayerCharacter that represents any character the player doesn't control,
 * but can interact with
 */
public abstract class NonPlayerCharacter extends Character {

	/**
	 * Class constructor
	 */
	public NonPlayerCharacter(String name) {
		super(name);
	}

	public abstract void talk();
}
