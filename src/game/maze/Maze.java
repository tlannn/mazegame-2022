package game.maze;

import game.maze.WallOrientation;

public abstract class Maze{
    
    protected int length;
    protected int height;
    protected Cell[][] cells;
    protected int nbCells;


    public Maze(int length, int height){
        this.length = length;
        this.height = height;
        this.nbCells = length * height;
        this.cells = new Cell[height][length];
        for(int i = 0; i < height; i++ ){
            for(int j = 0; j < length; j++){
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
                cell.setNorthWall(false);
                if (!this.isExternalWall(cell,orientation)){
                    cells[cell.getX()][cell.getY()-1].setSouthWall(false);
                }
                break;
            case SOUTH:
                cell.setSouthWall(false);
                if (!this.isExternalWall(cell,orientation)){
                    cells[cell.getX()][cell.getY()+1].setNorthWall(false);
                }
                break;
            case EAST:
                cell.setEastWall(false);
                if (!this.isExternalWall(cell,orientation)){
                    cells[cell.getX()-1][cell.getY()].setEastWall(false);
                }
                break;
            case WEST:
                cell.setWestWall(false);
                if (!this.isExternalWall(cell,orientation)){
                    cells[cell.getX()+1][cell.getY()].setWestWall(false);
                }
                break;
        }
    }

    public boolean isExternalWall(Cell cell, WallOrientation orientation){
        switch(orientation){
            case NORTH:
                return(cell.hasNorthWall() && cell.getY()==0);
            case SOUTH:
                return(cell.hasSouthWall() && cell.getX()==this.height);
            case EAST:
                return(cell.hasEastWall() && cell.getY()==this.length);               
            case WEST:
                return(cell.hasWestWall() && cell.getX()==0); 
            default:
                return false;             
            }
        }

    
    
    public int getnbCell(){
        return this.nbCells;
    }
    
    public String toString(){
        String res="";
        for (int j = 0;j<this.length;j++) {
            res+="+---";
        }
        res+="+\n";
        for(int i=0;i<this.height;i++){
            for (int k =0;k<this.length;k++){
                if (this.isExternalWall(getCell(i, k),WallOrientation.EAST) || this.getCell(i, k).hasEastWall()){
                    res+="|   ";}
                else{
                    res+="    ";
                }
            }
            res+="|\n";
            for (int j = 0;j<this.length;j++) {
                if (this.isExternalWall(getCell(i, j),WallOrientation.SOUTH) || this.getCell(i, j).hasSouthWall()){
                    res+="+---";}
                else{
                    res+="+   ";
                }
            }
            res+="+\n";
        }
        
        //System.out.println(res);

        return res;
    }

    public static void main(String[] args){
        Maze m = new KruskalMaze(5, 3);
        System.out.println(m);
    }
}