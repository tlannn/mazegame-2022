package game.enigma;

import game.Game;

public class AnswerEnigma extends Enigma{

    private String answer;

    public AnswerEnigma(String question, String answer) {
        super(question);
        this.answer = answer;
    }

    public void resolve() {
        String answer;

        do {
            answer = Game.getInputSystem().getMessage();

            if (answer.isEmpty())
                Game.getGraphicsSystem().displayError("Veuillez entrer une réponse.");
        } while (answer.isEmpty());

        if (this.answer.equalsIgnoreCase(answer))
            this.resolved = true;
    }
}
