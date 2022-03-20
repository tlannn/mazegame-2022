package game;

import game.character.Character;
import game.maze.*;
import game.character.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private Maze maze;
	private Player player;
	private List<Character> characters;

	public Game() {
		this.maze = new KruskalMaze(5, 5);
		this.player = new Player("Player");
		this.characters = new ArrayList<>();
	}

	public void play() {
		boolean isGameFinished = false;

		while (!isGameFinished) {
			for (Character character : this.characters)
				this.playNPCTurn(character);

			isGameFinished = this.playPlayerTurn(this.player);
		}
	}

	public boolean playNPCTurn(Character character) {
		Cell cell = chooseCell();
		character.move(cell);
	}

	public boolean playPlayerTurn(Character character) {
		Cell cell = askCell();
		character.move(cell);
	}
}
