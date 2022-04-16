package game;

import game.maze.*;
import game.character.*;


public class Main {
	public static void main(String[] args) {
		Maze kruskalMaze = new KruskalMaze(7, 4);
		// // System.out.println(kruskalMaze);
		//
		// // Maze depthFirstSearchMaze = new DepthFirstSearchMaze(7, 4);
		// // System.out.println(depthFirstSearchMaze);
		//
		// Player player = new Player("Emma",kruskalMaze.getCell(0,0));
		// //il faut mettre dans la case (0,0) l'attribut player
		// Game game = new Game (kruskalMaze, player);
		// game.playTurn(player, kruskalMaze);
		// game.playTurn(player, kruskalMaze);
		// game.playTurn(player, kruskalMaze);

		GameGenerator gameGenerator = new GameGenerator();

	}

}
