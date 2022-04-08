package game.character;

import game.enigma.*;
import game.hint.*;
import game.maze.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import game.hint.*;

public class Sphinx extends NonPlayerCharacter {

	private Hint hint;
	private List<Enigma> enigmas;
	private int indexCurrentEnigma;
	private boolean hasGivenHint;

	public Sphinx(Cell startingCell) {
		super("Sphinx", startingCell);

		this.hint = null;
		this.enigmas = new ArrayList<>();
		this.indexCurrentEnigma = 0;
		this.hasGivenHint = false;
		this.movable = false;
	}

	public void setHint(Hint hint) {
		if (this.hint == null)
			this.hint = hint;
	}

	public void addEnigma(Enigma enigma) {
		this.enigmas.add(enigma);
	}

	public void talk() {
		if (!this.hasGivenHint) {
			int i = 0;
			while(i<enigmas.size() && !enigmas.get(i).getIsResolved()){
					enigmas.get(i).toString();
					Scanner scan= new Scanner(System.in);
					String text= scan.nextLine();
					try{
						enigmas.get(i).resolve(text);
                        if(enigmas.get(i).getIsResolved()){
							this.hint.toString();
							this.hasGivenHint = true;
						}
                    }
                    catch(Exception AnswerNoContainsQCM) {
                        System.out.println("Cette réponse ne fait pas partit de celles proposées");
                    }
					scan.close();
				}					
			}
		else {
			System.out.println("Je vous ai déjà donné mon indice.");
			System.out.println(this.hint);
		}
	}
}
