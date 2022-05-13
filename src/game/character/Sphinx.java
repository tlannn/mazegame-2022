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

	/**
	 * Class constructor
	 * @param startingCell start cell of the sphinx
	 * @param manager enigmas of the sphinx with EnigmaManager class
	 */
	public Sphinx(Cell startingCell, EnigmaManager manager) {
		super("le sphinx", startingCell);

		this.hint = null;
		this.hintGiven = false;
		this.movable = false;
		this.enigmaManager = manager;

		this.dialog = new EnigmaDialog(this);
	}

	/**
	 * Returns true if the sphinx gave its hint. Otherwise false
	 * @return true if the sphinx gave a hint
	 */
	public boolean hasGivenHint() {
		return this.hintGiven;
	}

	/**
	 * put the attribute hint at true
	 */
	public void markHintGiven() {
		this.hintGiven = true;
	}

	/**
	 * Set the hint to give of the altruist if he doesn't have any
	 * @param hint the hint to give
	 */
	public void setHint(Hint hint) {
		if (this.hint == null)
			this.hint = hint;
	}

	/**
	 * Getter of attribute hint
	 * @return the value of attribute
	 */
	public Hint getHint() {
		return this.hint;
	}

	/**
	 * Getter for an enigma in enigmaManager
	 * @return an enigma given by the enigma manager
	 */
	public Enigma getEnigma() {
		return this.enigmaManager.getEnigma();
	}
}
