 package game.enigma;

import game.Game;

import java.util.*;

 /**
  * An enigma with predefined answers
  */
 public class QCMEnigma extends Enigma {

    private List<String> answers;
    private String solution;

    /**
     * Class constructor
     * @param question the question of the enigma
     * @param answers the possible answers to the enigma
     * @param solution the correct answer to the enigma
     */
    public QCMEnigma(String question, List<String> answers, String solution){
        super(question);
        this.answers = answers;
        this.solution = solution;
    }

    @Override
    public void resolve() {
        int choice;
        boolean validAnswer = false;

        do {
            choice = Game.getInputSystem().getInteger();

            if (choice >= 0 && choice < this.answers.size()){
                validAnswer = true;

                if (this.solution.equals(this.answers.get(choice)))
                    this.resolved = true;
            }

            else
                Game.getGraphicsSystem().displayError("Cette réponse ne fait pas partie de celles proposées.");
        } while (!validAnswer);
    }

     /**
      * Returns a string representation of the enigma, with its question and all possible answers
      * @return a string representation
      */
    public String toString(){
        String res = this.question + "\n";
        int i = 0;
        for(String answer : this.answers) {
            res = res + i + " - " + answer +" \n";
            i += 1;
        }
        return res;

    }
}
