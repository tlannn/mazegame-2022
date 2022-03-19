package game.character;

import game.enigma.*;

public class Sphinx extends NonPlayerCharacter {

	private Hint hint;
	private Enigma[] enigmas;
	private int indexCurrentEnigma;
	private boolean hasGivenHint;

	public Sphinx(Hint hint, Enigma[] enigmas, Maze maze) {
		super("Sphinx", maze);

		this.hint = hint;
		this.enigmas = enigmas;
		this.indexCurrentEnigma = 0;
		this.hasGivenHint = false;
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
