package game;

import game.system.graphics.ConsoleGraphicsSystem;
import game.system.graphics.GraphicsSystem;
import game.system.input.ConsoleInputSystem;
import game.system.input.InputSystem;

import java.lang.reflect.InvocationTargetException;

public enum GameGraphicsMode {
    CONSOLE(0);

    private final Class<? extends GraphicsSystem> graphicsSystem;
    private final Class<? extends InputSystem> inputSystem;

    private GameGraphicsMode(int mode) {
        switch (mode) {
            // Add other cases for future graphics modes
            default:
                this.graphicsSystem = ConsoleGraphicsSystem.class;
                this.inputSystem = ConsoleInputSystem.class;
        }
    }

    public GraphicsSystem getGraphicsSystem() {
        try {
            return this.graphicsSystem.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public InputSystem getInputSystem() {
        try {
            return this.inputSystem.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}