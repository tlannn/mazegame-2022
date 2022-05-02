package game.enigma;

public class Answer extends Enigma{

    private String answer;

    public Answer(String question, String answer){
        super(question);
        this.answer = answer;
    }

    public String getAnswer(){
        return this.answer;
    }

    public void resolve(String answer){
        if (this.answer.equals(answer)){
            this.isResolved = true;
        }
    }

}
