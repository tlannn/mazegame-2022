package game.item;

import game.maze.*;
import game.character.*;
import game.observer.Observable;
import game.util.Event;
import game.observer.Observer;

import java.util.List;

public abstract class Item implements Observable {

    protected Cell currentCell;
    protected List<Observer> observers;

    public Item(Cell cell){
        this.currentCell = cell;

        // Inform the cell that it contains the item
        if (currentCell != null)
            this.currentCell.addItem(this);
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public void setCurrentCell(Cell cell){
        this.currentCell = cell;
    }

    public abstract void use(Player player);

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
}
