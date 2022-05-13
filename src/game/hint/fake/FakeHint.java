package game.hint.fake;

import game.hint.Hint;

/**
* fake hint has a false hint
*/
public class FakeHint extends Hint {
	protected String statement;

	public String toString() {
		return statement;
	}
}
