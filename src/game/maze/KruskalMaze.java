package game.maze;

import java.util.List;
import java.util.ArrayList;
import game.util.Random;

/**
 * Class KruskalMaze that represents a maze in the game, generated using the randomized Kruskal's algorithm.
 * The algorithm is defined like this :
 * 	- choose a random wall to open
 * 	- compare the cells linked by the wall
 * 		- if these cells belong to the same set, the wall cannot be removed
 * 		- if these cells don't belong to the same set, remove the wall and make the cells belong to the same set
 * 	- the generation is done when all cells belong to a unique set
 */
public class KruskalMaze extends Maze {
	/**
	 * Class Node that stands as a cell in the maze, but also as a node in a tree (in the mathematical sense)
	 */
    private class Node {
		/* Instance attributes */
		private Node parent; // Parent node in the tree
		private int index; // Index of the associated cell in the maze
		private int rank; // Depth of the tree, but this no longer makes sense with the path compression in method find()

		/**
		 * Node class constructor
		 * @param index the index of the associated cell in the maze
		*/
		public Node(int index) {
			this.parent = this; // A node by default has for parent itself, meaning it is a root node in the tree
			this.index = index;
			this.rank = 0;
		}

		/**
		 * Getter for attribute `parent`
		 * @return the value of the attribute
		 */
		public Node getParent() {
			return this.parent;
		}

		/**
		 * Getter for attribute `index`
		 * @return the value of the attribute
		 */
		public int getIndex() {
			return this.index;
		}

		/**
		 * Getter for attribute `rank`
		 * @return the value of the attribute
		 */
		public int getRank() {
			return this.rank;
		}

		/**
		 * Setter for attribute `parent`
		 * @param parent the value to set to the attribute
		 */
		public void setParent(Node parent) {
			this.parent = parent;
		}

		/**
		 * Setter for attribute `rank`
		 * @param rank the value to set to the attribute
		 */
		public void setRank(int rank) {
			this.rank = rank;
		}

		/**
		 * Compare the current instance to an object to check whether they are equals or not
		 * @param object the object to compare
		 * @return true if the object is equal, false otherwise
		 */
		public boolean equals(Object object) {
			if (object instanceof Node) {
				Node node = (Node)object;
				return this.index == node.getIndex();
			}

			return false;
		}
	}

	/**
	 * KruskalMaze class constructor
	 * @param length the length of the maze
	 * @param height the height of the maze
	 */
	public KruskalMaze(int length, int height) {
		super(length, height);
    	this.generate();
	}

	/**
	 * Generate the maze by opening random walls using the Kruskal's algorithm
	 */
	protected void generate() {
		/*
		 * Create an array that will contain nodes (aka cells).
		 * See nodes as if each one belong to their own set. If nodes were to belong to the same set,
		 * it would mean they are connected together through an opened wall in the maze
		 */
		Node[] nodes = new Node[this.nbCells];

		// Create the sets (nodes) for the maze generation
		for (int i = 0; i < this.nbCells; ++i)
			nodes[i] = new Node(i);

		// Execute the algorithm to randomly open walls
		int nbUnion = 0;
		while (nbUnion < nodes.length - 1) { // Maze is done when all sets are united
			System.out.println("nbUnion : " + nbUnion);
			/*
			 * First step is to choose a wall to remove.
			 * We establish the cells that can be selected according to the orientation
			 * randomly chose, to prevent external walls being selected
			 */
			List<Integer> possibleIndex = new ArrayList<>();
			Orientation orientation = Random.randInt(0, 2) == 0 ? Orientation.SOUTH : Orientation.EAST;

			// Add all cells index except those whose oriented wall is the border of the maze
			for (int i = 0; i < this.nbCells; ++i) {
				if (!this.isExternalWall(this.getCellByNodeIndex(i), orientation)) {
					possibleIndex.add(i);
				}
			}

			if (!possibleIndex.isEmpty()) {
				// Choose a random cell index among the remaining cells
				int randIndex = possibleIndex.get(Random.randInt(0, possibleIndex.size() - 1));
				int adjacentIndex = orientation == Orientation.EAST ? randIndex + 1 : randIndex + this.length; // Depending on the orientation (south, east) the adjacent is whether to the right or below

				/*
				 * Next step is to unite the sets corresponding to the selected nodes
				 * If sets are united, the wall can be removed
				 */
				if (this.union(nodes[randIndex], nodes[adjacentIndex])) {
					// Remove the wall from the chosen cell and the adjacent cell, since they share the same wall
					this.removeWall(this.getCellByNodeIndex(randIndex), orientation);

					++nbUnion;
				}
			}
		}
	}

	/**
	 * Cells are stored by coordinates, and nodes by an index. This function returns the cell associated to a node
	 * @param index the index of the node
	 * @return the cell associated
	 */
	private Cell getCellByNodeIndex(int index) {
		int x = (int)(index % this.length);
		int y = (int)(index / this.length);
		return this.getCell(x, y);
	}

	/**
	 * Search the root of a node (the upmost node in the tree). To do this, find() is called recursively on the parent of the node until the root is found.
	 * The function is upgraded with the path compression which allows to flatten the tree faster : since each parent met recursively share the same root node,
	 * their parent is set to this root node
	 *
	 * @param node the node whose parent we want to have
	 * @return the root of the node
	 */
	private Node find(Node node) {
		Node parent = node.getParent();

		// The node isn't a root node
		if (parent != node) {
			// Search for the parent of the node's parent
			node.setParent(this.find(parent));
		}

		return node.getParent();
	}

	/**
	 * Unite two nodes. If they don't belong to the same tree, the smallest tree is attached to the biggest one. If they are already united, nothing happens
	 * @param x the first node to unite
	 * @param y the second node to unite
	 * @return true if nodes has been united, false otherwise
	 */
	private boolean union(Node x, Node y) {
		boolean unionOccurred = false;

		// Search the root node of each node
		Node xRoot = find(x);
		Node yRoot = find(y);

		// If nodes haven't the same root, they don't belong to the same tree
		if (xRoot != yRoot) {
			unionOccurred = true;

			if (xRoot.getRank() < yRoot.getRank()) // The tree containing node X is the smallest
				xRoot.setParent(yRoot);

			else { // The tree containing node Y is the smallest
				yRoot.setParent(xRoot);
				if (xRoot.getRank() == yRoot.getRank())
					xRoot.setRank(xRoot.getRank() + 1);
			}
		}

		return unionOccurred;
	}
}
