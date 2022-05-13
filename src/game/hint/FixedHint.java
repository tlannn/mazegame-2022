package game.hint;

/**
* Represents a fixed hint that mean the hint doesn't change during the game
*/
public abstract class FixedHint extends Hint{
    protected String statement;

    public FixedHint() {}

    /**
    * Display the hint 
    */
    public String toString() {
        return this.statement;
    }
}
