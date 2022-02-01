package game.maze;

public class notInListException extends Exception {
	public notInListException(){
		super();
	}
	
	public notInListException(String e){
		super(e);
	}
}
