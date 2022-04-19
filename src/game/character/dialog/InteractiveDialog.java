package game.character.dialog;

import game.system.input.InputSystem;
import game.system.output.GraphicsSystem;

public abstract class InteractiveDialog extends Dialog {
    protected InputSystem input;

    public InteractiveDialog(GraphicsSystem graphicsSystem, InputSystem inputSystem) {
        super(graphicsSystem);
        this.input = inputSystem;
    }
}
