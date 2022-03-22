package game;

import java.util.*;
import game.maze.*;
import game.character.Character;

public class Game{
    
    public Game(){

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

    public void moveOrientation(Orientation orientation, Maze maze, Character character){
        int x = character.getCurrentCell().getX();
        int y = character.getCurrentCell().getY();
        // On supprime le character de la liste des perso présentent dans la cellule courrante
        try{
            character.getCurrentCell().removeCharacter(character);
        }
        catch(Exception e){
            System.out.println("Pas possible");
        }
        if (orientation == Orientation.EAST){
            character.setCurrentCell(maze.getCell(x+1, y));
        }
        else if (orientation == Orientation.WEST){
            character.setCurrentCell(maze.getCell(x-1, y));
        }
        else if (orientation == Orientation.NORTH){
            character.setCurrentCell(maze.getCell(x, y-1));
        }
        else if (orientation == Orientation.SOUTH){
            character.setCurrentCell(maze.getCell(x, y+1));
        }
        // On ajoute le charcter dans la liste des pesro dans la nouvelle cellule
        character.getCurrentCell().addCharacter(character);
    }

    public boolean move(Orientation orientation, Maze maze, Character character){
        if (character.isMovable() && character.getCurrentCell().possibleOrientations().contains(orientation) ){
            this.moveOrientation(orientation, maze, character);
            return true;
        }
        else{return false;}

    }
}
