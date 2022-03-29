package game.quest;

import game.character.Player;
import game.observer.Observable;
import game.util.Event;

import static game.util.Event.EVENT_PICK_UP_GOLD;

public class EarnGoldCondition extends QuestCondition {
	private Player player;
	private final int goldRequired;

	public EarnGoldCondition(Player player, int goldRequired) {
		this.player = player;
		this.goldRequired = goldRequired;
	}

	public boolean isCompleted() {
		return this.player.getGold() >= this.goldRequired;
	}

	public void onNotify(Observable observable, Event event) {
		if (event == EVENT_PICK_UP_GOLD)
			this.completed = ((Player)observable).getGold() >= this.goldRequired;
	}

	public String toString(){
		return "Tu dois récupérer " + this.goldRequired + " gold !";
	}
}
