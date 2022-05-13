package game.observer;

/**
 * Represents an object that can observe an observable for a specific event
 */
public interface Observer {
	/**
	 * Execute something when notified of an observable event
	 * @param observable the observable linked to the event
	 * @param event the event that occurred
	 */
	public void onNotify(Observable observable, ObservableEvent event);
}
