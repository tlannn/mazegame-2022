package game.character;

import game.item.Parchment;
import game.util.InputReader;

public class Trader extends NonPlayerCharacter {
	private int parchmentCost;
	private Parchment[] parchments;

	/**
	 * Class constructor
	 */
	public Trader(Parchment[] parchments, Maze maze) {
		super("Trader", maze);

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
