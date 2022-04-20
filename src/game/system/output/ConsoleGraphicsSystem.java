package game.system.output;
import java.util.*;

import game.character.NonPlayerCharacter;
import game.item.Item;
import game.Level;
import game.character.Inventory;
import game.character.Player;
import game.maze.Cell;
import game.maze.Orientation;

/**
 * Provide a graphics system for a console application
 */
public class ConsoleGraphicsSystem implements GraphicsSystem {
	@Override
	public void displayText(String text) {
		System.out.println(text);
	}

	@Override
	public void displayError(String error) {
		System.out.println(error);
	}

	@Override
	public void displayGameTitle(String title) {}

	@Override
	public void displayGameStatus(Level level, Player player) {
		this.displayLevel(level);
		this.displayText(""); // Print an empty line

		this.displayText("Vous êtes situé sur la case " + player.getCurrentCell());

		int i=0;
		List<Item> items = player.getCurrentCell().getItemsInCell();
		List<NonPlayerCharacter> characters = player.getCurrentCell().getNonPlayerCharactersInCell();
		if (items.isEmpty() && characters.isEmpty()){
				this.displayText("Il n'y a rien sur cette case");
		}
		else{
				this.displayText("Sur cette case se trouve :");
				for (i=0; i<items.size(); i++){
						this.displayText("- "+items.get(i));
				}
				for(int j=i; j< characters.size(); j++){
						this.displayText("- "+characters.get(j));
				}
		}

		// if (player.getCurrentCell().getNonPlayerCharactersInCell().size() > 0) {
		// 	this.displayText("Sur cette case se trouve " + player.getCurrentCell().getNonPlayerCharactersInCell().size() + " personnages");
		// }
		// else {
		// 	this.displayText("Personne ne se trouve sur cette case");
		// }
		//
		// if (player.getCurrentCell().getItemsInCell().size() > 0) {
		// 	this.displayText("Sur cette case se trouve " + player.getCurrentCell().getItemsInCell().size() + " objets");
		// }
		// else {
		// 	this.displayText("Il n'y a pas d'objets sur cette case");
		// }
		//
		// this.displayText("\nDirections possibles : " + player.getCurrentCell().possibleOrientations().toString());
		// this.displayText(""); // Print an empty line
	}

	@Override
	public void displayLevel(Level level) {
		// Symbol + represents a corner, symbols - and | stand as a wall
		StringBuilder string = new StringBuilder();

		// Draw the northernmost walls
		string.append("+---".repeat(level.getMaze().getLength())).append("+\n");

		// Draw each line of cells
		for (int i = 0; i < level.getMaze().getHeight(); i++) {
			string.append("|"); // Start the line of vertical walls

			// Draw the line of vertical walls
			for (int k = 0; k < level.getMaze().getLength(); k++) {
				Cell currentCell = level.getMaze().getCell(k, i);
				String cellMarker;

				// Determine the marker to show in the cell
				if (level.getPlayer().getCurrentCell() == currentCell)
					cellMarker = "P";
				else
					//cellMarker = currentCell.isVisited() ? " " : "*";
					cellMarker = level.isCellVisited(currentCell) ? " " : "*";

				if (level.getMaze().isExternalWall(currentCell, Orientation.EAST) || currentCell.hasEastWall()) {
					string.append(" ").append(cellMarker).append(" |");
				} else {
					string.append(" ").append(cellMarker).append("  ");
				}
			}

			string.append("\n"); // Go to next line

			// Draw the line of horizontal walls
			for (int j = 0; j < level.getMaze().getLength(); j++) {
				Cell currentCell = level.getMaze().getCell(j, i);

				if (level.getMaze().isExternalWall(currentCell, Orientation.SOUTH) || currentCell.hasSouthWall()) {
					string.append("+---");
				} else {
					string.append("+   ");
				}
			}

			string.append("+\n"); // End the line horizontal wall
		}

		this.displayText(string.toString());
	}

	@Override
	public void displayInventory(Player player){
		String string = "";
		Inventory inventory = player.getInventory();

		for (int i = 0; i < inventory.getItems().size(); ++i) {
			string += i + " - " + inventory.getItem(i).toString() + "\n";
		}

		this.displayText(string);
	}

	@Override
	public void displayHelp() {
		this.displayText("Un petit peu d'aide ?");
		this.displayText("Z - Aller vers le nord");
		this.displayText("S - Aller vers le sud");
		this.displayText("Q - Aller vers l'est");
		this.displayText("D - Aller vers l'ouest");
		this.displayText("P - Parler à un personnage");
		this.displayText("R - Ramasser un objet");
		this.displayText("U - Utiliser un objet");
		this.displayText(""); // Print an empty line
	}
}
