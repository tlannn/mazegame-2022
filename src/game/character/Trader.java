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
	private int priceMultiplicator;

	/**
	 * Class constructor
	 */
	public Trader(Cell startingCell,int basePrice, int priceMultiplicator) {
		super("le marchand", startingCell);
		this.parchments = new ArrayList<>();
		this.parchmentCost = basePrice;
		this.dialog = new TraderDialog(this);
		this.priceMultiplicator = priceMultiplicator;
	}

	public void addParchment(Parchment parchment) {
		this.parchments.add(parchment);
	}

	public void removeParchment(Parchment parchment){
		this.parchments.remove(parchment);
	}

	public void increaseParchmentCost(int priceMultiplicator) {
		this.parchmentCost = this.parchmentCost * priceMultiplicator;
	}

	public int getpriceMultiplicator(){
		return this.priceMultiplicator;
	}

	public int getParchmentCost(){
		return this.parchmentCost;
	}

	public List<Parchment> getParchments(){
		return this.parchments;
	}

	public int getTotalGoldRequired(){
		int totalGoldRequired = 0;
		for(int i = 0; i<this.parchments.size();i++){
			totalGoldRequired += this.parchmentCost*(Math.pow(priceMultiplicator, i));
		}
		return totalGoldRequired;
	}
}
