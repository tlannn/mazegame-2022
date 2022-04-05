package game;

import java.util.*;
import game.maze.*;
import game.character.*;
import game.character.Character;
import game.character.*;
import game.item.*;

public class Game{

    private Maze maze;
    private Player player;

    public Game(Maze maze, Player player){
        this.maze = maze;
        this.player = player;
    }


    public void playTurn(Player player, Maze maze){
        System.out.println("---------------------------------------------------");
        System.out.println(maze);
        System.out.println("h - aide");
        System.out.println("Je suis case"+this.player.getCurrentCell().toString());
        player.look();
        boolean bonText=false;
        //while (! bonText){//ATTENTION actuellement je fais une boucle infini

            Scanner scan= new Scanner(System.in);
            String text= scan.nextLine();

            if (text.equals("h")){
                System.out.println(this.afficheAide());
                text= scan.nextLine();
            }

            if (text.equals("a")){
                boolean bonAvancement=false;
                while(! bonAvancement){
                    System.out.println(this.regarderAutour());
                    //System.out.println("appuyer sur z pour avancer vers le nord, sur d pour aller vers l'est, sur q pour aller vers l'ouest sur s pour aller vers le sud");
                    text= scan.nextLine();

                    if(text.equals("z")){
                        bonAvancement=this.move(Orientation.NORTH, this.player);
                    }
                    else if(text.equals("d")){
                        bonAvancement=this.move(Orientation.EAST, this.player);
                    }
                    else if(text.equals("q")){
                        bonAvancement=this.move(Orientation.WEST, this.player);
                    }
                    else if(text.equals("s")){
                        bonAvancement=this.move(Orientation.SOUTH, this.player);
                    }
                    else if (text.equals("a")){
                        bonAvancement=true;
                    }
                    if(bonAvancement==false){
                        System.out.println("Ce déplacement n'est pas possible, taper 'a' pour quitter ou choisissez une position correcte");
                    }
                }
            }

            if (text.equals("u")){
                //faire une boucle tant que l'utilisateur a pas envoyé le bon num et faire un truc retour pour si finalement il ne veut plus utiliser d'objet
                boolean bonNum=false;
                while (!bonNum){
                    System.out.println("Quel objet voulez vous utiliser ? (entrer son indice)");
                    System.out.println(this.player.getInventory().toString());
                    String text2= scan.nextLine();
                    if(text2.equals("q")){
                        bonNum=true;
                    }
                    try{
                        if (Integer.parseInt(text2)>=0 && Integer.parseInt(text2)<player.getInventory().getItems().size()){
                            bonNum=true;
                            Item item = player.getInventory().getItem(Integer.parseInt(text2));
                            player.useItem(item);
                        }
                    }
                    catch(Exception NumberFormatException) {
                        System.out.println("Ce numero n'est pas valide, si vous ne voulez pas utiliser d'objet appyer sur q");
                    }
                }
            }

            if (text.equals("t")){
                List <Item> items= player.getCurrentCell().getItemsInCell();
                boolean bonNum=false;
                while(!bonNum){
                    System.out.println("quel item voulez vous prendre ? (taper son indice)");
                    for (int i=0; i<items.size();i++){
                        System.out.print(i+"-"+items.get(i)+" ");
                    }
                    String text2= scan.nextLine();
                    if(text2.equals("q")){
                        bonNum=true;
                    }
                    try{ if (Integer.parseInt(text2)>=0 && Integer.parseInt(text2)<items.size()){
                        bonNum=true;
                        player.getInventory().addItem(items.get(Integer.parseInt(text2)));
                    }}
                    catch(Exception NumberFormatException){
                        System.out.println("Ce numero n'est pas valide, si vous ne voulez pas ramasser d'objet appuyer sur q");
                    }
                }
            }
            if (text.equals("p")){
                List <NonPlayerCharacter> characters = player.getCurrentCell().getNonPlayerCharactersInCell();
                boolean bonNum=false;
                while(!bonNum){
                    System.out.println("A qui voulez vous parler ? (taper son indice)");
                    for (int i=0; i<characters.size();i++){
                        System.out.print(i+"-"+characters.get(i)+" ");
                    }
                    String text2= scan.nextLine();
                    if(text2.equals("q")){
                        bonNum=true;
                    }
                    try{
                        if (Integer.parseInt(text2)>=0 && Integer.parseInt(text2)<characters.size()){
                            bonNum=true;
                            characters.get(Integer.parseInt(text2)).talk();
                        }
                    }
                    catch(Exception NumberFormatException){
                        System.out.println("Ce numero n'est pas valide, si vous ne voulez pas parler avec un personnage appuyer sur q");
                    }
                }
            }
    }


    public String regarderAutour(){
        List <Orientation> orientationPossible = player.getCurrentCell().possibleOrientations();
        System.out.println("Vous pouvez aller au:");
        String res = "";
        for(Orientation orientation : orientationPossible){
            switch (orientation){
                case NORTH:
                res += "z - "+orientation.toString()+"\n";
                break;
                case SOUTH:
                res += "s - "+orientation.toString()+"\n";
                break;
                case EAST:
                res += "d - "+orientation.toString()+"\n";
                break;
                case WEST:
                res += "q - "+orientation.toString()+"\n";
                break;
            }
        }
        return res.substring(0,res.length()-1);
    }

    public String afficheAide(){
        String res="";
        res += "appuyer sur:\n";
        res += "a - avancer\n";
        //System.out.println("r - regarder autour de vous");
        res += "u - utiliser un objet de votre inventaire\n";
        res += "p - parler avec un personnage\n";
        res += "t - ramasser un objet\n";
        return res;
    }

    public String inventoryToString(Player player){
        String res="";
        int i=0;
        for (Item item : player.getInventory().getItems()){
            res+=i+"-"+item.toString();
        }
        return res;
    }

    public void moveOrientation(Orientation orientation, Character character){
        int x = character.getCurrentCell().getX();
        int y = character.getCurrentCell().getY();
        // On supprime le character de la liste des perso présentent dans la cellule courrante
        try{
            character.getCurrentCell().removeCharacter(character);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Pas possible");
        }
        if (orientation == Orientation.EAST){
            character.setCurrentCell(this.maze.getCell(x+1, y));
        }
        else if (orientation == Orientation.WEST){
            character.setCurrentCell(this.maze.getCell(x-1, y));
        }
        else if (orientation == Orientation.NORTH){
            character.setCurrentCell(this.maze.getCell(x, y-1));
        }
        else if (orientation == Orientation.SOUTH){
            character.setCurrentCell(this.maze.getCell(x, y+1));
        }
        // On ajoute le charcter dans la liste des pesro dans la nouvelle cellule
        character.getCurrentCell().addCharacter(character);
    }

    public boolean move(Orientation orientation, Character character){
        if (character.isMovable() && character.getCurrentCell().possibleOrientations().contains(orientation) ){
            this.moveOrientation(orientation, character);
            return true;
        }
        else{return false;}
    }
}
