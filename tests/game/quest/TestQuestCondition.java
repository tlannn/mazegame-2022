package game.quest;

import game.observer.Observable;
import game.observer.ObservableEvent;
import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

public class TestQuestCondition extends Tester {
    @Test
    public void testConditionIsUncompletedWhenCreated() {
        QuestCondition condition = new QuestCondition() {
            @Override
            public void onNotify(Observable observable, ObservableEvent event) {}
        };

        assertFalse(condition.isCompleted());
    }
}
