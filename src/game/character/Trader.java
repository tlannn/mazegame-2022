package game.character;

import game.item.Parchment;
import game.maze.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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

	public void talk(Player player) {
		System.out.println("En échange de la modique somme de " + this.parchmentCost + "galons d'or, souhaitez-vous acquérir ce parchemin ?");
		Scanner scan= new Scanner(System.in);
		String text= scan.nextLine();
		if(text.equals("o") ){
			player.getInventory().addItem(parchments.get(0));
			this.removeParchment(parchments.get(0));
			this.increaseParchmentCost();
		}
		scan.close();
	}

	public void addParchment(Parchment parchment) {
		this.parchments.add(parchment);
	}

	private void removeParchment(Parchment parchment){
		this.parchments.remove(parchment);

	}

	private void increaseParchmentCost() {
		this.parchmentCost = this.parchmentCost * 2;
	}
}
