package game.character;

import game.item.Parchment;
import game.maze.Cell;
import game.util.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Trader extends NonPlayerCharacter {
	private int parchmentCost;
	private List<Parchment> parchments;

	/**
	 * Class constructor
	 */
	public Trader(Cell startingCell) {
		super("Marchand", startingCell);

		this.parchments = new ArrayList<>();
		this.parchmentCost = 5;
	}

	public void talk() {
		// TODO
	}

	public void addParchment(Parchment parchment) {
		this.parchments.add(parchment);
	}

	private void increaseParchmentCost() {
		this.parchmentCost = this.parchmentCost * 2;
	}
}
