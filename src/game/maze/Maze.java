package game.maze;

public abstract class Maze{

    /**
     * this Maze are a array of lenght "length" and height "height" with a number of cells
     */
    protected int length;
    protected int height;
    protected Cell[][] cells;
    protected int nbCells;


    /**
     * Make Maze, the cells have all the walls
     *
     * @param length length of this Maze
     * @param height height of this Maze
     */
    public Maze(int length, int height){
        this.length = length;
        this.height = height;
        this.nbCells = length * height;
        this.cells = new Cell[length][height];
        for(int i = 0; i < length; i++ ){
            for(int j = 0; j < height; j++){
                this.cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Return the Cell of coodinate x and y
     *
     * @param x coordinate x
     * @param y coordinate y
     * @return this Cell
     */
    public Cell getCell(int x, int y){
        return this.cells[x][y];
    }

    protected abstract void generate();

    /**
     * Delete the wall of the cell with orientation
     *
     * @param cell the cell with the wall at deleted
     * @param orientation orientation of this wall at deleted
     */
    public void removeWall(Cell cell, WallOrientation orientation){
        switch(orientation){
            case NORTH:
                if (!this.isExternalWall(cell,orientation)){
                    cell.setNorthWall(false);
                    this.getCell(cell.getX(), cell.getY() - 1).setSouthWall(false);
                }
                break;
            case SOUTH:
                if (!this.isExternalWall(cell,orientation)){
                    cell.setSouthWall(false);
                    this.getCell(cell.getX(), cell.getY() + 1).setNorthWall(false);
                }
                break;
            case EAST:
                if (!this.isExternalWall(cell,orientation)){
                    cell.setEastWall(false);
                    this.getCell(cell.getX() + 1, cell.getY()).setWestWall(false);
                }
                break;
            case WEST:
                if (!this.isExternalWall(cell,orientation)){
                    cell.setWestWall(false);
                    this.getCell(cell.getX() - 1, cell.getY()).setEastWall(false);
                }
                break;
        }
    }

    /**
     * Delete the wall between cell and adjacentCell
     *
     * @param cell cell with the wall at deleted
     * @param adjacentCell the cell which share the wall at deleted
     */
    public void removeWall(Cell cell, Cell adjacentCell) {
        WallOrientation orientation;

        if (adjacentCell.getX() == cell.getX() + 1 && adjacentCell.getY() == cell.getY()) // adjacentCell is on the right of cell
            orientation = WallOrientation.EAST;
        else if (adjacentCell.getX() == cell.getX() - 1 && adjacentCell.getY() == cell.getY()) // adjacentCell is on the left of cell
            orientation = WallOrientation.WEST;
        else if (adjacentCell.getY() == cell.getY() + 1 && adjacentCell.getX() == cell.getX()) // adjacentCell is under cell
            orientation = WallOrientation.SOUTH;
        else if (adjacentCell.getY() == cell.getY() - 1 && adjacentCell.getX() == cell.getX()) // adjacentCell is above cell
            orientation = WallOrientation.NORTH;
        else // adjacentCell is not adjacent to cell, do nothing
            return;

        this.removeWall(cell, orientation);
    }

    /**
     * Return true if the wall is an external wall false if else
     *
     * @param cell Cell to test
     * @param orientation orientation to test
     * @return true if is extternal wall
     */
    public boolean isExternalWall(Cell cell, WallOrientation orientation){
        switch(orientation){
            case NORTH:
                return cell.getY()==0;
            case SOUTH:
                return cell.getY()==this.height-1;
            case EAST:
                return cell.getX()==this.length-1;
            case WEST:
                return cell.getX()==0;
            default:
                return false;
        }
    }

    /**
     * Return the number of cell of this Maze
     */
    public int getNbCell(){
        return this.nbCells;
    }

    /**
     * Display this Maze with type +, - and | for a wall
     */
    public String toString(){
        String res = "";

        // Draw the northernmost walls
        for (int j = 0; j < this.length; j++) {
            res += "+---";
        }

        res += "+\n"; // End line of horizontal walls

        // Draw for each line
        for(int i = 0 ;i < this.height; i++){
            res += "|"; // Start the line of vertical walls

            // Draw the line of vertical walls
            for (int k = 0; k < this.length; k++){
                if (this.isExternalWall(getCell(k, i),WallOrientation.EAST) || this.getCell(k, i).hasEastWall()) {
                    res += "   |";
                }
                else{
                    res += "    ";
                }
            }

            res += "\n"; // Go to next line

            // Draw the line of horizontal walls
            for (int j = 0; j < this.length; j++) {
                if (this.isExternalWall(getCell(j, i), WallOrientation.SOUTH) || this.getCell(j, i).hasSouthWall()){
                    res += "+---";
                }
                else{
                    res += "+   ";
                }
            }

            res += "+\n"; // End the line horizontal wall
        }

        return res;
    }

    public int getLength(){
        return this.length;
    }

    public int getHeight(){
        return this.height;
    }
}
