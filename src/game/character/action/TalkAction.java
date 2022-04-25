package game.character.action;

import game.Level;
import game.character.NonPlayerCharacter;
import game.character.Player;

import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

/**
 * Represents the action of talking to a NonPlayerCharacter
 */
public class TalkAction implements Action {
    private NonPlayerCharacter npc;

    /**
     * Class constructor
     * @param npc the NonPlayerCharacter to talk to
     */
    public TalkAction(NonPlayerCharacter npc) {
        this.npc = npc;
    }

    @Override
    public boolean execute(Level level, Player player, InputSystem inputSystem, GraphicsSystem graphicsSystem){
        this.npc.talk(graphicsSystem, inputSystem, player);
        return true;
    }

    @Override
    public String toString() {
      return "Merci de ta visite.";
        // return "Vous parlez à " + this.npc;
    }
}
