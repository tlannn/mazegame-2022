// MODIF il faut fermer le scan
package game.character;

import game.character.dialog.EnigmaDialog;
import game.enigma.*;
import game.hint.*;
import game.maze.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import game.hint.*;

import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

public class Sphinx extends NonPlayerCharacter {

	private Hint hint;
	private boolean hintGiven;
	private EnigmaManager enigmaManager;

	public Sphinx(Cell startingCell, EnigmaManager manager) {
		super("le sphinx", startingCell);

		this.hint = null;
		this.hintGiven = false;
		this.movable = false;
		this.enigmaManager = manager;

		this.dialog = new EnigmaDialog(this);
	}

	public boolean hasGivenHint() {
		return this.hintGiven;
	}

	public void markHintGiven() {
		this.hintGiven = true;
	}

	public void setHint(Hint hint) {
		if (this.hint == null)
			this.hint = hint;
	}

	public Hint getHint() {
		return this.hint;
	}

	public Enigma getEnigma() {
		return this.enigmaManager.getEnigma();
	}
}
