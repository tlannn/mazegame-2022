// changer partout ou c'est mis MODIF
// on ne rencontre jamais de personnages
package game;

import game.character.Character;
import game.item.*;
import game.enigma.*;

//import game.enigma.Hint;

import game.quest.*;
import game.maze.*;
import game.character.*;
import game.hint.*;
import game.util.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import game.hint.Hint;


// quand on ajoute le player il ne faut pas oublier que la case sur laquelle on l'ajoute le player doit avoir player dans ses characters

public class LevelGenerator {

	private Maze maze;
	private Quest quest;
	private Player player;
	// private List<Character> characters;
	private List<Altruist> altruists ;
	private List<Fool> fools;
	private List<Sphinx> sphinxs;
	private List<Trader> traders;

	private List<Hint> hints;
	private List<FakeHint> fakeHints;
	private List<Item> items;
	private List<Enigma> enigmes;

	public LevelGenerator() {
		/*// 1. Créer le labyrinthe
		this.maze = new KruskalMaze(2, 2);

		// 2. Créer le joueur

		//faire ça aléatoirement MODIF
		Player player = new Player("Emma",this.maze.getCell(0,0));
		this.player = player;
		this.maze.getCell(0,0).addCharacter(player);



		// Créer les characters
		// on ne peut créer que 3 indice min et 4 indices max pour l'instant
		// on a que 3 enigme de faites
		// donc 3 <= nbSphinx + nbAltruist <= 4
		// nbSphinx <= 3
		int nbTrader = 5;
		int nbSphinx = 3;
		int nbFools = 5;
		int nbAltruist = 1;


		this.createCharacters(nbTrader, nbSphinx, nbFools, nbAltruist);

		// 4. Créer la quête (a besoin des characters)
		this.quest = createQuest();

		// 6 Créer les indices (a besoin de la quete)
		this.createHints( nbSphinx + nbAltruist , nbFools);


		// 3. Créer les items (a besoin de hint)
		this.items = this.createItems(5);

		// créer les enigmes
		this.createEnigmas();

		// assigner des hints/parchment/enigme au characters
		this.assignItemToCharacter();
		// 7 On crée le jeu
		Game game = new Game (this.maze, this.player);
		game.playTurn(player, this.maze);
		game.playTurn(player, this.maze);
		game.playTurn(player, this.maze);
		game.playTurn(player, this.maze);
		game.playTurn(player, this.maze);*/
	}



	/*public Level generateLevel(Player player){
		//on remet toutes les listes à 0 pour si on a déjà créer un level au par avant
		// this.altruists = new ArrayList<Altruist>();
		// this.fools = new ArrayList <Fool>();
		// this.sphinxs = new ArrayList <Sphinx>();
		// this.traders = new ArrayList <Trader>();

		this.hints = new ArrayList <Hint>();
		this.fakeHints = new ArrayList <FakeHint>();
		this.items = new ArrayList <Item>();
		this.enigmes = new ArrayList <Enigma>();


		//on décide du nombre de personnages.
		// on ne peut créer que 3 indice min et 4 indices max pour l'instant
		// on a que 3 enigme de faites
		// donc 3 <= nbSphinx + nbAltruist + trader <= 4
		// nbSphinx <= 3

		//test on a que 3 indices mais on a l'indice de gold 
		int nbTrader = 0;
		int nbSphinx = 3;
		int nbFools = 0;
		int nbAltruist = 0;

		//on créer le labyrinthe et le joueur
		this.maze = new KruskalMaze(2, 2);
		this.player = player;

		//on créer les personnages
		this.createCharacters(nbTrader, nbSphinx, nbFools, nbAltruist);

		//on crée la quete (a besoin des perso)
		this.quest = createQuest();

		// Créer les indices (a besoin de la quete)
		this.createHints( nbSphinx + nbAltruist + nbTrader, nbFools);

		//  Créer les items (a besoin de hint)
		this.items = this.createItems(5);

		// créer les enigmes
		this.createEnigmas();

		// assigner des hints/parchment/enigme au characters
		this.assignItemToCharacter();

		List <NonPlayerCharacter> NPCs = new ArrayList <NonPlayerCharacter>();
		NPCs.addAll(this.altruists);
		NPCs.addAll(this.fools);
		NPCs.addAll(this.sphinxs);
		NPCs.addAll(this.traders);

		if (nbSphinx + nbAltruist + nbTrader > this.hints.size()){
			System.out.println("--------------------------------------ERROR : Il n'y a pas assez d'indice !!!!!!!!!!! --------------------------------------");
		}

		return new Level(player, this.maze, this.quest, NPCs, this.items);

	}*/

