package game.quest;

import game.observer.Observer;

/**
 * A condition to fulfill to complete the quest
 */
public abstract class QuestCondition implements Observer {
	protected boolean completed;

	/**
	 * Class constructor
	 */
	public QuestCondition() {
		this.completed = false;
	}

	/**
	 * Return if the condition is fulfilled
	 * @return true if the condition is fulfilled
	 */
	public boolean isCompleted() {
		return this.completed;
	}
}
