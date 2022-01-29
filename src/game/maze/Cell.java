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
