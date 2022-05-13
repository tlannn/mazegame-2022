package game.quest;

import game.character.Player;
import game.observer.Observable;
import game.observer.ObservableEvent;

import static game.observer.ObservableEvent.EVENT_PICK_UP_GOLD;
import static game.observer.ObservableEvent.EVENT_SPEND_GOLD;

/**
 * A condition where the player must have a certain amount of gold when reaching the winning cell
 */
public class EarnGoldCondition extends QuestCondition {
	private Player player;
	private final int goldRequired;

	/**
	 * Class constructor
	 * @param player the player that must gather gold
	 * @param goldRequired the amount of gold to gather
	 */
	public EarnGoldCondition(Player player, int goldRequired) {
		this.player = player;
		this.goldRequired = goldRequired;
	}

	@Override
	public void onNotify(Observable observable, ObservableEvent event) {
		if (event == EVENT_PICK_UP_GOLD || event == EVENT_SPEND_GOLD)
			this.completed = ((Player)observable).getGold() >= this.goldRequired;
	}

	/**
	 * Return the amount of gold required to fulfill the condition
	 * @return the amount of gold required
	 */
	public int getGoldRequired(){
		return this.goldRequired;
	}

	@Override
	public String toString(){
		return "Tu dois récupérer " + this.goldRequired + " golds pour valider la quête.";
	}
}
