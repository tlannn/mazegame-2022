package game.character;

import game.enigma.FakeHint;
import game.maze.*;

public class Fool extends NonPlayerCharacter {
	private FakeHint fakeHint;

	public Fool(FakeHint fakeHint, Cell startingCell) {
		super("Fou", startingCell);

		this.fakeHint = fakeHint;
	}

	public void talk() {
		System.out.println("Vous êtes sur une quête ? Laissez-moi vous donner un indice :");
		System.out.println(this.fakeHint);
	}
}
