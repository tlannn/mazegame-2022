package game.character;

import game.item.Parchment;
import game.maze.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
	 * @param startingCell start cell of trader
	 * @param basePrice base price of parchment
	 * @param priceMultiplicator multiplicator for the second parchment and etc...
	 */
	public Trader(Cell startingCell,int basePrice, int priceMultiplicator) {
		super("le marchand", startingCell);
		this.parchments = new ArrayList<>();
		this.parchmentCost = basePrice;
		this.dialog = new TraderDialog(this);
		this.priceMultiplicator = priceMultiplicator;
	}

	/**
	 * add the parchment in the attribute list "parchment"
	 * @param parchment the new parchmùent to add
	 */
	public void addParchment(Parchment parchment) {
		this.parchments.add(parchment);
	}

	/**
	 * remove the parchment if it contains in attribute list "parchment"
	 * @param parchment the parchement to remove
	 * @throws NoSuchElementException when the parchment is not in the list "parchment"
	 */
	public void removeParchment(Parchment parchment) throws NoSuchElementException{
		if (!this.parchments.contains(parchment)){
			throw new NoSuchElementException("Parchemin Inconnu");
		}
		this.parchments.remove(parchment);
	}

	/**
	 * change the parchmentCost with priceMultiplicator
	 * @param priceMultiplicator multiplier 
	 */
	public void increaseParchmentCost(int priceMultiplicator) {
		this.parchmentCost = this.parchmentCost * priceMultiplicator;
	}

	/**
	 * getter for the attribute priceMultiplicator
	 * @return the value of attribute
	 */
	public int getpriceMultiplicator(){
		return this.priceMultiplicator;
	}

	/**
	 * getter for the attribute parchmentCost
	 * @return the value of attribute
	 */
	public int getParchmentCost(){
		return this.parchmentCost;
	}

	/**
	 * getter for the attribute parchments
	 * @return the value of attribute
	 */
	public List<Parchment> getParchments(){
		return this.parchments;
	}

	/**
	 * get the total gold required for buy all parchment
	 * @return the value
	 */
	public int getTotalGoldRequired(){
		int totalGoldRequired = 0;
		for(int i = 0; i<this.parchments.size();i++){
			totalGoldRequired += this.parchmentCost*(Math.pow(priceMultiplicator, i));
		}
		return totalGoldRequired;
	}
}
