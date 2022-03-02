package game.maze;

/**
 * Represents a cell in the maze with 4 walls in each direction. The walls are closed when the cell is created.
 */
public class Cell {
    private int x; // Abscissa
    private int y; // Ordinate
    private boolean northWall;
    private boolean southWall;
    private boolean eastWall;
    private boolean westWall;
	private List<Item> theItems;
	private List<Character> theCharacters;

	/**
	 * Class constructor
	 *
	 * @param x the abscissa of the cell in the maze
	 * @param y the ordinate of the cell in the maze
	 */
	public Cell(int x, int y){
    	this.x=x;
    	this.y=y;
    	this.northWall=true;
    	this.southWall=true;
    	this.eastWall=true;
    	this.westWall=true;
    }

    /**
     * Return the abscissa of the cell
     * @return (int) x;
     */
    public int getX() {
		return x;
	}

    /**
     * Return the ordinate of the cell
     * @return (int) y;
     */
	public int getY() {
		return y;
	}

    /**
	 * Tell if there is a north wall to the cell
     * @return true if there is a north wall
     */
    public boolean hasNorthWall(){
        return this.northWall;
    }

    /**
	 * Tell if there is a south wall to the cell
     * @return true if there is a south wall
     */
    public boolean hasSouthWall(){
        return this.southWall;
    }

    /**
	 * Tell if there is an east wall to the cell
     * @return true if there is an east wall
     */
    public boolean hasEastWall(){
        return this.eastWall;
    }

    /**
	 * Tell if there is a west wall to the cell
     * @return true if there is a west wall
     */
    public boolean hasWestWall(){
        return this.westWall;
    }

    /**
	 * Change the status of the north wall (opened or closed)
     * @param northWall the status of the north wall
     */
	public void setNorthWall(boolean northWall) {
		this.northWall = northWall;
	}

	/**
	 * Change the status of the south wall (opened or closed)
	 * @param southWall the status of the south wall
	 */
	public void setSouthWall(boolean southWall) {
		this.southWall = southWall;
	}

	/**
	 * Change the status of the east wall (opened or closed)
	 * @param eastWall the status of the east wall
	 */
	public void setEastWall(boolean eastWall) {
		this.eastWall = eastWall;
	}

	/**
	 * Change the status of the west wall (opened or closed)
	 * @param westWall the status of the west wall
	 */
	public void setWestWall(boolean westWall) {
		this.westWall = westWall;
	}

	public List<Item> getItems(){
		return this.theItems;
	}

	public List<Character> getCharacters(){
		return this.theCharacters;
	}

	public void addItem(Item item){
		this.theItems.add(item);
	}

	public void addCharacter(Character character){
		this.theItems.add(character);
	}


	/**
	 * Test the equality with another cell. Cells are the same if they have the same coordinates
	 * @return true if the 2 cells have the same coordinates
	 */
	public boolean equals (Object object) {
		if (object instanceof Cell) {
			Cell cell= (Cell) object;
			if (this.x == cell.getX() && this.y==cell.getY()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * Returns a string representation of the cell with its coordinates
	 */
	public String toString() {
        return "("+x+","+y+")";
	}
}
