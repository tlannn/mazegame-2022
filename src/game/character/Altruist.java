package game.character;

import game.hint.Hint;
import game.maze.*;

import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

public class Altruist extends NonPlayerCharacter {
	private Hint hint;

	public Altruist(Cell startingCell) {
		super("Altruiste", startingCell);
		this.hint = null;
	}

	public void setHint(Hint hint) {
		if (this.hint == null)
			this.hint = hint;
	}

	public void talk(GraphicsSystem graphicsSystem, InputSystem inputSystem, Player player) {
		System.out.println("Vous êtes sur une quête ? Laissez-moi vous donner un indice :");
		System.out.println(this.hint);
	}
}
