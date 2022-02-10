package game.maze;

public abstract class Maze{
    
    protected int length;
    protected int height;
    protected Cell[][] cells;
    protected int nbCells;


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

        this.generate();
    }

    public Cell getCell(int x, int y){
        return this.cells[x][y];
    }

    protected abstract void generate();

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

    public boolean isExternalWall(Cell cell, WallOrientation orientation){
        switch(orientation){
            case NORTH:
                return cell.getX()==0;
            case SOUTH:
                return cell.getY()==this.height-1;
            case EAST:
                return cell.getX()==this.length-1;
            case WEST:
                return cell.getY()==0;
            default:
                return false;             
        }
    }

    public int getNbCell(){
        return this.nbCells;
    }
    
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
}