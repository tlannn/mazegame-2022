package game.quest;

import game.character.Player;

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

	public String toString(){
		return "Tu dois récupérer " + this.goldRequired + " gold !";
	}
}
