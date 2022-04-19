package game.character;

import game.Level;
import game.maze.*;
import game.observer.Observable;
import game.observer.Observer;
import game.system.input.InputSystem;
import game.system.output.GraphicsSystem;
import game.util.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Character implements Observable {

    protected Cell currentCell;
    protected String name;
    protected boolean movable;
    protected List<Observer> observers;

    public Character(String name, Cell startingCell) {
        this.name = name;
        this.currentCell = startingCell;
        this.movable = true;
        this.observers = new ArrayList<>();
    }

    public abstract void update(Level level, InputSystem inputSystem, GraphicsSystem graphicsSystem);

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notify(Observable observable, Event event) {
        for (Observer observer : this.observers) {
            observer.onNotify(observable, event);
        }
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public void setCurrentCell(Cell cell){
        this.currentCell = cell;
    }

    public boolean isMovable(){
        return this.movable;
    }

    public String toString(){
        return this.name;
    }

}
