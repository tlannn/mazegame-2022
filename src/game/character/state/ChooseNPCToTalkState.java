package game.character.state;

import game.character.NonPlayerCharacter;
import game.character.Player;
import game.character.action.Action;
import game.character.action.TalkAction;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

import java.util.List;

/**
 * Represents the state where the player must choose the NonPlayerCharacter he wants to talk with
 */
public class ChooseNPCToTalkState implements BaseState {
    @Override
    public boolean enter(Player player, GraphicsSystem graphics) {
        List<NonPlayerCharacter> NPCs = player.getCurrentCell().getNonPlayerCharactersInCell();

        if (!NPCs.isEmpty()) {
            graphics.displayText("Sur cette case se trouve :");
            graphics.displayList(NPCs, true);
            graphics.displayText("A qui voulez-vous parler ?");

            return true;
        }

        else {
            graphics.displayText("Il n'y a personne à qui parler ici.");
            return false;
        }
    }

    @Override
    public Action handleInput(Player player, InputSystem input) {
        List<NonPlayerCharacter> NPCs = player.getCurrentCell().getNonPlayerCharactersInCell();
        int choice = input.getIntegerFromLetter();

        if (choice >= 0 && choice < NPCs.size()) {
            return new TalkAction(NPCs.get(choice));
        }

        return null;
    }
}
