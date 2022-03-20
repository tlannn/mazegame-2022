package game.character;

import game.enigma.Hint;
import game.maze.*;

public class Altruist extends NonPlayerCharacter {
	private Hint hint;

	public Altruist(Hint hint, Cell startingCell) {
		super("Altruist", startingCell);

		this.hint = hint;
	}

	public void talk() {
		System.out.println("You are on a quest ? Let me give you a hint :");
		System.out.println(this.hint);
	}
}
