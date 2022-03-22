package game;

import java.util.*;
import game.maze.*;
import game.character.Character;

public class Game{

    public Game(){

    }

    public boolean playTurn(Player player,, Maze maze){
      boolean bonText=false
      while (! bonText){//ATTENTION actuellement je fais une boucle infini

        Scanner scan= new Scanner(System.in);
        String text= scan.nextLine();
        System.out.println("Pour avancer d'une case appuyé sur a ");
        System.out.println("Pour regarder autour de vous appuyer sur r ");
        System.out.println("Pour utiliser un objet de votre inventaire appuyer sur u ");

        if text.equals("a"){
          boolean bonAvancement=false;
          while(! bonAvancement){

            System.out.println("appuyer sur z pour avancer vers le nord, sur d pour aller vers l'est, sur q pour aller vers l'ouest sur s pour aller vers le sud");
            String text= scan.nextLine();

            if(text.equals("z")){
              bonAvancement=this.move(Orientation.NORTH, maze, player)
            }
            else if(text.equals("d")){
              bonAvancement=this.move(Orientation.EAST, maze, player)
            }
            else if(text.equals("q")){
              bonAvancement=this.move(Orientation.WEAST, maze, player)
            }
            else if(text.equals("s")){
              bonAvancement=this.move(Orientation.SOUTH, maze, player)
            }
            else if (text.equals("a")){
              bonAvancement=true;
            }
            if{ bonAvancement==false){
              system.out.println("Ce déplacement n'est pas possible, taper a pour quitter");
            }
          }

        }

        if text.equals("r"){// PAS FINI faut voir ce qu'on fait quand on trouve un objet
            player.look();
        }
        if text.equals("u"){
            //faire une boucle tant que l'utilisateur a pas envoyé le bon num et faire un truc retour pour si finalement il ne veut plus utiliser d'objet
            boolean bonNum=false;
            while (!bonNum){
              System.out.println("Quel objet voulez vous utiliser ? (entrer son indice)");
              System.out.println(inventoryToString());
              int num=scan.nextInt();
              if (num>=0 && num<player.getInventoryItems().size()){
                bonNum=true;
                player.getInventoryItems()[num].use()//on utilise l'item
              }
              else if(num.equals("q")){
                bonNum=true;
              }
              else{
                System.out.println("Ce numero n'est pas valide, si vous ne voulez pas utiliser d'objet appyer sur q");
              }
            }
          }
        }
    }


    public String inventoryToString(Player player){
        String res="";
        int i=0;
        for (Item item : player.getInventoryItems()){
            res+=i+"-"+item.toString();
        }
        return res;
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
