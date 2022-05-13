package game.character;

import game.character.dialog.GiveHintDialog;
import game.hint.fake.FakeHint;
import game.maze.*;

public class Fool extends NonPlayerCharacter {
	private FakeHint fakeHint;

	/**
	 * class constructor
	 * @param startingCell
	 */
	public Fool(Cell startingCell) {
		super("le fou", startingCell);
		this.fakeHint = null;
	}

	/**
	 * change fakeHint if the attribute is null
	 * @param fakeHint the new fakeHint
	 */
	public void setHint(FakeHint fakeHint) {
		if (this.fakeHint == null) {
			this.fakeHint = fakeHint;
			this.dialog = new GiveHintDialog(this.fakeHint);
		}
	}

	/**
     * getter for attribute fakeHint
     * @return the value of attribute
     */
	public FakeHint getFakeHint(){
		return this.fakeHint;
	}
}
