package game.observer;

public interface Observable {
	public void notify(Observable observable, ObservableEvent event);
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
}
