package game;

import game.maze.*;

public class Main {
	public static void main(String[] args) {
		// Check arguments
		if (args.length == 0 || Integer.parseInt(args[0]) < 1) {
			System.out.println("""
					Paramètres attendus: <id algorithme>
					
					id algorithme:
					- 1 -> Depth First Search algorithm
					- 2 -> Kruskal Search algorithm""");
		}

		else {
			// Get the maze algorithm according to the argument
			MazeAlgorithm algorithm = MazeAlgorithm.values()[Integer.parseInt(args[0]) - 1];

			Game game = new Game(GameGraphicsMode.CONSOLE);
			game.init(algorithm); // Create the level and the player
			game.play(); // Start the game
		}
	}
}
