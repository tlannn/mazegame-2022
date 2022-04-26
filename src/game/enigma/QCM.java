 package game.enigma;

import java.util.*;

public class QCM extends Enigma {

    private List<String> reponses;
    private String solution;

    public QCM(String question, List<String> reponses, String solution){
        super(question);
        this.reponses = reponses;
        this.solution = solution;
    }


    public List<String> getReponses(){
        return this.reponses;
    }

    
    public void resolve(String reponseDonne) throws AnswerNoContainsQCM{
        int reponse = Integer.parseInt(reponseDonne);
        if (reponse < this.reponses.size()){
            if (this.solution.equals(this.reponses.get(reponse))){
                this.isResolved = true;
            }
            //if (this.reponses)
        }
        else{
            throw new AnswerNoContainsQCM("Cette réponse ne fait pas partit de celles proposées");
        }
        /*
        if (this.reponses.containsKey(reponseDonne)){
            if(this.reponses.get(reponseDonne) == true){
                this.isResolved = true;
            }
        }
        else{
            throw new AnswerNoContainsQCM("Cette réponse ne fait pas partit de celles proposées");
        }
        */

    }
    

    public String toString(){
        String res = this.question + "\ntaper l'indice correspondant à la bonne réponse \n";
        int i = 0;
        for(String reponse : this.reponses){
            res = res + i + " - " + reponse +" \n";
            i += 1;
        }
        return res;

    }



}
