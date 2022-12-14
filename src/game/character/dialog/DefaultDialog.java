package game.character.dialog;

import game.Game;
import game.character.Player;
import game.system.SpeechPauseSystem;

/**
 * When a NonPlayerCharacter hasn't something to say, this is the dialog that he will say
 */
public class DefaultDialog extends Dialog {
    @Override
    public void start(Player player) {
        String text = "Tu essayes d'engager la conversation mais tu n'obtiens aucune réponse"
                + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "."
                + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "."
                + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "."
                + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG;
        Game.getGraphicsSystem().displayText(text);
    }
}
