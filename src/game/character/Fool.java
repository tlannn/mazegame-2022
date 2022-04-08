package game.character;

import game.hint.FakeHint;
import game.maze.*;

public class Fool extends NonPlayerCharacter {
	private FakeHint fakeHint;

	public Fool(Cell startingCell) {
		super("Fou", startingCell);

		this.fakeHint = null;
	}

	public void setFakeHint(FakeHint fakeHint) {
		if (this.fakeHint == null)
			this.fakeHint = fakeHint;
	}

	public void talk() {
		System.out.println("Vous êtes sur une quête ? Laissez-moi vous donner un indice :");
		System.out.println(this.fakeHint);
	}
}
