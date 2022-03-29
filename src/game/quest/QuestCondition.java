package game.quest;

import game.util.Observer;

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
