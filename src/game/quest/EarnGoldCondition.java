package game.quest;

import game.character.Player;
import game.observer.Observable;
import game.observer.ObservableEvent;

import static game.observer.ObservableEvent.EVENT_PICK_UP_GOLD;
import static game.observer.ObservableEvent.EVENT_SPEND_GOLD;


public class EarnGoldCondition extends QuestCondition {
	private Player player;
	private final int goldRequired;

	public EarnGoldCondition(Player player, int goldRequired) {
		this.player = player;
		this.goldRequired = goldRequired;
	}



	public void onNotify(Observable observable, ObservableEvent event) {
		if (event == EVENT_PICK_UP_GOLD || event == EVENT_SPEND_GOLD)
			this.completed = ((Player)observable).getGold() >= this.goldRequired;
	}

	public int getGoldRequired(){
		return this.goldRequired;
	}

	public String toString(){
		return "Tu dois récupérer " + this.goldRequired + " golds pour valider la quête.";
	}
}
