package game.enigma;

public abstract class Enigma {

    protected String question;
    protected boolean resolved;

    public Enigma(String question) {
        this.question = question;
        this.resolved = false;
    }

    public abstract void resolve();

    public boolean isResolved(){
        return this.resolved;
    }
}
