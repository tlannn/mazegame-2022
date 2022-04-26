package game.character.dialog;

import game.character.Player;
import game.system.graphics.GraphicsSystem;

public abstract class Dialog {
    protected GraphicsSystem graphics;

    public Dialog(GraphicsSystem graphicsSystem) {
        this.graphics = graphicsSystem;
    }

    public abstract void start(Player player);
}