	 public Level generateLevel(Player player){
	 	Maze maze = new KruskalMaze(6, 4);
	 	List<NonPlayerCharacter> NPCs = new ArrayList<>();

	 	List<QuestCondition> winningConditions = new ArrayList<>();
	 	Altruist altruist = new Altruist(maze.getRandomCell());

//	 	Sphinx sphinx = new Sphinx(maze.getCell(0, 0));
	 	Sphinx sphinx = new Sphinx(maze.getRandomCell());
	 	NPCs.add(sphinx);

//	 	MeetSpecificCharacterCondition c= new MeetSpecificCharacterCondition(sphinx);
//	 	winningConditions.add(c);
	 	 EarnGoldCondition e = new EarnGoldCondition(player, 10);
	 	 winningConditions.add(e);

	 	Quest quest = new Quest(maze.getRandomCell(), winningConditions);
	 	List<Item> items = new ArrayList<>();

	 	sphinx.setHint(new WinningCellCoordinatesHint(quest.getWinningCell(), false, true));
	 	sphinx.addEnigma(new AnswerEnigma("Quelle est le nom de famille de Timo ?", "Léon"));

	 	altruist.setHint(new WinningCellCoordinatesHint(quest.getWinningCell(), true, true));
	 	NPCs.add(altruist);

	 	 Fool fool = new Fool(maze.getRandomCell());
	 	 fool.setHint(new FakeHint("Pour gagner il faut aller à la case (5,7)"));
	 	 NPCs.add(fool);

		 sphinx.setHint(new WinningCellCoordinatesHint(quest.getWinningCell(), false, true));
		 List<String> reponses = new ArrayList<>();
		 reponses.add("A16");
		 reponses.add("B589");
		 reponses.add("A10");
		 reponses.add("A00");
		 sphinx.addEnigma(new QCMEnigma("Quelle est la salle de travail du M5 ? ", reponses, reponses.get(2)));
		 //sphinx.addEnigma(new Answer("Quelle est le nom de famille de Timo ?", "Léon"));
		 NPCs.add(sphinx);


	 	Trader trader = new Trader(maze.getRandomCell());
	 	trader.addParchment(new Parchment(new QuestConditionHint(e)));
	 	NPCs.add(trader);


	 	Jewel jewel = new Jewel(JewelRarity.BLUE, maze.getCell(0, 0));
	 	items.add(jewel);

		 Jewel jewel2 = new Jewel(JewelRarity.GREEN, maze.getRandomCell());
		 items.add(jewel2);

	 	return new Level(player, maze, quest, NPCs, items);
	 }

	private void assignItemToCharacter(){
		int h = 0;
		int a = 0;
		int f = 0;
		int fh = 0;
		int s = 0;
		int e = 0;

		while ( a < this.altruists.size()){
			this.altruists.get(a).setHint(this.hints.get(h));
			a++;
			h++;
		}
		while ( s < this.sphinxs.size() ){
			this.sphinxs.get(s).setHint(this.hints.get(h));
			if (this.enigmes.get(e) == null){
				System.out.println("----------------------ERROR: on a pas créer assez d'enigme pour les mettres dans les sphinxs.-----------------------------");
			}
			this.sphinxs.get(s).addEnigma(this.enigmes.get(e));
			e++;
			s++;
			h++;
		}
		while( f < this.fools.size()-1){
			this.fools.get(f).setHint(this.fakeHints.get(fh));
			fh++;
			f++;
		}
	}




