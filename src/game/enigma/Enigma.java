package game.enigma;

public abstract class Enigma {

    protected String question;
    protected boolean resolved;

    public Enigma(String question){
        this.question = question;
        this.resolved = false;
    }

    public abstract void resolve(String answer) throws Exception;

    public boolean isResolved(){
        return this.resolved;
    }

    public String getQuestion(){
        return this.question;
    }

    public String toString(){
        return this.question;
    }


    

    
}
