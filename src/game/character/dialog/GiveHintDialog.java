package game.character.dialog;

import game.character.Player;
import game.system.output.GraphicsSystem;
import game.hint.Hint;

public class GiveHintDialog extends Dialog {
    private Hint hint;

    public GiveHintDialog(GraphicsSystem graphicsSystem, Hint hint) {
        super(graphicsSystem);
        this.hint = hint;
    }

    @Override
    public void start(Player player) {
        this.graphics.displayText("Psst... vous cherchez un indice ? En voici un :");
        this.graphics.displayText(hint.toString());
    }
}
