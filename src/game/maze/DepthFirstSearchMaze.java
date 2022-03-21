package game.maze;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Class DepthFirstSearchMaze that represents a maze in the game, generated using the randomized depth-first search algorithm.
 * The algorithm is defined like this :
 * - start from a random cell
 * - then repeat this process :
 * - look around for the neighboring cells that are not yet visited, and choose and random cell among these
 * - open the wall between the cell and the neighbor
 * - add this cell to a stack to keep track of visited cells
 * - if you reach a cell during the process without unvisited neighboring cells, consider this a dead-end :
 * - go back in the path until reaching a cell with an unvisited neighbor
 * - then continue the process
 * - the generation is done when the stack is emptied, meaning there is no cell with unvisited neighbors left
 */
public class DepthFirstSearchMaze extends Maze {

	private Map<Cell, Boolean> cellsAlreadyVisited;

	/**
	 * DepthFirstSearchMaze class constructor
	 *
	 * @param length the length of the maze
	 * @param height the height of the maze
	 */
	public DepthFirstSearchMaze(int length, int height) {
		super(length, height);

		// Mark all cells unvisited
		this.cellsAlreadyVisited = new HashMap<Cell, Boolean>();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < height; j++) {
				cellsAlreadyVisited.put(this.getCell(i, j), false);
			}
		}

		this.generate();
	}

	/**
	 * Return the unvisited neighboring cells of a cell
	 *
	 * @param cell the cell whose neighbors we want
	 * @return the neighboring cells that are not visited
	 */
	private List<Cell> neighboringCells(Cell cell) {
		List<Cell> neighboring = new ArrayList<>();

		// Case for neighboring cell above
		if (!isExternalWall(cell, Orientation.NORTH)) {
			Cell cellFrontOf = this.getCell(cell.getX(), cell.getY() - 1);
			if (!cellsAlreadyVisited.get(cellFrontOf)) {
				neighboring.add(cellFrontOf);
			}
		}

		// Case for neighboring cell below
		if (!isExternalWall(cell, Orientation.SOUTH)) {
			Cell cellFrontOf = this.getCell(cell.getX(), cell.getY() + 1);
			if (!cellsAlreadyVisited.get(cellFrontOf)) {
				neighboring.add(cellFrontOf);
			}
		}

		// Case for neighboring cell to the left
		if (!isExternalWall(cell, Orientation.WEST)) {
			Cell cellFrontOf = this.getCell(cell.getX() - 1, cell.getY());
			if (!cellsAlreadyVisited.get(cellFrontOf)) {
				neighboring.add(cellFrontOf);
			}
		}

		// Case for neighboring cell to the right
		if (!isExternalWall(cell, Orientation.EAST)) {
			Cell cellFrontOf = this.getCell(cell.getX() + 1, cell.getY());
			if (!cellsAlreadyVisited.get(cellFrontOf)) {
				neighboring.add(cellFrontOf);
			}
		}

		return neighboring;
	}

	/**
	 * Generate the maze by following a path to open walls of unvisited cells using the Depth First Search algorithm
	 */
	protected void generate() {
		// Choose a random cell to start
		int x = new Random().nextInt(this.length);
		int y = new Random().nextInt(this.height);
		Cell currentCell = this.getCell(x, y);

		/*
		 * Create the stack for the maze generation.
		 * Stacks up the visited cells until reaching a dead-end, then unstack cells until finding a cell that has unvisited neighbors.
		 * When the stack is empty again, this means all cells have been visited.
		 */
		Stack<Cell> cellsInPath = new Stack<Cell>();

		// Repeat until all cells are visited (i.e. stack is empty)
		do {
			cellsAlreadyVisited.put(currentCell, true); // Mark the current cell visited
			List<Cell> neighboringCells = this.neighboringCells(currentCell); // Get neighboring cells

			if (!neighboringCells.isEmpty()) {
				cellsInPath.push(currentCell); // Stack the current cell
				Cell neighborCell = neighboringCells.get(new Random().nextInt(neighboringCells.size())); // Choose a random cell among neighboring cells

				// Remove the wall between the neighborCell and the current cell
				try {
					this.removeWall(currentCell, neighborCell);
				} catch (InvalidAdjacentCellException e) {
					System.out.println("Error : Trying to open wall between two non-adjacent cells");
				}

				// Update the current cell
				currentCell = neighborCell;
			} else {
				currentCell = cellsInPath.pop(); // Go back in the path to the last visited cell
			}
		} while (!cellsInPath.isEmpty());
	}
}
