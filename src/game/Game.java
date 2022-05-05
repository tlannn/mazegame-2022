package game;

import java.util.*;

import game.maze.*;
import game.character.*;
import game.character.Character;
import game.item.*;
import game.system.SpeechPauseSystem;
import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

public class Game{
    private Player player;
    private Level level;
    private static InputSystem inputSystem;
    private static GraphicsSystem graphicsSystem;

    public Game(Player player, GameGraphicsMode mode){
        this.player = player;
        graphicsSystem = mode.getGraphicsSystem(); // ConsoleGraphicsSystem
        inputSystem = mode.getInputSystem();

        // Create the level
        LevelGenerator generator = new LevelGenerator();
        this.level = generator.generateLevel(this.player);
    }

    public void init(){
        graphicsSystem.displayGameTitle();
        graphicsSystem.displayText("\nQuel est ton prénom," + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " aventurier ?");
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
}
