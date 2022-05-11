package game.character;

import game.Level;
import game.maze.*;
import game.observer.Observable;
import game.observer.ObservableEvent;
import game.observer.Observer;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

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

        // Inform the cell that it contains the character
        if (currentCell != null)
            this.currentCell.addCharacter(this);
    }

    public abstract void update(Level level);

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notify(Observable observable, ObservableEvent event) {
        for (Observer observer : this.observers) {
            observer.onNotify(observable, event);
        }
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public String getName(){
        return this.name;
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
