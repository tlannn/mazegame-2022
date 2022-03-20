package game.character;

import game.enigma.*;
import game.maze.*;

public class Sphinx extends NonPlayerCharacter {

	private Hint hint;
	private Enigma[] enigmas;
	private int indexCurrentEnigma;
	private boolean hasGivenHint;

	public Sphinx(Hint hint, Enigma[] enigmas, Cell startingCell) {
		super("Sphinx", startingCell);

		this.hint = hint;
		this.enigmas = enigmas;
		this.indexCurrentEnigma = 0;
		this.hasGivenHint = false;
		this.movable = false;
	}

	public void talk() {
		if (!this.hasGivenHint) {
			// TODO
		}

		else {
			System.out.println("Je vous ai déjà donné mon indice.");
			System.out.println(this.hint);
		}
	}
}
