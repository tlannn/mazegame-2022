package game.hint;

import game.observer.Observable;
import game.observer.Observer;
import game.observer.ObservableEvent;



import java.util.ArrayList;
import java.util.List;

public abstract class Hint implements Observable{

	protected List<Observer> observers;

	public Hint() {
		this.observers = new ArrayList<Observer>();
	}

	public void notify(Observable observable, ObservableEvent event){
		for (Observer observer : this.observers) {
			observer.onNotify(observable, event);
		}
	}
	public void addObserver(Observer observer){
		this.observers.add(observer);
	}
	public void removeObserver(Observer observer){
		this.observers.remove(observer);
	}

}
