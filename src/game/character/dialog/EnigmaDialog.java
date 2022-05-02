package game.character.dialog;

import game.Game;
import game.character.Player;
import game.character.Sphinx;
import game.enigma.Enigma;
import game.system.SpeechPauseSystem;
import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

import java.util.List;

public class EnigmaDialog extends Dialog {
    private Sphinx sphinx;

    public EnigmaDialog(Sphinx sphinx) {
        super();
        this.sphinx = sphinx;
    }

    @Override
    public void start(Player player) {
        InputSystem input = Game.getInputSystem();
        GraphicsSystem graphics = Game.getGraphicsSystem();

        if (!this.sphinx.hasGivenHint()) {
            graphics.displayText("Bonjour," + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " je m'appelle Léo le Sphinx." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
            graphics.displayText("Il semblerait que je ne vous ai pas encore donné d'indice" + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
            List<Enigma> enigmas = this.sphinx.getEnigmas();
            int i = 0;

            if (i < enigmas.size() && ! enigmas.get(i).getIsResolved()) {
                graphics.displayText("Résolvez mon énigme," + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " et mon indice sera vôtre :" + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                graphics.displayText(enigmas.get(i).toString());
                String answer = input.getMessage();

                try{
                    enigmas.get(i).resolve(answer);
                    if(enigmas.get(i).getIsResolved()){
                        graphics.displayText("Bien joué," + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " c'est la bonne réponse !" + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG + " En récompense," + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " voici mon indice :" + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                        graphics.displayText(this.sphinx.getHint().toString() + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                        this.sphinx.markHintGiven();
                    }
                    else{
                        graphics.displayText("Navré aventurier," + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " mais c'est la mauvaise réponse." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG + " Reviens me voir si tu veux retenter ta chance." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                    }
                }
                catch(Exception AnswerNoContainsQCM) {
                    graphics.displayText("Cette réponse ne fait pas partie de celles proposées." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                }
            }
        }
        else {
            graphics.displayText("Je vous ai déjà donné mon indice." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
            graphics.displayText(this.sphinx.getHint().toString() + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
        }
    }
}
