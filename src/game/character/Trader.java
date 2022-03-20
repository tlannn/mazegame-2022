package game.character;

import game.item.Parchment;
import game.maze.Cell;
import game.util.InputReader;

public class Trader extends NonPlayerCharacter {
	private int parchmentCost;
	private Parchment[] parchments;

	/**
	 * Class constructor
	 */
	public Trader(Parchment[] parchments, Cell startingCell) {
		super("Marchand", startingCell);

		this.parchments = parchments;
		this.parchmentCost = 5;
	}

	public void talk() {
		// TODO
	}

	private void increaseParchmentCost() {
		this.parchmentCost = this.parchmentCost * 2;
	}
}
