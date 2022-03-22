package game.enigma;

public class Enigma {

    private String statement;
    private String[] answer;
    private int correctAnswerIndex;

    public Enigma(String statement, String[] answer, int correctAnswerIndex){

        this.statement = statement;
        this.answer = answer;
        this.correctAnswerIndex = correctAnswerIndex;

    }

    
}
