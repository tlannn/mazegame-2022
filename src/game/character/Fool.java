package game.character;

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
		if (this.fakeHint == null)
			this.fakeHint = fakeHint;
	}

	public void talk(Player player) {
		super.talk(player);
		System.out.println("Vous êtes sur une quête ? Laissez-moi vous donner un indice :");
		System.out.println(this.fakeHint);
	}
}
