package game.character;

import game.character.dialog.GiveHintDialog;
import game.hint.Hint;
import game.maze.*;
import game.observer.ObservableEvent;
import game.system.graphics.ConsoleGraphicsSystem;
import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

public class Altruist extends NonPlayerCharacter {
	private Hint hint;

	/**
	 * class constructor
	 * @param startingCell the start cell for the altruist
	 */
	public Altruist(Cell startingCell) {
		super("l'altruiste", startingCell);
		this.hint = null;
	}
	
	/**
	 * change the hint if the attribute is null
	 * @param hint
	 */
	public void setHint(Hint hint) {
		if (this.hint == null) {
			this.hint = hint;
			this.dialog = new GiveHintDialog(this.hint);
		}
	}
}
