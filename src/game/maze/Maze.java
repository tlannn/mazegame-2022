package game.maze;

//import game.util.InputReader;
import game.util.Random;

//import java.io.IOException;

/**
 * Abstract class Maze that represents a random maze in the game
 */
public abstract class Maze {

	protected int length;
	protected int height;
	protected int nbCells;
	protected Cell[][] cells;

	/**
	 * Maze class constructor
	 *
	 * @param length the length of the maze
	 * @param height the height of the maze
	 */
	public Maze(int length, int height) {
		this.length = length;
		this.height = height;
		this.nbCells = length * height;

		// Create all cells of the maze, each having its walls closed
		this.cells = new Cell[length][height];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < height; j++) {
				this.cells[i][j] = new Cell(i, j);
			}
		}
	}

	/**
	 * Return the cell of coordinate x and y
	 *
	 * @param x coordinate x
	 * @param y coordinate y
	 * @return the corresponding cell
	 */
	public Cell getCell(int x, int y) {
		return this.cells[x][y];
	}

	/**
	 * Return a random cell in the maze
	 *
	 * @return a random cell
	 */
	public Cell getRandomCell() {
		int randomX = Random.randInt(0, this.length-1);
		int randomY = Random.randInt(0, this.height-1);

		return this.getCell(randomX, randomY);
	}

	/**
	 * Generate the maze randomly
	 */
	protected abstract void generate();

	/**
	 * Remove the wall of a cell according to a given orientation. Since two adjacent cells share the same wall, the wall is removed from the two cells
	 *
	 * @param cell the cell whose wall we want to remove
	 * @param orientation the orientation of the wall relative to the cell
	 */
	public void removeWall(Cell cell, Orientation orientation) {
		switch (orientation) {
			case NORTH:
				if (!this.isExternalWall(cell, orientation)) {
					cell.setNorthWall(false);
					this.getCell(cell.getX(), cell.getY() - 1).setSouthWall(false);
				}
				break;
			case SOUTH:
				if (!this.isExternalWall(cell, orientation)) {
					cell.setSouthWall(false);
					this.getCell(cell.getX(), cell.getY() + 1).setNorthWall(false);
				}
				break;
			case EAST:
				if (!this.isExternalWall(cell, orientation)) {
					cell.setEastWall(false);
					this.getCell(cell.getX() + 1, cell.getY()).setWestWall(false);
				}
				break;
			case WEST:
				if (!this.isExternalWall(cell, orientation)) {
					cell.setWestWall(false);
					this.getCell(cell.getX() - 1, cell.getY()).setEastWall(false);
				}
				break;
		}
	}

	/**
	 * Remove the wall between cell and adjacentCell. Since the two cells share the same wall, the wall is removed from both cells
	 *
	 * @param cell the cell whose wall we want to remove
	 * @param adjacentCell the adjacent cell that share the wall
	 * @throws InvalidAdjacentCellException when adjacentCell is not next to cell
	 */
	public void removeWall(Cell cell, Cell adjacentCell) throws InvalidAdjacentCellException {
		Orientation orientation;

		if (adjacentCell.getX() == cell.getX() + 1 && adjacentCell.getY() == cell.getY()) // adjacentCell is on the right of cell
			orientation = Orientation.EAST;
		else if (adjacentCell.getX() == cell.getX() - 1 && adjacentCell.getY() == cell.getY()) // adjacentCell is on the left of cell
			orientation = Orientation.WEST;
		else if (adjacentCell.getY() == cell.getY() + 1 && adjacentCell.getX() == cell.getX()) // adjacentCell is under cell
			orientation = Orientation.SOUTH;
		else if (adjacentCell.getY() == cell.getY() - 1 && adjacentCell.getX() == cell.getX()) // adjacentCell is above cell
			orientation = Orientation.NORTH;
		else // adjacentCell is not adjacent to cell
			throw new InvalidAdjacentCellException();

		this.removeWall(cell, orientation);
	}

	/**
	 * Check if a wall is an external wall
	 *
	 * @param cell the cell where the wall is attached to
	 * @param orientation the orientation of the wall relative to the cell
	 * @return true if the wall is an external wall
	 */
	public boolean isExternalWall(Cell cell, Orientation orientation) {
		switch (orientation) {
			case NORTH:
				return cell.getY() == 0;
			case SOUTH:
				return cell.getY() == this.height - 1;
			case EAST:
				return cell.getX() == this.length - 1;
			case WEST:
				return cell.getX() == 0;
			default:
				return false;
		}
	}

	/**
	 * Return the number of cells of the maze
	 * @return the number of cells
	 */
	public int getNbCell() {
		return this.nbCells;
	}

	/**
	 * Return the length of the maze
	 * @return the length of the maze
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * Return the height of the maze
	 * @return the height of the maze
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Display the maze. Symbol + represents a corner, symbols - and | stands as a wall
	 */
	public String toString() {
		String res = "";

		// Draw the northernmost walls
		for (int j = 0; j < this.length; j++) {
			res += "+---";
		}

		res += "+\n"; // End line of horizontal walls

		// Draw for each line
		for (int i = 0; i < this.height; i++) {
			res += "|"; // Start the line of vertical walls

			// Draw the line of vertical walls
			for (int k = 0; k < this.length; k++) {
				if (this.isExternalWall(getCell(k, i), Orientation.EAST) || this.getCell(k, i).hasEastWall()) {
					res += "   |";
				} else {
					res += "    ";
				}
			}

			res += "\n"; // Go to next line

			// Draw the line of horizontal walls
			for (int j = 0; j < this.length; j++) {
				if (this.isExternalWall(getCell(j, i), Orientation.SOUTH) || this.getCell(j, i).hasSouthWall()) {
					res += "+---";
				} else {
					res += "+   ";
				}
			}

			res += "+\n"; // End the line horizontal wall
		}

		return res;
	}
}
