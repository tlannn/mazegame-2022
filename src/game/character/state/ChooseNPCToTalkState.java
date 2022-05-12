package game.character.state;

import game.Game;
import game.character.NonPlayerCharacter;
import game.character.Player;
import game.character.action.Action;
import game.character.action.TalkAction;
import game.character.action.ChangeStateAction;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

import java.util.List;

/**
 * Represents the state where the player must choose the NonPlayerCharacter he wants to talk with
 */
public class ChooseNPCToTalkState implements BaseState {
    @Override
    public boolean enter(Player player) {
        GraphicsSystem graphics = Game.getGraphicsSystem();
        List<NonPlayerCharacter> NPCs = player.getCurrentCell().getNonPlayerCharactersInCell();

        if (!NPCs.isEmpty()) {
            if (NPCs.size() > 1) { // Single NPCs will automatically be talked to
                graphics.displayText("Sur cette case se trouve :");
                graphics.displayList(NPCs, true);
                graphics.displayText("A qui voulez-vous parler ?");
            }

            return true;
        }

        else {
            graphics.displayText("Il n'y a personne à qui parler ici.");
            return false;
        }
    }

    @Override
    public Action handleInput(Player player) {
        List<NonPlayerCharacter> NPCs = player.getCurrentCell().getNonPlayerCharactersInCell();

        if (NPCs.size() > 1) { // Single NPCs will automatically be talked to
            int choice = Game.getInputSystem().getIntegerFromLetter();

            /*if ((char) choice == 'Q') {
                return new ChangeStateAction(new StartTurnState());
            }*/

            if (choice >= 0 && choice < NPCs.size()) {
                return new TalkAction(NPCs.get(choice));
            }
        }

        else if (NPCs.size() == 1){
            return new TalkAction(NPCs.get(0));
        }

        return null;
    }
}