		// while(i< this.characters.size()){
		// 	if ( ! (characters.get(i) instanceof Trader) ){
		// 		// Sphinx characterTemp = (Sphinx) characters.get(i);
		// 		// this.characters.remove(i);
		// 		// this.characters.add(i, characterTemp);
		// 		if(j < this.hints.size()){
		// 			// characterTemp.setHint(hints.get(j));
		//
		// 			this.characters.get(i).setHint(hints.get(j));
		// 			j++;
		// 		}
		// 		else{
		// 			System.out.println("erreur il n'y a plus d'indice à donner");
		// 		}
		// 	}
		//
		// 	if ( characters.get(i) instanceof Trader ){
		// 			while(p < this.items.size() && !(items.get(p) instanceof Parchment) ){
		// 				p++;
		// 			}
		// 			if (p < this.items.size()){
		// 				this.characters.get(i).addParchment(items.get(p));
		// 				p++;
		// 			}
		// 			else{
		// 				System.out.println("erreur il n'y a plus de parchemin à donner");
		// 			}
		// 	}
		// 	if ( characters.get(i) instanceof Sphinx ){
		// 		if(e < this.enigmes.size()){
		// 			this.characters.get(i).addEnigma(this.enigmes.get(e));
		// 			e++;
		// 		}
		// 		else{
		// 			System.out.println("erreur il n'y a plus d'énigme à donner");
		// 		}
		// 	}
		// 	//altruiste
		// 	//fool
		//
		// 	// trader --> parchemin
		//
		// 	//sphinx indice et enigme
		//
		// 	i++;
		// }



	private Quest createQuest() {
		Cell winningCell = this.maze.getRandomCell();
		List<QuestCondition> conditions = this.createQuestConditions();

		return new Quest(winningCell, conditions);
	}

	//crée les hints et les fakeHints
	//nbrItemTotaleParam doit est au moins égale à 4
	private List<Hint> createHints (int nbrItemTotaleParam, int nbFools){
	// private List<Hint> createHints (int nbrItemTotaleParam, int nbrItemHint, int nbFools){
		this.hints = new ArrayList<>();
		this.fakeHints = new ArrayList<>();
		int nbrItemTotale = 0;
		Hint winningCellCoordinatesHintA = new WinningCellCoordinatesHint(this.quest.getWinningCell(), true, false);
		this.hints.add(winningCellCoordinatesHintA);
		Hint winningCellCoordinatesHintO = new WinningCellCoordinatesHint(this.quest.getWinningCell(), false, true);
		this.hints.add(winningCellCoordinatesHintO);
		// Hint winningCellOrientationHint = new WinningCellOrientationHint(this.quest.getWinningCell(), this.player);
		// this.hints.add(winningCellOrientationHint);
		// Hint distanceFromWinningCellHint = new DistanceFromWinningCellHint(this.quest.getWinningCell(), this.player);
		// this.hints.add(distanceFromWinningCellHint);
		nbrItemTotale +=2;
		// les items sont créés après les hints donc on peut pas les utiliser lors de la construction MODIF
		// for (int i = 0; i < nbrItemHint; i++){
		// 	int indice = Random.randInt(0, items.size()-1);
		// 	Hint itemPositionHint = new ItemPositionHint(items.get(indice));
		// 	this.hints.add(itemPositionHint);
		// }
		int j = 0;
		while ( (nbrItemTotale < nbrItemTotaleParam) && (j < this.quest.getWinningConditions().size()) ){
			System.out.println("je suis dans LevelGenerator et je créer un indice questConditionHint");
			Hint questConditionHint = new QuestConditionHint(this.quest.getWinningConditions().get(j));
			this.hints.add(questConditionHint);
			j++;
			nbrItemTotale +=1;
		}
		for (int i = 0; i < nbFools; i++){
			int x = Random.randInt(0, maze.getLength());
			int y = Random.randInt(0, maze.getHeight());
			FakeHint fakeHint = new FakeHint("Pour gagner il faut aller à la case (" + x + "," + y + ")");
			this.fakeHints.add(fakeHint);
		}
		return this.hints;
	}

