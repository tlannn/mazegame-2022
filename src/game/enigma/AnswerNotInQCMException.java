package game.enigma;

public class AnswerNotInQCMException extends Exception{
    public AnswerNotInQCMException(){
        super();
    }

    public AnswerNotInQCMException(String e){
        super(e);
    }
}