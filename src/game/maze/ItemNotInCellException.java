package game.maze;

public class ItemNotInCellException extends Exception {
	public ItemNotInCellException(){
		super();
	}
	
	public ItemNotInCellException(String e){
		super(e);
	}
}
