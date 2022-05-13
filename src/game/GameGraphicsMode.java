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

    private GraphicsSystem currentGraphicsSystem;
    private InputSystem currentInputSystem;

    /**
     * class constructor
     * @param mode the graphics mode of the game with index
     */
    private GameGraphicsMode(int mode) {
        switch (mode) {
            // Add other cases for future graphics modes
            default:
                this.graphicsSystem = ConsoleGraphicsSystem.class;
                this.inputSystem = ConsoleInputSystem.class;
        }
    }

    /**
     * return the new graphics systeme if this curent is null else current graphic systeme
     * @return return graphics system
     */
    public GraphicsSystem getGraphicsSystem() {
        return this.currentGraphicsSystem == null ? this.getNewGraphicsSystem() : this.currentGraphicsSystem;
    }

    /**
     * getter for the attributes currentGraphicsSystem
     * @return the value of attribute
     */
    public GraphicsSystem getNewGraphicsSystem() {
        try {
            this.currentGraphicsSystem = this.graphicsSystem.getDeclaredConstructor().newInstance();
            return this.currentGraphicsSystem;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * getter for the attributes currentInputSystem
     * @return the value of attribute 
     */
    public InputSystem getInputSystem() {
        return this.currentInputSystem == null ? this.getNewInputSystem() : this.currentInputSystem;
    }

    public InputSystem getNewInputSystem() {
        try {
            this.currentInputSystem = this.inputSystem.getDeclaredConstructor().newInstance();
            return this.currentInputSystem;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}