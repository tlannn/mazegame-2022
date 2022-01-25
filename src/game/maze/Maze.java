package game.maze;

import game.maze.WallOrientation;

public abstract class Maze{
    
    protected int length;
    protected int height;
    protected Cell[][] cells;

    public Maze(int length, int height){
        this.length = length;
        this.height = height;
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
            case WallOrientation.NORTH:
                cell.setNorthWall(false);
                break;
            case WallOrientation.SOUTH:
                cell.setSouthWall(false);
                break;
            case WallOrientation.EAST:
                cell.setEastWall(false);
                break;
            case WallOrientation.WEST:
                cell.setWestWall(false);
                break;
        }
    }

    public boolean isExternalWall(Cell cell, WallOrientation orientation){
        
    }






}