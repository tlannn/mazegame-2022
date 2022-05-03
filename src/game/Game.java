package game;

import java.util.*;

import game.maze.*;
import game.character.*;
import game.character.Character;
import game.item.*;
import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

public class Game{
    private Player player;
    private Level level;
    private static InputSystem inputSystem;
    private static GraphicsSystem graphicsSystem;

    public Game(Player player, GameGraphicsMode mode){
        this.player = player;
        graphicsSystem = mode.getGraphicsSystem();
        inputSystem = mode.getInputSystem();

        // Create the level
        LevelGenerator generator = new LevelGenerator();
        this.level = generator.generateLevel(this.player);
    }

    public void initGame(){

        this.graphicsSystem.displayGameTitle();
        this.graphicsSystem.displayText("Quel est to n prénom aventurier ?");
        String test = inputSystem.getMessage();
        
        

    }

    public static InputSystem getInputSystem() {
        return inputSystem;
    }

    public static GraphicsSystem getGraphicsSystem() {
        return graphicsSystem;
    }

    public void play() {
        // Create the list of characters that will be updated each turn
        List<Character> characters = new ArrayList<>();
        characters.add(this.player);
        characters.addAll(this.level.getNPCs());

        // Make an iterator for the characters
        ListIterator<Character> iterator = characters.listIterator();

        // Game loop
        while (!this.level.getQuest().isFinished(this.player.getCurrentCell())) {
            // If all characters has played their turn
            if (!iterator.hasNext())
                iterator = characters.listIterator(); // Restart from the first character

            // Play the turn of the next character
            Character nextCharacter = iterator.next();
            nextCharacter.update(this.level);
        }

        this.graphicsSystem.displayGameStatus(level, player);

        // this.graphicsSystem.displayText("Vous avez gagné !");
        this.graphicsSystem.displayText("\n\n               /$$");
        this.graphicsSystem.displayText("              |__/");
        this.graphicsSystem.displayText("/$$  /$$  /$$ /$$ /$$$$$$$");
        this.graphicsSystem.displayText("| $$ | $$ | $$| $$| $$__  $$");
        this.graphicsSystem.displayText("| $$ | $$ | $$| $$| $$  \\ $$");
        this.graphicsSystem.displayText("| $$ | $$ | $$| $$| $$  | $$");
        this.graphicsSystem.displayText("|  $$$$$/$$$$/| $$| $$  | $$");
        this.graphicsSystem.displayText("\\_____/\\___/  |__/|__/  |__/\n\n\n\n");   
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
                    text= scan.nextLine();

                    if(text.equals("z")){
                        bonAvancement=level.move(this.player, Orientation.NORTH);
                    }
                    else if(text.equals("d")){
                        bonAvancement=level.move(this.player, Orientation.EAST);
                    }
                    else if(text.equals("q")){
                        bonAvancement=level.move(this.player, Orientation.WEST);
                    }
                    else if(text.equals("s")){
                        bonAvancement=level.move(this.player, Orientation.SOUTH);
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
                    catch( NumberFormatException e) {
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
            // if (text.equals("p")){
            //     List <NonPlayerCharacter> characters = player.getCurrentCell().getNonPlayerCharactersInCell();
            //     boolean bonNum=false;
            //     while(!bonNum){
            //         System.out.println("A qui voulez vous parler ? (taper son indice)");
            //         for (int i=0; i<characters.size();i++){
            //             System.out.print(i+"-"+characters.get(i)+" ");
            //         }
            //         String text2= scan.nextLine();
            //         if(text2.equals("q")){
            //             bonNum=true;
            //             scan.close();
            //         }
            //         System.out.println("text2: " + text2);
            //         try{
            //             int i = Integer.parseInt(text2);
            //             if (i>=0 && i<characters.size()){
            //                 bonNum=true;
            //                 System.out.println("before talk");
            //                 characters.get(i).talk(this.player);
            //                 System.out.println("end of talk");
            //             }
            //         }
            //         catch(Exception NumberFormatException){
            //           System.out.println(NumberFormatException.getMessage());
            //             System.out.println("Ce numero n'est pas valide, si vous ne voulez pas parler avec un personnage appuyer sur q");
            //         }
            //     }
            // }
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
}
