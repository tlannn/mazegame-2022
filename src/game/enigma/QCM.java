 package game.enigma;

import java.util.*;

public class QCM extends Enigma {

    private Map<String, Boolean> reponses;

    public QCM(String question, Map<String, Boolean> reponses){
        super(question);
        this.reponses = reponses;
    }


    public Map<String, Boolean> getReponses(){
        return this.reponses;
    }

    public void resolve(String reponseDonne) throws AnswerNoContainsQCM{
        if (this.reponses.containsKey(reponseDonne)){
            if(this.reponses.get(reponseDonne) == true){
                this.isResolved = true;
            }
        }
        else{
            throw new AnswerNoContainsQCM("Cette réponse ne fait pas partit de celles proposées");
        }

    }

    public String toString(){
        String res = this.question + "\ntaper l'indice correspondant à la bonne réponse \n";
        int i = 0;
        for(String reponse : this.reponses.keySet()){
            res = res + i + " - " + reponse +" ";
            i += 1;
        }
        return res;

    }



}
