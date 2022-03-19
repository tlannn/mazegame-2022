package game.character;

import game.enigma.Hint;

public class Altruist extends NonPlayerCharacter {
	private Hint hint;

	public Altruist(Hint hint, Maze maze) {
		super("Altruist",maze);

		this.hint = hint;
	}

	public void talk() {
		System.out.println("You are on a quest ? Let me give you a hint :");
		System.out.println(this.hint);
	}
}
