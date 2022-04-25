package game.character;

import game.Level;
import game.character.dialog.Dialog;
import game.maze.*;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;
import game.util.Random;

import java.util.List;

/**
 * Class NonPlayerCharacter that represents any character the player doesn't control,
 * but can interact with
 */
public abstract class NonPlayerCharacter extends Character {

	protected Dialog dialog;

	/**
	 * Class constructor
	 */
	public NonPlayerCharacter(String name, Cell startingCell) {
		super(name, startingCell);
	}

	public abstract void talk(GraphicsSystem graphicsSystem, InputSystem inputSystem, Player player);

	public void talkTo(Player player) {
		dialog.start(player);
	}

	@Override
	public void update(Level level, InputSystem inputSystem, GraphicsSystem graphicsSystem) {
		// Make the NPC move if it is allowed to
		if (this.movable) {
			List<Character> charactersInCell = this.currentCell.getCharactersInCell();

			// If the player isn't in the same cell, move the character
			if (!charactersInCell.contains(level.getPlayer())) {
				// Choose a random direction
				List<Orientation> possibleOrientations = this.currentCell.possibleOrientations();
				int randomOrientation = Random.randInt(0, possibleOrientations.size() - 1);

				// Move the character
				level.move(this, possibleOrientations.get(randomOrientation));
			}
		}
	}
}
