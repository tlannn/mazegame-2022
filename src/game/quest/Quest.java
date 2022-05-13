package game.quest;

import game.maze.Cell;

import java.util.List;
import java.util.ListIterator;

/**
 * Represent a quest that the player must complete to win the game
 */
public class Quest {
	private Cell winningCell;
	private List<QuestCondition> winningConditions;

	/**
	 * Class constructor
	 * @param winningCell the cell where the player must go to win
	 * @param winningConditions the additional conditions to fulfill to win
	 */
	public Quest(Cell winningCell, List<QuestCondition> winningConditions) {
		this.winningCell = winningCell;
		this.winningConditions = winningConditions;
	}

	/**
	 * Check if the quest is completed
	 * @param playerCell the current the of the player
	 * @return true if the quest is completed
	 */
	public boolean isFinished(Cell playerCell) {
		ListIterator<QuestCondition> it = this.winningConditions.listIterator();
		boolean isFinished = true;

		while (it.hasNext() && isFinished) {
			QuestCondition condition = it.next();
			if (!condition.isCompleted())
				isFinished = false;
		}

		return isFinished && this.winningCell.equals(playerCell);
	}

	/**
	 * Getter for the winning cell
	 * @return the winning cell
	 */
	public Cell getWinningCell(){
		return this.winningCell;
	}

	/**
	 * Getter for the list of conditions to fulfill
	 * @return the list of quest conditions
	 */
	public List<QuestCondition> getWinningConditions(){
		return this.winningConditions;
	}
}
