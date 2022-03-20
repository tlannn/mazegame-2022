package game.enigma;

import game.quest.*;

public class QuestConditionHint extends FixedHint {

    public QuestConditionHint(QuestCondition quest){
        super();

        this.statement = quest.toString();
    }




    
}
