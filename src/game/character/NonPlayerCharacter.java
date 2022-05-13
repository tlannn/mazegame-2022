package game.character;

import game.Game;
import game.Level;
import game.character.dialog.DefaultDialog;
import game.character.dialog.Dialog;
import game.maze.*;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;
import game.util.Random;

import java.util.List;

import game.observer.ObservableEvent;


/**
 * Class NonPlayerCharacter that represents any character the player doesn't control,
 * but can interact with
 */
public abstract class NonPlayerCharacter extends Character {

	protected Dialog dialog;

	/**
	 * Class constructor
	 * @param name the name of the NPC
	 * @param startingCell the starting cell of the NPC
	 */
	public NonPlayerCharacter(String name, Cell startingCell) {
		super(name, startingCell);
		this.dialog = new DefaultDialog();
	}

	/**
	 * Start the dialog of the NPC and notify that it has been met
	 * @param player the player to interact with
	 */
	public void talk(Player player){
		this.notify(this,ObservableEvent.EVENT_MEET_CHARACTER);
		this.dialog.start(player);
	}

	@Override
	public void update(Level level) {
		// Make the NPC move if it is allowed to
		if (this.movable) {
			List<Character> charactersInCell = this.currentCell.getCharactersInCell();

			// If player is in cell, give a chance to the NPC to move
			if (!charactersInCell.contains(level.getPlayer()) || Random.randInt(0, 1) == 0) {
				// Choose a random direction
				List<Orientation> possibleOrientations = this.currentCell.possibleOrientations();
				int randomOrientation = Random.randInt(0, possibleOrientations.size() - 1);

				// Move the character
				level.move(this, possibleOrientations.get(randomOrientation));
			}
		}
	}
}
