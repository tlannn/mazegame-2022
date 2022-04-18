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

    public void resolve(String reponsesDonnee){
      System.out.println("la réponse est elle bonne ?");
        if (this.answer.equals(reponsesDonnee)){
            this.isResolved = true;
        }
    }

}
