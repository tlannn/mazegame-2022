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
        this.cells = new Cell[length][height];
        for(int i = 0; i < length; i++ ){
            for(int j = 0; j < height; j++){
                this.cells[i][j] = new Cell(i, j);
            }
        }
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
                    cells[cell.getX()+1][cell.getY()].setEastWall(false);
                }
                break;
            case WEST:
                cell.setWestWall(false);
                if (!this.isExternalWall(cell,orientation)){
                    cells[cell.getX()-1][cell.getY()].setWestWall(false);
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
}