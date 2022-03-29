package game.observer;

import game.util.Event;

public interface Observer {
	public void onNotify(Observable observable, Event event);
}
