package game.character.state;

import game.Game;
import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.character.action.MoveAction;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;
import game.maze.Orientation;
import game.character.action.LookDiscoveredHintAction;

/**
 * Represents the state where the player is beginning his turn ; he's asked to choose an action to do
 */
public class StartTurnState implements BaseState {
    @Override
    public boolean enter(Player player) {
        Game.getGraphicsSystem().displayText("Que voulez-vous faire ? (appuyez sur H pour obtenir de l'aide)", true);
        return true;
    }

    @Override
    public Action handleInput(Player player) {
        switch (Game.getInputSystem().getLetter()) {
            case 'H':
                return new ChangeStateAction(new AskHelpState());
            case 'Z':
                return new MoveAction(Orientation.NORTH);
            case 'S':
                return new MoveAction(Orientation.SOUTH);
            case 'Q':
                return new MoveAction(Orientation.WEST);
            case 'D':
                return new MoveAction(Orientation.EAST);
            case 'P':
                return new ChangeStateAction(new ChooseNPCToTalkState());
            case 'R':
                return new ChangeStateAction(new ChooseItemToPickUpState());
            case 'I':
                return new ChangeStateAction(new LookingInventoryState());
            case 'V':
                return new LookDiscoveredHintAction();
            default:
                return null;
        }
    }
}
