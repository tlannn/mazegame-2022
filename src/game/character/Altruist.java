package game.character;

import game.enigma.Hint;
import game.maze.*;

public class Altruist extends NonPlayerCharacter {
	private Hint hint;

	public Altruist(Hint hint, Cell startingCell) {
		super("Altruiste", startingCell);

		this.hint = hint;
	}

	public void talk() {
		System.out.println("Vous êtes sur une quête ? Laissez-moi vous donner un indice :");
		System.out.println(this.hint);
	}
}
