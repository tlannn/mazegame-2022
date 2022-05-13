package game.hint;

import game.quest.*;

/**
*
* Represents a hint that say a quest condition who is mandatory to win
*/
public class QuestConditionHint extends FixedHint {

    /**
    * Constructor
    * @param condition the hint say a condition that you have to do to win
    */
    public QuestConditionHint(QuestCondition condition){
        super();

        this.statement = condition.toString();
    }





}
