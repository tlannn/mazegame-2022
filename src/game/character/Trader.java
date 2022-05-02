package game.character;

import game.item.Parchment;
import game.maze.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;
import game.character.dialog.TraderDialog;



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
		this.dialog = new TraderDialog(this);
	}

	public void addParchment(Parchment parchment) {
		this.parchments.add(parchment);
	}

	public void removeParchment(Parchment parchment){
		this.parchments.remove(parchment);
	}

	public void increaseParchmentCost() {
		this.parchmentCost = this.parchmentCost * 2;
	}

	public int getParchmentCost(){
		return this.parchmentCost;
	}

	public List<Parchment> getParchments(){
		return this.parchments;
	}
}
