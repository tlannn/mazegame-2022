package game.character;

import game.character.dialog.GiveHintDialog;
import game.hint.Hint;
import game.maze.*;
import game.observer.ObservableEvent;
import game.system.graphics.ConsoleGraphicsSystem;
import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

/**
 * A NonPlayerCharacter called altruist who gives a hint for free to the player
 */
public class Altruist extends NonPlayerCharacter {
	private Hint hint;

	/**
	 * Class constructor
	 * @param startingCell the start cell for the altruist
	 */
	public Altruist(Cell startingCell) {
		super("l'altruiste", startingCell);
		this.hint = null;
	}
	
	/**
	 * Set the hint to give of the altruist if he doesn't have any
	 * @param hint the hint to give
	 */
	public void setHint(Hint hint) {
		if (this.hint == null) {
			this.hint = hint;
			this.dialog = new GiveHintDialog(this.hint);
		}
	}
}
