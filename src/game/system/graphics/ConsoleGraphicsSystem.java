package game.system.graphics;
import java.util.*;

import game.character.NonPlayerCharacter;
import game.item.Item;
import game.Level;
import game.character.Inventory;
import game.character.Player;
import game.maze.Cell;
import game.maze.Orientation;
import game.system.SpeechPauseSystem;

/**
 * Provide a graphics system for a console application
 */
public class ConsoleGraphicsSystem implements GraphicsSystem {
	@Override
	public void displayText(String text, boolean instantly) {
		SpeechPauseSystem.say(text, instantly);
	}

	@Override
	public void displayText(String text) {
		displayText(text, false);
	}

	@Override
	public void displayError(String error) {
		System.out.println(error);
	}

	@Override
	public <T> void displayList(List<T> list, boolean withLetterIndex) {
		char letter = 'A';

		for (T element : list) {
			if (withLetterIndex)
				displayText(letter + " - " + element.toString());
			else
				displayText("- " + element.toString());

			++letter;
		}
	}

	@Override
	public void displayHint(Level level){
		if(level.getHints().size()==0){
			displayText("Désolée mais tu n'as pas encore trouvé d'indice.");
		}
		else{
			displayText("Voici les indices que tu as découvert, ils te permetront de remplir la quête et gagner le jeu.");
			displayList(level.getHints(), false);
		}
	}

	@Override
	public void displayGameTitle() {
		// displayText("████████╗██╗  ██╗███████╗    ██████╗ ██╗      ██████╗ ███╗   ██╗██████╗     ███╗   ███╗ █████╗ ███████╗███████╗", true);
		// displayText("╚══██╔══╝██║  ██║██╔════╝    ██╔══██╗██║     ██╔═══██╗████╗  ██║██╔══██╗    ████╗ ████║██╔══██╗╚══███╔╝██╔════╝", true);
		// displayText("   ██║   ███████║█████╗      ██████╔╝██║     ██║   ██║██╔██╗ ██║██║  ██║    ██╔████╔██║███████║  ███╔╝ █████╗", true);
		// displayText("   ██║   ██╔══██║██╔══╝      ██╔══██╗██║     ██║   ██║██║╚██╗██║██║  ██║    ██║╚██╔╝██║██╔══██║ ███╔╝  ██╔══╝", true);
		// displayText("   ██║   ██║  ██║███████╗    ██████╔╝███████╗╚██████╔╝██║ ╚████║██████╔╝    ██║ ╚═╝ ██║██║  ██║███████╗███████╗", true);
		// displayText("   ╚═╝   ╚═╝  ╚═╝╚══════╝    ╚═════╝ ╚══════╝ ╚═════╝ ╚═╝  ╚═══╝╚═════╝     ╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝╚══════╝", true);

	}

	@Override
	public void displayGameStatus(Level level, Player player) {
		this.displayLevel(level);
		this.displayText(""); // Print an empty line
		this.displayGold(player);

		this.displayText("Tu es situé sur la case " + player.getCurrentCell(), true);

		int i=0;
		List<Item> items = player.getCurrentCell().getItemsInCell();
		List<NonPlayerCharacter> characters = player.getCurrentCell().getNonPlayerCharactersInCell();
		if (items.isEmpty() && characters.isEmpty()){
				this.displayText("Il n'y a rien sur cette case\n", true);
		}
		else{
				this.displayText("Sur cette case se trouve :", true);
				for (i=0; i<items.size(); i++){
						this.displayText("- "+items.get(i), true);
				}
				for(int j=0; j< characters.size(); j++){
						this.displayText("- "+characters.get(j), true);
				}
		}
	}

	@Override
	public void displayLevel(Level level) {
		// Symbol + represents a corner, symbols - and | stand as a wall
		StringBuilder string = new StringBuilder();

		System.out.println("\n--------------------------------------------------------");
		System.out.println("--------------------------------------------------------\n");

		string.append("   ");

		for (int i = 0; i < level.getMaze().getLength(); ++i)
			string.append("  ").append(i).append(" ");

		// Draw the northernmost walls
		string.append("\n   ").append("+---".repeat(level.getMaze().getLength())).append("+\n");

		// Draw each line of cells
		for (int i = 0; i < level.getMaze().getHeight(); i++) {
			string.append(" ").append(i).append(" ").append("|"); // Start the line of vertical walls

			// Draw the line of vertical walls
			for (int k = 0; k < level.getMaze().getLength(); k++) {
				Cell currentCell = level.getMaze().getCell(k, i);
				String cellMarker;

				// Determine the marker to show in the cell
				if (level.getPlayer().getCurrentCell() == currentCell)
                    cellMarker = "P";
					//cellMarker = "☻";
				else
					//cellMarker = currentCell.isVisited() ? " " : "*";
					cellMarker = level.isCellVisited(currentCell) ? " " : "*";

				if (level.getMaze().isExternalWall(currentCell, Orientation.EAST)) {
					string.append(" ").append(cellMarker).append(" |");//.append(" " + i);
				}
				else if(currentCell.hasEastWall()) {
					string.append(" ").append(cellMarker).append(" |");
				} else {
					string.append(" ").append(cellMarker).append("  ");
				}
			}

			string.append("\n"); // Go to next line

			// Draw the line of horizontal walls
			for (int j = 0; j < level.getMaze().getLength(); j++) {
				Cell currentCell = level.getMaze().getCell(j, i);

				if (j == 0)
					string.append("   ");

				if (level.getMaze().isExternalWall(currentCell, Orientation.SOUTH) || currentCell.hasSouthWall()) {
					string.append("+---");
				} else {
					string.append("+   ");
				}
			}

			string.append("+\n"); // End the line horizontal wall
		}

		this.displayText(string.toString(), true);
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

	public void displayGold(Player player) {
		if (player.getGold() == 0)
			this.displayText("Tu n'as pas de galons d'or.", true);
		else
			this.displayText("Tu as " + player.getGold() + " galons d'or.", true);
	}

	@Override
	public void displayHelp() {
		StringBuilder help = new StringBuilder();
		help.append("Un petit peu d'aide ?\n")
		.append("Z - Aller vers le nord\n")
		.append("S - Aller vers le sud\n")
		.append("Q - Aller vers l'ouest\n")
		.append("D - Aller vers l'est\n")
		.append("P - Parler à un personnage\n")
		.append("R - Ramasser un objet\n")
		.append("I - Ouvrir l'inventaire\n")
		.append("V - Voir les indices obtenus\n")
		.append("\n"); // Print an empty line

		this.displayText(help.toString(), true);
	}
}
