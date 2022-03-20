package game.character;

import game.enigma.FakeHint;
import game.maze.*;

public class Fool extends NonPlayerCharacter {
	private FakeHint fakeHint;

	public Fool(FakeHint fakeHint, Cell startingCell) {
		super("Fool", startingCell);

		this.fakeHint = fakeHint;
	}

	public void talk() {
		System.out.println("You are on a quest ? Let me give you a hint :");
		System.out.println(this.fakeHint);
	}
}
