package game.quest;

import game.character.Player;
import game.character.Character;
import game.util.Event;

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

	public void onNotify(Character character, Event event) {
		switch (event) {
			case EVENT_PICK_UP_GOLD:
				this.completed = ((Player)character).getGold() >= this.goldRequired;
				break;
		}
	}

	public String toString(){
		return "Tu dois récupérer " + this.goldRequired + " gold !";
	}
}
