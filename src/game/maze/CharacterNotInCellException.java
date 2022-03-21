package game.maze;

public class CharacterNotInCellException extends Exception {
    public CharacterNotInCellException(){
        super();
    }

    public CharacterNotInCellException(String e){
        super(e);
    }
}
