package game.maze;

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
		private int rank; // Depth of the tree (number of children), but this no longer makes sense with the path compression in method find()

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
			this.parent = parent;
		}

		/**
		 * Getter for attribute `index`
		 * @return the value of the attribute
		 */
		public int getIndex() {
			this.index = index;
		}

		/**
		 * Getter for attribute `rank`
		 * @return the value of the attribute
		 */
		public int getRank() {
			this.rank = rank;
		}

		/**
		 * Setter for attribute `parent`
		 * @param parent the value to set to the attribute
		 */
		public void setParent(Node parent) {
			this.parent = parent;
		}

		/**
		 * Setter for attribute `index`
		 * @param index the value to set to the attribute
		 */
		public void setIndex(int index) {
			this.index = index;
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
	}

	/**
	 * Generate the maze by opening random walls using the Kruskal's algorithm
	 */
	public void generate() {}

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
		boolean unionOccured = false;

		// Search the root node of each nodes
		Node xRoot = find(x);
		Node yRoot = find(y);

		// If nodes haven't the same root, they don't belong to the same tree
		if (xRoot != yRoot) {
			unionOccured = true;

			if (xRoot.getRank() < yRoot.getRank()) // The tree containing node X is the smallest
				xRoot.setParent(yRoot);

			else { // The tree containing node Y is the smallest
				yRoot.setParent(xRoot);
				if (xRoot.getRank() == yRoot.getRank())
					xRoot.setRank(xRoot.getRank() + 1);
			}
		}

		return unionOccured;
	}
}
