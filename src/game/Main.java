package game;

import game.maze.*;

public class Main {
	public static void main(String[] args) {
		Maze maze = new KruskalMaze(7, 5);
		System.out.println(maze);
	}
}
