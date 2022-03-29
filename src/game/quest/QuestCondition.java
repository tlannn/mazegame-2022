package game.quest;

import game.observer.Observer;

public abstract class QuestCondition implements Observer {
	protected boolean completed;
	public QuestCondition() {
		this.completed = false;
	}

	//public abstract boolean isCompleted();
	public boolean isCompleted() {
		return this.completed;
	}
}
