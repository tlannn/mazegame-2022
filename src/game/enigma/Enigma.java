package game.enigma;

public abstract class Enigma {

    protected String question;
    protected boolean isResolved;

    public Enigma(String question){
        this.question = question;
        this.isResolved = false;
    }

    public abstract void resolve(String reponseDonne) throws Exception;

    public boolean getIsResolved(){
        return this.isResolved;
    }

    public String getQuestion(){
        return this.question;
    }

    public String toString(){
        return this.question;
    }


    
}
