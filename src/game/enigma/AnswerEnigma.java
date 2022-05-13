package game.enigma;

import game.Game;

/**
 * Represents an enigma that needs a written answer
 */
public class AnswerEnigma extends Enigma{

    private String answer;

    /**
     * Class constructor
     * @param question the question of the enigma
     * @param answer the answer to the enigma
     */
    public AnswerEnigma(String question, String answer) {
        super(question);
        this.answer = answer;
    }

    @Override
    public void resolve() {
        String answer;

        do {
            answer = Game.getInputSystem().getMessage();

            if (answer.isEmpty())
                Game.getGraphicsSystem().displayError("Tu dois  rentrer une réponse.");
        } while (answer.isEmpty());

        if (this.answer.equalsIgnoreCase(answer))
            this.resolved = true;
    }

    @Override
    public String toString() {
        return this.question;
    }
}
