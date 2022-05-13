package game.observer;

/**
 * Represents an object that can be observed for a specific event
 */
public interface Observable {
	/**
	 * Notify all observers of the observable that an event occurred
	 * @param observable the observable linked to the event
	 * @param event the event that occurred
	 */
	public void notify(Observable observable, ObservableEvent event);

	/**
	 * Add an observer to the observable
	 * @param observer the observer
	 */
	public void addObserver(Observer observer);

	/**
	 * Remove an observer from the observable
	 * @param observer the observer
	 */
	public void removeObserver(Observer observer);
}
