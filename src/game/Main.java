package game;

import game.character.Player;

public class Main {
	public static void main(String[] args) {
		Player player = new Player("Emma");

		Game game = new Game(player, GameGraphicsMode.CONSOLE);
		game.play(); // Start the game
	}
}
