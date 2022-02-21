package game;

import game.maze.*;

public class Main {
	public static void main(String[] args) {
		Maze kruskalMaze = new KruskalMaze(7, 4);
		System.out.println(kruskalMaze);

		Maze depthFirstSearchMaze = new DepthFirstSearchMaze(7, 4);
		System.out.println(depthFirstSearchMaze);
	}
}
