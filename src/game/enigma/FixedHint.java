package game.enigma;

public abstract class FixedHint extends Hint{
    protected String statement;

    public FixedHint() {}

    public String toString() {
        return this.statement;
    }
}
