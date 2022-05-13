package game;

import java.util.*;

import game.maze.*;
import game.character.*;
import game.character.Character;
import game.system.SpeechPauseSystem;
import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

public class Game{

    private Player player;
    private Level level;
    private static InputSystem inputSystem;
    private static GraphicsSystem graphicsSystem;

    public Game(GameGraphicsMode mode){
        Game.setGameGraphicsMode(mode);
    }

    public Game(GameGraphicsMode mode, Player player, Level level) {
        Game.setGameGraphicsMode(mode);
        this.player = player;
        this.level = level;
    }

    public void init(MazeAlgorithm algorithm) {
        if (this.player == null) {
            graphicsSystem.displayGameTitle();
            graphicsSystem.displayText("\nQuel est ton prénom," + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " aventurier ?");
            String playerName = inputSystem.getMessage();
            this.player = new Player(playerName);
        }

        if (this.level == null) {
            // Create the level
            LevelGenerator generator = new LevelGenerator();
            this.level = generator.generateLevel(this.player, algorithm);
        }
    }

    public static void setGameGraphicsMode(GameGraphicsMode mode) {
        Game.graphicsSystem = mode.getNewGraphicsSystem();
        Game.inputSystem = mode.getNewInputSystem();
    }

    public static InputSystem getInputSystem() {
        return Game.inputSystem;
    }

    public static GraphicsSystem getGraphicsSystem() {
        return Game.graphicsSystem;
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

        this.graphicsSystem.displayText("Felcitation " + this.player + " ! Tu as Gagné !", true);
        this.graphicsSystem.displayText("Tu as le droit de recommancer une partie" + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + ".");

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

    public Player getPlayer() {
        return this.player;
    }

    public Level getLevel() {
        return this.level;
    }
}
