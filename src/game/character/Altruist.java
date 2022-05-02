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

	public Altruist(Cell startingCell) {
		super("Altruiste", startingCell);
		this.hint = null;
		this.dialog = new GiveHintDialog(this.hint);
	}

	public void setHint(Hint hint) {
		if (this.hint == null) {
			this.hint = hint;
			this.dialog = new GiveHintDialog(this.hint);
		}
	}

	public void talk(Player player) {
		super.talk(player);
		this.dialog.start(player);
	}
}
