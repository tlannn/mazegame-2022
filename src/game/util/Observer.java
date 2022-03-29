package game.util;

import game.character.Character;

public interface Observer {
	public void onNotify(Character character, Event event);
}
