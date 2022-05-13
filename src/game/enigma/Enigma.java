package game.enigma;

/**
 * Represents an enigma given by a sphinx
 */
public abstract class Enigma {

    protected String question;
    protected boolean resolved;

    /**
     * Class constructor
     * @param question the question of the enigma
     */
    public Enigma(String question) {
        this.question = question;
        this.resolved = false;
    }

    /**
     * Ask the player an answer and check if it is valid
     */
    public abstract void resolve();

    /**
     * Return if the enigma is resolved
     * @return true if it is resolved
     */
    public boolean isResolved(){
        return this.resolved;
    }
}
