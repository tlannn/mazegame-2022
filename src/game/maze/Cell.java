package game.maze;

/**a cell in the maze*/
public class Cell{
    private int x;
    private int y;
    private boolean northWall;
    private boolean southWall;
    private boolean eastWall;
    private List <Character> characters;


    public Cell(int x, int y){

    }

    /** tell if there is a north wall to the cell
    @return true if there is a north wall
    */
    public hasNorthWall(){
        return this.northWall;
    }

    /** tell if there is a south wall to the cell
    @return true if there is a south wall
    */
    public hasSouthWall(){
        return this.southWall;
    }

    /** tell if there is a east wall to the cell
    @return true if there is a east wall
    */
    public hasEastWall(){
        return this.eastWall;
    }

    /** tell if there is a west wall to the cell
    @return true if there is a west wall
    */
    public hasWestWall(){
        return this.westWall;
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
