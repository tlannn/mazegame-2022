package game.character.state;

import game.Game;
import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.system.SpeechPauseSystem;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

/**
 * Represents the state where the player is looking in his inventory
 */
public class LookingInventoryState implements BaseState {
    @Override
    public boolean enter(Player player) {
        GraphicsSystem graphics = Game.getGraphicsSystem();

        if (!player.getInventory().getItems().isEmpty()) {
            graphics.displayText("Vous avez dans votre inventaire :" + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
            graphics.displayList(player.getInventory().getItems(), false);
            graphics.displayText("\nSouhaitez-vous utiliser un objet ? (O/N)");
            return true;
        }

        else {
            graphics.displayText("Vous n'avez rien dans votre inventaire." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
            return false;
        }
    }

    @Override
    public Action handleInput(Player player) {
        switch (Game.getInputSystem().getLetter()) {
            case 'O':
                return new ChangeStateAction(new ChooseItemToUseState());
            case 'N':
                return new ChangeStateAction(new StartTurnState());
            default:
                return null;
        }
        /*if (input.getLetter() == 'A')
            //player.useItem(player.getInventory().getItem(0));
            return new UseItemAction(player.getInventory().getItem(0));

        return null;*/
    }
}
