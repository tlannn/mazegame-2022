package game.character.dialog;

import game.Game;
import game.character.Player;
import game.character.Sphinx;
import game.enigma.Enigma;
import game.system.SpeechPauseSystem;
import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;
import static game.observer.ObservableEvent.EVENT_HINT_DISCOVERED;


import java.util.List;

public class EnigmaDialog extends Dialog {
    private Sphinx sphinx;

    /**
     * Class constructor
     * @param sphinx the sphinx that gives a riddle
     */
    public EnigmaDialog(Sphinx sphinx) {
        super();
        this.sphinx = sphinx;
    }

    @Override
    public void start(Player player) {
        InputSystem input = Game.getInputSystem();
        GraphicsSystem graphics = Game.getGraphicsSystem();

        // Check first if the sphinx hasn't given a hint
        if (!this.sphinx.hasGivenHint()) {
            // Display a visual of the sphinx
            graphics.displayText("\n\n       .~~~.");
            graphics.displayText("      /|6 6|\\");
            graphics.displayText("     /O\\_^_/O\\");
            graphics.displayText("     \\/`===`\\/");
            graphics.displayText("     ,| |^| |.");
            graphics.displayText("____(n(n)_(n)n)____");
            graphics.displayText("%%%%%%%%%%%%%%%%%%%\n\n");

            // Display intro
            graphics.displayText("Bonjour " + player + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + ", je m'appelle Léo le Sphinx." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
            graphics.displayText("Il semblerait que je ne t'ai pas encore donné d'indice" + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
            Enigma enigma = this.sphinx.getEnigma();

            // Check if sphinx can give an enigma
            if (enigma != null) {
                graphics.displayText("Résous mon énigme" + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " et mon indice sera tiens :" + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                graphics.displayText(enigma.toString());
//                String answer = input.getMessage();

//                try {
                    enigma.resolve();
                    if (enigma.isResolved()) {
                        graphics.displayText("Bien joué " + player + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + ", c'est la bonne réponse !" + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG + " En récompense," + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " voici mon indice :" + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                        graphics.displayText(this.sphinx.getHint().toString() + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                        this.sphinx.getHint().notify(this.sphinx.getHint(), EVENT_HINT_DISCOVERED);
                        this.sphinx.markHintGiven();
                    } else {
                        graphics.displayText("Navré aventurier," + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " mais c'est la mauvaise réponse." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG + " Reviens me voir si tu veux retenter ta chance." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                    }
                /*} catch (Exception AnswerNoContainsQCM) {
                    graphics.displayText("Cette réponse ne fait pas partie de celles proposées." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                }*/
            } else {
                graphics.displayText("Navré, mais je n'ai plus aucune énigme en tête.");
            }

        }

        else {
            graphics.displayText("Désolé " + player + ", je t'ai déjà donné mon indice." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
            graphics.displayText(this.sphinx.getHint().toString() + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
        }
    }
}
