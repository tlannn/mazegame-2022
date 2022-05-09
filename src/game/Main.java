package game;

import game.character.Player;
import game.character.Trader;
import game.maze.Cell;

import java.lang.reflect.InvocationTargetException;

public class Main {
	public static void main(String[] args) {
		Game game = new Game(GameGraphicsMode.CONSOLE);
		game.init(); // Create the level and the player
		game.play(); // Start the game
	}
}
