package game.character;

import game.item.Parchment;
import game.maze.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
	 * Add a parchment to the list of parchments to sell
	 * @param parchment the parchment to add
	 */
	public void addParchment(Parchment parchment) {
		this.parchments.add(parchment);
	}

	/**
	 * Remove a parchment from the list of parchments to sell
	 * @param parchment the parchment to remove
	 * @throws NoSuchElementException when the parchment is not in the list "parchment"
	 */
	public void removeParchment(Parchment parchment) throws NoSuchElementException{
		if (!this.parchments.contains(parchment)){
			throw new NoSuchElementException("Parchemin inconnu");
		}
		this.parchments.remove(parchment);
	}

	/**
	 * Increase the cost of a parchment by the priceMultiplicator
	 */
	public void increaseParchmentCost() {
		this.parchmentCost = this.parchmentCost * this.priceMultiplicator;
	}

	/**
	 * Getter for the attribute priceMultiplicator
	 * @return the value of attribute
	 */
	public int getPriceMultiplicator(){
		return this.priceMultiplicator;
	}

	/**
	 * Getter for the attribute parchmentCost
	 * @return the value of attribute
	 */
	public int getParchmentCost(){
		return this.parchmentCost;
	}

	/**
	 * Getter for the attribute parchments
	 * @return the value of attribute
	 */
	public List<Parchment> getParchments(){
		return this.parchments;
	}

	/**
	 * Get the total gold required to buy all parchments
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
