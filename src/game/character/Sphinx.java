// MODIF il faut fermer le scan
package game.character;

import game.enigma.*;
import game.hint.*;
import game.maze.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import game.hint.*;

import game.system.graphics.GraphicsSystem;
import game.system.input.InputSystem;

public class Sphinx extends NonPlayerCharacter {

	private Hint hint;
	private List<Enigma> enigmas;
	//private int indexCurrentEnigma;
	private boolean hasGivenHint;

	public Sphinx(Cell startingCell) {
		super("Sphinx", startingCell);

		this.hint = null;
		this.enigmas = new ArrayList<>();
		//this.indexCurrentEnigma = 0;
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

	public void talk(GraphicsSystem graphicsSystem, InputSystem inputSystem, Player player) {
		System.out.println("Bonjour, je m'appelle Léo le sphinx");
		if (!this.hasGivenHint) {
			int i = 0;
			System.out.println("je ne t'ai pas encore donné d'indice...");

			if(i < this.enigmas.size() && ! this.enigmas.get(i).getIsResolved()){
				System.out.println("Voici mon enigme :");
					System.out.println(this.enigmas.get(i).toString());
					Scanner scan= new Scanner(System.in);
					String text = scan.nextLine();
					try{
						this.enigmas.get(i).resolve(text);
            if(this.enigmas.get(i).getIsResolved()){
							System.out.println("Bien joué, c'est la bonne réponse ! En récompense je te donne cet indice :");
							System.out.println(this.hint.toString());
							this.hasGivenHint = true;
						}
						else{
							System.out.println("Désolé c'est la mauvaise réponse");
						}
          }
          catch(Exception AnswerNoContainsQCM) {
              System.out.println("Cette réponse ne fait pas partit de celles proposées");
          }
					// quand je ferme le scan ça fait un bug
					// scan.close();
				}
			}
		else {
			System.out.println("Je vous ai déjà donné mon indice.");
			System.out.println(this.hint);
		}
	}
}
