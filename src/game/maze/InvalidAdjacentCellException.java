package game.maze;

public class InvalidAdjacentCellException extends Exception {
	public InvalidAdjacentCellException(){
		super();
	}

	public InvalidAdjacentCellException(String e){
		super(e);
	}
}
