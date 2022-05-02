package game;

import game.system.graphics.ConsoleGraphicsSystem;
import game.system.graphics.GraphicsSystem;
import game.system.input.ConsoleInputSystem;
import game.system.input.InputSystem;

public enum GameGraphicsMode {
    CONSOLE(0);

    private final GraphicsSystem graphicsSystem;
    private final InputSystem inputSystem;

    private GameGraphicsMode(int mode) {
        switch (mode) {
            // Add other cases for future graphics modes
            default:
                this.graphicsSystem = new ConsoleGraphicsSystem();
                this.inputSystem = new ConsoleInputSystem();
        }
    }

    public GraphicsSystem getGraphicsSystem() {
        return graphicsSystem;
    }

    public InputSystem getInputSystem() {
        return inputSystem;
    }
}
