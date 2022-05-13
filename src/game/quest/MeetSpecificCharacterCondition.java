package game.quest;

import game.character.Character;
import game.observer.Observable;
import game.observer.ObservableEvent;

import static game.observer.ObservableEvent.EVENT_MEET_CHARACTER;

public class MeetSpecificCharacterCondition extends QuestCondition {
	private final Character characterToMeet;

	public MeetSpecificCharacterCondition(Character characterToMeet) {
		this.characterToMeet = characterToMeet;
	}

	public void onNotify(Observable observable, ObservableEvent event) {
		if(event == EVENT_MEET_CHARACTER) {
			if(observable.getClass().isInstance(this.characterToMeet)){
				this.completed = true;
			}
		}
	}

	public String toString() {
		return "Tu dois absolument voir " + this.characterToMeet + " pour valider ta quête.";
	}
}
