package game.quest;

import game.character.Player;

public class MeetSpecificCharacterCondition extends QuestCondition {
	private Player player;
	private final Character characterToMeet;

	public MeetSpecificCharacterCondition(Player player, Character characterToMeet) {
		this.player = player;
		this.characterToMeet = characterToMeet;
	}

	public boolean isCompleted() {
		return this.player.getCharactersMet().contains(this.characterToMeet);
	}
}
