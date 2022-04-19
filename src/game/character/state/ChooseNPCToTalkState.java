package game.character.state;

import game.character.NonPlayerCharacter;
import game.character.Player;
import game.character.action.Action;
import game.character.action.TalkAction;
import game.system.input.InputSystem;
import game.system.output.GraphicsSystem;

import java.util.List;

/**
 * Represents the state where the player must choose the NonPlayerCharacter he wants to talk with
 */
public class ChooseNPCToTalkState implements BaseState {
    @Override
    public void enter(Player player, GraphicsSystem graphics) {
        List<NonPlayerCharacter> NPCs = player.getCurrentCell().getNonPlayerCharactersInCell();
        graphics.displayText("A qui voulez-vous parler ?\n");

        for (int i = 0; i < NPCs.size(); ++i) {
            graphics.displayText(i + " - " + NPCs.get(i).toString());
        }
    }

    @Override
    public Action handleInput(Player player, InputSystem input) {
        List<NonPlayerCharacter> NPCs = player.getCurrentCell().getNonPlayerCharactersInCell();
        int choice = input.getInteger();

        if (choice > 0 && choice < NPCs.size()) {
            return new TalkAction(NPCs.get(choice));
        }

        return null;
    }
}
