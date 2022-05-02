package game.enigma;

public class AnswerEnigma extends Enigma{

    private String answer;

    public AnswerEnigma(String question, String answer){
        super(question);
        this.answer = answer;
    }

    public String getAnswer(){
        return this.answer;
    }

    public void resolve(String answer){
        if (this.answer.equals(answer)){
            this.resolved = true;
        }
    }

}
