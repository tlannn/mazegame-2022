package game;

import game.maze.*;

public class Main {
	public static void main(String[] args) {
	/*	Maze maze = new KruskalMaze(9, 9);
		System.out.println(maze);*/
		System.out.println("je commence");
		Maze mazeEmma = new DepthFirstSearchMaze(3,3);
		System.out.println(mazeEmma);
		System.out.println("j'ai fini");
	}
}
