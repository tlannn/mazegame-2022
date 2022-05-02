package game.character.dialog;

import game.Game;
import game.character.Player;
import game.system.SpeechPauseSystem;

public class DefaultDialog extends Dialog {
    @Override
    public void start(Player player) {
        String text = "Vous essayez d'engager la conversation mais vous n'obtenez aucune réponse"
                + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "."
                + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "."
                + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "."
                + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG;
        Game.getGraphicsSystem().displayText(text);
    }
}
