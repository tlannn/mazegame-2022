package game.hint;

public class FakeHint extends Hint {
	private final String statement;

	public FakeHint(String statement) {
		this.statement = statement;
	}

	public String toString() {
		return statement;
	}
}
