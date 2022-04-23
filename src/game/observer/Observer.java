package game.observer;

public interface Observer {
	public void onNotify(Observable observable, ObservableEvent event);
}
