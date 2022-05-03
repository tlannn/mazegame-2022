// changer partout ou c'est mis MODIF
// on ne rencontre jamais de personnages
package game;

import game.hint.fake.FakeHint;
import game.hint.fake.FakeWinningCellCoordinatesHint;
import game.item.*;
import game.enigma.*;

//import game.enigma.Hint;

import game.quest.*;
import game.maze.*;
import game.character.*;
import game.hint.*;
import game.util.Random;

import java.util.ArrayList;
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
	private List <Hint> allHints;
	private List<FakeHint> fakeHints;
	private List<Item> items;
	private List<Enigma> enigmes;


	public Level generateLevel(Player player){
		//on remet toutes les listes à 0 pour si on a déjà créer un level au par avant
		// this.altruists = new ArrayList<Altruist>();
		// this.fools = new ArrayList <Fool>();
		// this.sphinxs = new ArrayList <Sphinx>();
		// this.traders = new ArrayList <Trader>();

		this.hints = new ArrayList <Hint>();
		this.allHints = new ArrayList <Hint>();
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
		this.maze = new KruskalMaze(4, 4);
		this.player = player;

		//on créer les personnages
		this.createCharacters();

		//on crée la quete (a besoin des perso)
		this.quest = createQuest();

		// Créer les indices (a besoin de la quete)
		// this.createHints( nbSphinx + nbAltruist + nbTrader, nbFools);
		this.createHints(this.sphinxs.size() + this.altruists.size() + this.traders.size(), nbFools);

		//  Créer les items (a besoin de hint)
		this.items = this.createItems(5);

		// créer les enigmes
		this.createEnigmas();

		// assigner des hints/parchment/enigme au characters
		this.assignItemToCharacter();

		//on crée la liste de tous les indices
		this.allHints.addAll(this.hints);
		this.allHints.addAll(this.fakeHints);


		this.checkGoldInGame();


		List <NonPlayerCharacter> NPCs = new ArrayList <NonPlayerCharacter>();
		NPCs.addAll(this.altruists);
		NPCs.addAll(this.fools);
		NPCs.addAll(this.sphinxs);
		NPCs.addAll(this.traders);

		if (nbSphinx + nbAltruist + nbTrader > this.hints.size()){
			System.out.println("-------------------------------------- ERROR : Il n'y a pas assez d'indice !!!!!!!!!!! --------------------------------------");
		}

		System.out.println("Nb NPC : " + NPCs.size());
		System.out.println("Nb hints : " + this.hints.size());
		System.out.println("Nb fakehints : " + this.fakeHints.size());
		System.out.println("Nb items : " + this.items.size());

		return new Level(player, this.maze, this.quest, NPCs, this.items, this.allHints);

	}

	 /*public Level generateLevel(Player player){
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


	 	Trader trader = new Trader(maze.getRandomCell(),5,3);
	 	trader.addParchment(new Parchment(new QuestConditionHint(e)));
	 	NPCs.add(trader);


	 	Jewel jewel = new Jewel(JewelRarity.BLUE, maze.getCell(0, 0));
	 	items.add(jewel);

		Jewel jewel2 = new Jewel(JewelRarity.GREEN, maze.getRandomCell());
		items.add(jewel2);

	 	return new Level(player, maze, quest, NPCs, items);
	}*/

	private void assignItemToCharacter(){
		int h = 0; // Hint index
		int a = 0; // Altruist index
		int f = 0; // Fool index
		int fh = 0; // FakeHint index
		int s = 0; // Sphinx index
		int t = 0; // Trader index
		int e = 0; // Enigma index

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
		while( f < this.fools.size()){
			this.fools.get(f).setHint(this.fakeHints.get(fh));
			fh++;
			f++;
		}

		for (Item item : this.items) {
			if(item.getClass().getSimpleName().equals("Parchment")){
				this.traders.get(t).addParchment((Parchment)item);
				t=(t+1)%this.traders.size();
			}
		}

	}


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
		// Hint winningCellCoordinatesHintO = new WinningCellCoordinatesHint(this.quest.getWinningCell(), false, true);
		// this.hints.add(winningCellCoordinatesHintO);




		// Hint winningCellOrientationHint = new WinningCellOrientationHint(this.quest.getWinningCell(), this.player);
		// this.hints.add(winningCellOrientationHint);
		Hint distanceFromWinningCellHint = new DistanceFromWinningCellHint(this.quest.getWinningCell(), this.player);
		this.hints.add(distanceFromWinningCellHint);
		nbrItemTotale +=2;
		// les items sont créés après les hints donc on peut pas les utiliser lors de la construction MODIF
		// for (int i = 0; i < nbrItemHint; i++){
		// 	int indice = Random.randInt(0, items.size()-1);
		// 	Hint itemPositionHint = new ItemPositionHint(items.get(indice));
		// 	this.hints.add(itemPositionHint);
		// }
		int j = 0;
		while ( (nbrItemTotale < nbrItemTotaleParam) && (j < this.quest.getWinningConditions().size()) ){
			Hint questConditionHint = new QuestConditionHint(this.quest.getWinningConditions().get(j));
			this.hints.add(questConditionHint);
			j++;
			nbrItemTotale +=1;
		}
		for (int i = 0; i < this.fools.size(); i++){
			FakeHint fakeHint = new FakeWinningCellCoordinatesHint(this.maze.getLength(), this.maze.getHeight(), this.quest.getWinningCell());
			this.fakeHints.add(fakeHint);
		}
		return this.hints;
	}


	private List<QuestCondition> createQuestConditions() {
		// Create a number of quest conditions equal to half the number of NPCs
		int nbConditions = this.getNonPlayerCharacters().size() / 2;
		if (nbConditions == 0) nbConditions = 1;
		List<QuestCondition> conditions = new ArrayList<>();

		// Create a list of the possible conditions to add to the quest
		List<String> availableConditions = new ArrayList<>();
		availableConditions.add(EarnGoldCondition.class.getSimpleName());
		availableConditions.add(MeetSpecificCharacterCondition.class.getSimpleName());

		for (int i = 0; i < nbConditions; ++i) {
			int randomConditionIndex = Random.randInt(0, availableConditions.size()-1);
			String className = availableConditions.get(randomConditionIndex);

			switch (className) {
				case "EarnGoldCondition":
					availableConditions.remove(className); // Remove this condition so that there cannot be multiple EarnGoldCondition
					int goldRequired = Random.randInt(5, 20);
					conditions.add(new EarnGoldCondition(this.player, goldRequired));
					break;
				case "MeetSpecificCharacterCondition":
					List<NonPlayerCharacter> NPCs = this.getNonPlayerCharacters();

					int randomCharacterIndex = Random.randInt(0, NPCs.size()-1);
					conditions.add(new MeetSpecificCharacterCondition(NPCs.get(randomCharacterIndex)));
					break;
			}
		}

		return conditions;
	}

	private void checkGoldInGame(){
		int valeurtotale = 0 ;

		for (Item item : this.items) {
			if(item.getClass().getSimpleName() == "Jewel"){
				valeurtotale += ((Jewel)item).getRarity().getGoldValue();
			}
		}

		int goldminimal = 0;

		for(QuestCondition condition : this.quest.getWinningConditions() ){
			if (condition.getClass().getSimpleName() == "EarnGoldCondition"){
				goldminimal = ((EarnGoldCondition)condition).getGoldRequired();
			}
		}

		for(Trader trader : this.traders){
			goldminimal += trader.getTotalGoldRequired();
		}

		while (valeurtotale < goldminimal){
			Cell jewelPosition = this.maze.getRandomCell();
			JewelRarity[] rarities = JewelRarity.values();
			JewelRarity rarity = rarities[Random.randInt(0, rarities.length-1)];// (les bornes sont inclues)
			items.add(new Jewel(rarity, jewelPosition));
			valeurtotale += rarity.getGoldValue();
		}

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

	private List<NonPlayerCharacter> getNonPlayerCharacters(){
		List<NonPlayerCharacter> NPCs = new ArrayList<>();
		NPCs.addAll(this.traders);
		NPCs.addAll(this.sphinxs);
		NPCs.addAll(this.fools);
		NPCs.addAll(this.altruists);
		return NPCs;
	}

	private void createCharacters() {
		int nbDifferentNPCs = 4; // Trader, sphinx, fool, altruist = 4 different NPCs
		int nbNPCPerType = (int)Math.sqrt(this.maze.getHeight() * this.maze.getLength()) / nbDifferentNPCs;

		if (nbNPCPerType == 0) {
			this.createCharacters(1, 1, 0, 0);
		}

		else {
			this.createCharacters(nbNPCPerType, nbNPCPerType, nbNPCPerType, nbNPCPerType);
		}
	}

	private void createCharacters(int nbTrader, int nbSphinx, int nbFool, int nbAltruist) {
		this.traders = new ArrayList<>();
		this.sphinxs = new ArrayList<>();
		this.fools = new ArrayList<>();
		this.altruists = new ArrayList<>();
		List<Cell> cellsUsed = new ArrayList<>();

		// Create all traders
		for (int i = 0; i < nbTrader; ++i) {
			int basePrice = Random.randInt(1, 5);
			int priceMultiplicator = Random.randInt(1, 3);
			this.traders.add(new Trader(this.maze.getRandomCell(),basePrice,priceMultiplicator));
		}

		// Create all sphinx
		for (int i = 0; i < nbSphinx; ++i) {
			this.sphinxs.add(new Sphinx(this.randomCellNotAlreadyUsed(cellsUsed)));
		}

		// Create all fools
		for (int i = 0; i < nbFool; ++i) {
			this.fools.add(new Fool(this.randomCellNotAlreadyUsed(cellsUsed)));
		}

		// Create all altruists
		for (int i = 0; i < nbAltruist; ++i) {
			this.altruists.add(new Altruist(this.randomCellNotAlreadyUsed(cellsUsed)));
		}
	}

	private Cell randomCellNotAlreadyUsed(List<Cell> cellsUsed) {
		Cell cellChosen;

		do {
			cellChosen = this.maze.getRandomCell();
		} while (cellsUsed.contains(cellChosen));

		cellsUsed.add(cellChosen);
		return cellChosen;
	}
}
