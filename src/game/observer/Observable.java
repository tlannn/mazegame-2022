package game.observer;

import game.util.Event;

public interface Observable {
	public void notify(Observable observable, Event event);
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
}