	private List<QuestCondition> createQuestConditions() {
		int nbConditions = 1;
		// int nbConditions = Random.randInt(0, 2); // The number of conditions to fulfill we want for the quest
		List<QuestCondition> conditions = new ArrayList<>();

		// Create a list of the possible conditions to add to the quest
		List<String> availableConditions = new ArrayList<>();
		availableConditions.add(EarnGoldCondition.class.getSimpleName());
		availableConditions.add(MeetSpecificCharacterCondition.class.getSimpleName());

		for (int i = 0; i < nbConditions; ++i) {
			int randomConditionIndex = 0;
			// int randomConditionIndex = Random.randInt(0, availableConditions.size()-1);
			String className = availableConditions.get(randomConditionIndex);

			switch (className) {
				case "EarnGoldCondition":
					int goldRequired = Random.randInt(5, 20);
					conditions.add(new EarnGoldCondition(this.player, goldRequired));
					break;
					// A MODIF car this.characters n'existe plus
				// case "MeetSpecificCharacterCondition":
				// 	int randomCharacterIndex = Random.randInt(0, this.characters.size()-1);
				// 	conditions.add(new MeetSpecificCharacterCondition(this.characters.get(randomCharacterIndex)));
				// 	break;
			}
		}

		return conditions;
	}

	private List<Item> createItems(int nbJewels) {
		List<Item> items = new ArrayList<>();

		for (int i = 0; i < this.hints.size(); ++i) {
			items.add(new Parchment(this.hints.get(i)));
		}
		for (int i = 0; i < nbJewels; ++i) {
			Cell jewelPosition = this.maze.getRandomCell();
			JewelRarity[] rarities = JewelRarity.values();
			JewelRarity rarity = rarities[Random.randInt(0, rarities.length-1)];// (les bornes sont inclues)
			items.add(new Jewel(rarity, jewelPosition));
		}
		return items;
	}

	private List<Enigma> createEnigmas(){
		this.enigmes = new ArrayList <Enigma>();
		List<String> reponses = new ArrayList<>();
		Enigma enigme1 = new AnswerEnigma("Quelle est le nom de famille de Timo ?", "Léon");
		Enigma enigme2 = new AnswerEnigma("Quelle est la couleur du cheval blanc d'Henry IV ?", "Blanc");
		reponses.add("A16");
		reponses.add("B589");
		reponses.add("A10");
		reponses.add("A00");
		Enigma enigme3 = new QCMEnigma("Quelle est la salle de travail du M5 ? ", reponses, reponses.get(2));

		this.enigmes.add(enigme1);
		this.enigmes.add(enigme2);
		this.enigmes.add(enigme3);


		return this.enigmes;
	}

	/*
	private <T> void createCharacterType(List<T> characters, int number) {
		for (int i = 0; i < number; ++i) {
			characters.add(new T(this.maze.getRandomCell()));
		}
	}*/

		private List<Character> getCharacters(){
			List<Character> characters = new ArrayList<Character>();
			characters.addAll(this.traders);
			characters.addAll(this.sphinxs);
			characters.addAll(this.fools);
			characters.addAll(this.altruists);
			return characters;
		}

	void createCharacters(int nbTrader, int nbSphinx, int nbFool, int nbAltruist) {
	this.traders = new ArrayList<>();
	this.sphinxs = new ArrayList<>();
	this.fools = new ArrayList<>();
	this.altruists = new ArrayList<>();

		// Create all traders
		for (int i = 0; i < nbTrader; ++i) {
			this.traders.add(new Trader(this.maze.getRandomCell()));
		}

		// Create all sphinx
		for (int i = 0; i < nbSphinx; ++i) {
			this.sphinxs.add(new Sphinx(this.maze.getRandomCell()));
		}

		// Create all fools
		for (int i = 0; i < nbFool; ++i) {
			this.fools.add(new Fool(this.maze.getRandomCell()));
		}

		// Create all altruists
		for (int i = 0; i < nbAltruist; ++i) {
			this.altruists.add(new Altruist(this.maze.getRandomCell()));
		}
	}
}
