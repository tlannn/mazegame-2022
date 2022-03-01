package game.character;

public class NotEnoughGoldException extends Exception {

	public NotEnoughGoldException() {
		super();
	}

	public NotEnoughGoldException(String e) {
		super(e);
	}
}
