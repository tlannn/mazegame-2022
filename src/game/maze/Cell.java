package game.maze;

import java.util.ArrayList;

/**a cell in the maze*/
public class Cell{
    private int x;
    private int y;
    private boolean northWall;
    private boolean southWall;
    private boolean eastWall;
    private boolean westWall;
    private ArrayList <Character> characters;

    /*a cell must have its 4 walls */
    public Cell(int x, int y){
    	this.x=x;
    	this.y=y;
    	this.northWall=true;
    	this.southWall=true;
    	this.eastWall=true;
    	this.westWall=true;
    	this.characters= new ArrayList<Character>();
    }
    
    /**
     * return x which the coordonate at the cell
     * 
     * @return (int) x;
     */
    public int getX() {
		return x;
	}
    /**
     * return y which the coordonate at the cell
     * 
     * @return (int) y;
     */
	public int getY() {
		return y;
	}
	
    /** tell if there is a north wall to the cell
    @return true if there is a north wall
    */
    public boolean hasNorthWall(){
        return this.northWall;
    }

    /** tell if there is a south wall to the cell
    @return true if there is a south wall
    */
    public boolean hasSouthWall(){
        return this.southWall;
    }

    /** tell if there is a east wall to the cell
    @return true if there is a east wall
    */
    public boolean hasEastWall(){
        return this.eastWall;
    }

    /** tell if there is a west wall to the cell
    @return true if there is a west wall
    */
    public boolean hasWestWall(){
        return this.westWall;
    }
    
    /**can add or delete the northWall
     * 
     * @param: boolean true if there is a wall 
     * */
	public void setNorthWall(boolean northWall) {
		this.northWall = northWall;
	}

    /**can add or delete the southWall
     * 
     * @param: boolean true if there is a wall 
     * */
	public void setSouthWall(boolean southWall) {
		this.southWall = southWall;
	}
	
    /**can add or delete the eastWall
     * 
     * @param: boolean true if there is a wall 
     * */
	public void setEastWall(boolean eastWall) {
		this.eastWall = eastWall;
	}

    /**can add or delete the westWall
     * 
     * @param: boolean true if there is a wall 
     * */
	public void setWestWall(boolean westWall) {
		this.westWall = westWall;
	}
	
	/*return the list of character
	 * 
	 * @return character (ArrayList<character>) the list of character
	 */
	public ArrayList<Character> getCharacters() {
		return this.characters;
	}

	/*Add character at the list character
	 * 
	 * @param character (Character) : the character we want to add at the list.
	 */
	public void addCharacter(Character character) {
		this.characters.add(character);
	}
	
	/* Remove a character in the liste, 
	 * 
	 * @param character (Character): the Character we want to remove in the liste character
	 * 
	 *@exception notInListException: if the character we want to remove is not in the list
	 */
	public void removeCharacter(Character character) throws notInListException {
		try {
			this.characters.remove(character);
		}
		catch(Exception e) {
			throw new notInListException("Error "+character.toString()+" can't delete this character because it isn't in the list.");
		}
	}

	/*return true if the 2 cell have the same coordinate
	 * 
	 *@return (boolean) true if the 2 cell have the same coordinate 
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

	@Override
	/*
	 * display the coordonnate at the cellule
	 */
	public String toString() {
		return "Cell x="+x+" y="+y;
	}

}



/*
GAME
main.java
game.java
----MAZE
----maze.java
----cell.java
----PERSO
----character.java*/
