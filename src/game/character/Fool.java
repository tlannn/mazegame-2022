package game.character;

import game.character.dialog.GiveHintDialog;
import game.hint.fake.FakeHint;
import game.maze.*;

/**
 * A NonPlayerCharacter called fool who gives a fake hint to the player
 */
public class Fool extends NonPlayerCharacter {
	private FakeHint fakeHint;

	/**
	 * Class constructor
	 * @param startingCell the starting cell of the fool
	 */
	public Fool(Cell startingCell) {
		super("l'altruiste", startingCell);
		this.fakeHint = null;
	}

	/**
	 * Set the hint to give of the altruist if he doesn't have any
	 * @param fakeHint the fake hint to give
	 */
	public void setHint(FakeHint fakeHint) {
		if (this.fakeHint == null) {
			this.fakeHint = fakeHint;
			this.dialog = new GiveHintDialog(this.fakeHint);
		}
	}

	/**
     * Getter for attribute fakeHint
     * @return the value of attribute
     */
	public FakeHint getFakeHint(){
		return this.fakeHint;
	}
}
