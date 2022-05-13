package game.character.dialog;

import game.Game;
import game.character.Player;
import game.system.SpeechPauseSystem;
import game.system.graphics.GraphicsSystem;
import game.hint.Hint;

import static game.observer.ObservableEvent.EVENT_HINT_DISCOVERED;


public class GiveHintDialog extends Dialog {
    private Hint hint;

    /**
     * Class constructor
     * @param hint the new hint to give
     */
    public GiveHintDialog(Hint hint) {
        super();
        this.hint = hint;
    }

    @Override
    public void start(Player player) {
        GraphicsSystem graphics = Game.getGraphicsSystem();
        graphics.displayText("Psst..." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG + "Psst... " + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG + player + "..." + " Tu cherches un indice ?" + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG + " En voici un :" + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
        graphics.displayText(hint.toString() + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
        hint.notify(hint, EVENT_HINT_DISCOVERED);

    }
}
