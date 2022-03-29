package game.quest;

import game.character.Character;
import game.character.Player;
import game.util.Event;

public class MeetSpecificCharacterCondition extends QuestCondition {
	private final Character characterToMeet;

	public MeetSpecificCharacterCondition(Character characterToMeet) {
		this.characterToMeet = characterToMeet;
	}

	public void onNotify(Character character, Event event) {
		switch (event) {
			case EVENT_MEET_CHARACTER:
				if (character.equals(this.characterToMeet))
					this.completed = true;
				break;
		}
	}

	public String toString() {
		return "Tu dois absolument voir le " + this.characterToMeet;
	}
}
