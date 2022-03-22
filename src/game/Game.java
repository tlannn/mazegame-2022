package game;

import java.util.*;
import game.maze.*;
import game.character.*;

public class Game{
    
    public game(){

    }

    public boolean playTurn(Character player){
        Scanner scan= new Scanner(System.in);
        String text= scan.nextLine();
        System.out.println("Pour avancer d'une case appuyé sur a ");
        System.out.println("Pour regarder autour de vous appuyer sur r ");
        if text.equals("a"){
            System.out.println("appuyer sur z pour avancer vers le nord, sur d pour aller vers l'est, sur q pour aller vers l'ouest sur s pour aller vers le sud");
            this.move();
        }
    }

    public void move_orientation(Orientation orientation, Maze maze, Character character){
        int x = character.currentCell.getX();
        int y = character.currentCell.getY();
        if (orientation == Orientation.EAST){
            character.currentCell = maze.getCell(x+1, y);
        }
        if (orientation == Orientation.WEST){
            character.currentCell = maze.getCell(x-1, y);
        }
        if (orientation == Orientation.NORTH){
            character.currentCell = maze.getCell(x, y-1);
        }
        if (orientation == Orientation.SOUTH){
            character.currentCell = maze.getCell(x, y+1);
        }
    }

    public boolean move(Orientation orientation, Maze maze, Character character){
        if (character.movable && character.currentCell.possibleOrientations().contains(orientation) ){
            this.move_orientation(orientation, maze, character);
            return true;
        }
        else{return false;}

    }
}
