package game.character.action;

import game.Level;
import game.character.NonPlayerCharacter;
import game.character.Player;

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
    public boolean execute(Level level, Player player) {
        this.npc.talk(player);
        return true;
    }
}
