package game.character;

import game.character.dialog.GiveHintDialog;
import game.hint.fake.FakeHint;
import game.maze.*;

public class Fool extends NonPlayerCharacter {
	private FakeHint fakeHint;

	public Fool(Cell startingCell) {
		super("le fou", startingCell);
		this.fakeHint = null;
	}

	public void setHint(FakeHint fakeHint) {
		if (this.fakeHint == null) {
			this.fakeHint = fakeHint;
			this.dialog = new GiveHintDialog(this.fakeHint);
		}
	}

	public FakeHint getFakeHint(){
		return this.fakeHint;
	}
}
