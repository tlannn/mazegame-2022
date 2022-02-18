package game;

import game.maze.*;

public class Main {
	public static void main(String[] args) {
		Maze maze = new KruskalMaze(3, 3);
		System.out.println(maze);
	}
}
