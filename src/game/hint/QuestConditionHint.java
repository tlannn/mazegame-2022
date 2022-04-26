package game.hint;

import game.quest.*;

public class QuestConditionHint extends FixedHint {

    public QuestConditionHint(QuestCondition condition){
        super();

        this.statement = condition.toString();
    }





}
