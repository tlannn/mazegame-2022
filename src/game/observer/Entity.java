package game.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements Observable {
    protected List<Observer> observers;

    public Entity() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notify(Observable observable, ObservableEvent event) {
        for (Observer observer : this.observers) {
            observer.onNotify(observable, event);
        }
    }
}
