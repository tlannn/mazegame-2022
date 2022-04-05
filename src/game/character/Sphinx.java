package game.character;

import game.enigma.*;
import game.maze.*;

import java.util.ArrayList;
import java.util.List;

public class Sphinx extends NonPlayerCharacter {

	private Hint hint;
	private List<Enigma> enigmas;
	private int indexCurrentEnigma;
	private boolean hasGivenHint;

	public Sphinx(Cell startingCell) {
		super("Sphinx", startingCell);

		this.hint = null;
		this.enigmas = new ArrayList<>();
		this.indexCurrentEnigma = 0;
		this.hasGivenHint = false;
		this.movable = false;
	}

	public void setHint(Hint hint) {
		if (this.hint == null)
			this.hint = hint;
	}

	public void addEnigma(Enigma enigma) {
		this.enigmas.add(enigma);
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
