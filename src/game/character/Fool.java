package game.character;

import game.character.dialog.DefaultDialog;
import game.character.dialog.GiveHintDialog;
import game.hint.FakeHint;
import game.maze.*;

import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

public class Fool extends NonPlayerCharacter {
	private FakeHint fakeHint;

	public Fool(Cell startingCell) {
		super("Fou", startingCell);
		this.fakeHint = null;
	}

	public void setHint(FakeHint fakeHint) {
		if (this.fakeHint == null) {
			this.fakeHint = fakeHint;
			this.dialog = new GiveHintDialog(this.fakeHint);
		}
	}
}
