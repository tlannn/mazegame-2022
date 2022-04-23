package game.character.state;

import game.character.Player;
import game.character.action.Action;
import game.character.action.ChangeStateAction;
import game.character.action.MoveAction;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;
import game.maze.Orientation;

/**
 * Represents the state where the player is beginning his turn ; he's asked to choose an action to do
 */
public class StartTurnState implements BaseState {
    @Override
    public boolean enter(Player player, GraphicsSystem graphics) {
        graphics.displayText("Que voulez-vous faire ? (appuyez sur H pour obtenir de l'aide)");
        return true;
    }

    @Override
    public Action handleInput(Player player, InputSystem input) {
        switch (input.getLetter()) {
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
            default:
                return null;
        }
    }
}
