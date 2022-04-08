package game.quest;

import game.maze.Cell;

import java.util.List;
import java.util.ListIterator;

public class Quest {
	private Cell winningCell;
	private List<QuestCondition> winningConditions;

	public Quest(Cell winningCell, List<QuestCondition> winningConditions) {
		this.winningCell = winningCell;
		this.winningConditions = winningConditions;
	}

	public boolean isFinished() {
		ListIterator<QuestCondition> it = this.winningConditions.listIterator();
		boolean isFinished = true;

		while (it.hasNext() && isFinished) {
			QuestCondition condition = it.next();
			if (!condition.isCompleted())
				isFinished = false;
		}

		return isFinished;
	}

	public Cell getWinningCell(){
		return this.winningCell;
	}

	public List<QuestCondition> getWinningConditions(){
		return this.winningConditions;
	}
}
