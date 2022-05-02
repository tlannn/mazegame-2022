package game.character.state;

import game.character.Player;
import game.character.action.Action;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

/**
 * Represents the state where the player is talking to a NonPlayerCharacter
 */
public class TalkingState implements BaseState {
    @Override
    public boolean enter(Player player) {
        return true;
    }

    @Override
    public Action handleInput(Player player) {
        return null;
    }
}
