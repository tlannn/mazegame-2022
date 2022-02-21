package game;

import game.maze.*;

public class Main {
	public static void main(String[] args) {
		Maze maze = new KruskalMaze(9, 9);
		System.out.println(maze);
		Maze mazeEmma = new DepthFirstSearchMaze(9,9);
		System.out.println(mazeEmma);
	}
}
