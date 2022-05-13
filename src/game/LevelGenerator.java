package game;

import game.hint.fake.*;
import game.item.*;
import game.enigma.*;
import game.quest.*;
import game.maze.*;
import game.character.*;
import game.hint.*;
import game.util.parser.EnigmaParser;
import game.util.Random;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

public class LevelGenerator {

	private Maze maze;
	private Quest quest;
	private Player player;

	private List<Altruist> altruists ;
	private List<Fool> fools;
	private List<Sphinx> sphinxes;
	private List<Trader> traders;

	private List<Hint> hints;
	private List<FakeHint> fakeHints;
	private List<Item> items;
	private EnigmaManager enigmaManager;

	public Level generateLevel(Player player, MazeAlgorithm algorithm) {
		this.hints = new ArrayList <Hint>();
		this.fakeHints = new ArrayList <FakeHint>();
		this.items = new ArrayList <Item>();
		this.enigmaManager = new EnigmaManager();

		this.player = player;

		// Create the maze with the chosen generation algorithm
		switch (algorithm) {
			case DEPTH_FIRST_SEARCH:
				this.maze = new DepthFirstSearchMaze(9,4); // Length and height required in project specifications
				break;
			case KRUSKAL_SEARCH:
				this.maze = new KruskalMaze(10, 10); // Length and height required in project specifications
				break;
		}

		// Create enigmas
		this.createEnigmas();

		// Create characters
		this.createCharacters();

		// Create items (need characters)
		this.createItems();

		// Create the quest (need characters)
		this.createQuest();

		// Create hints (need the quest)
		this.createHints();

		// Assign hints, parchments to characters
		this.assignHintsToNPCs();

		this.checkGoldInGame();

		// Gather hints with fake hints
		List<Hint> allHints = new ArrayList<>();
		allHints.addAll(this.hints);
		allHints.addAll(this.fakeHints);

		return new Level(player, this.maze, this.quest, this.getNonPlayerCharacters(), this.items, allHints);
	}

	private void assignHintsToNPCs() {
		int hintIndex = 0;
		int fakeHintIndex = 0;
		int traderIndex = 0;

		// Assign a hint to each altruist
		for (Altruist altruist : this.altruists) {
			altruist.setHint(this.hints.get(hintIndex));
			hintIndex++;
		}

		// Assign a hint to each sphinx
		for (Sphinx sphinx : this.sphinxes) {
			sphinx.setHint(this.hints.get(hintIndex));
			hintIndex++;
		}

		// Assign a fake hint to each fool
		for (Fool fool : this.fools) {
			fool.setHint(this.fakeHints.get(fakeHintIndex));
			fakeHintIndex++;
		}

		// Assign each remaining hint to a parchment, and assign parchments to each trader
		while (hintIndex < this.hints.size()) {
			Parchment parchment = new Parchment(this.hints.get(hintIndex));
			this.traders.get(traderIndex).addParchment(parchment);

			traderIndex = (traderIndex+1) % this.traders.size();
			++hintIndex;
		}
	}


	private void createQuest() {
		Cell winningCell = this.maze.getCell(8, 3); // Required in project specifications
		List<QuestCondition> conditions = this.createQuestConditions();

		this.quest = new Quest(winningCell, conditions);
	}


	private void createHints (){
		this.hints = new ArrayList<>();
		this.fakeHints = new ArrayList<>();

		int hintsCreated = 0;

		// Choose between giving winning cell orientation or coordinates
		switch (Random.randInt(0, 1)) {
			case 0: // Hint for winning cell orientation and distance from player
				this.hints.add(new WinningCellOrientationHint(this.quest.getWinningCell(), this.player));
				this.hints.add(new DistanceFromWinningCellHint(this.quest.getWinningCell(), this.player));
				hintsCreated += 2;
				break;
			case 1: // Hint for winning cell coordinates
				this.hints.add(new WinningCellCoordinatesHint(this.quest.getWinningCell(), true, false));
				this.hints.add(new WinningCellCoordinatesHint(this.quest.getWinningCell(), false, true));
				hintsCreated += 2;
		}

		for (QuestCondition condition : this.quest.getWinningConditions()) {
			this.hints.add(new QuestConditionHint(condition));
			++hintsCreated;
		}

		int itemIndex = 0;

		// Create hints for items position to reach the number of hints required
		while (hintsCreated < this.getNonPlayerCharacters().size()) {
			this.hints.add(new ItemPositionHint(this.items.get(itemIndex)));
			++itemIndex;
			++hintsCreated;
		}

		// les items sont créés après les hints donc on peut pas les utiliser lors de la construction MODIF
		// for (int i = 0; i < nbrItemHint; i++){
		// 	int indice = Random.randInt(0, items.size()-1);
		// 	Hint itemPositionHint = new ItemPositionHint(items.get(indice));
		// 	this.hints.add(itemPositionHint);
		// }

		// Create a fake hint for each fool
		for (int i = 0; i < this.fools.size(); i++){
			FakeHint fakeHint = new FakeWinningCellCoordinatesHint(this.maze.getLength(), this.maze.getHeight(), this.quest.getWinningCell());
			this.fakeHints.add(fakeHint);
		}
	}


	private List<QuestCondition> createQuestConditions() {
		// Create a number of quest conditions equal to half the number of NPCs
		int nbConditions = this.getNonPlayerCharacters().size() / 2;
		if (nbConditions == 0) nbConditions = 1;

		List<QuestCondition> conditions = new ArrayList<>();
		conditions.add(new EarnGoldCondition(this.player, 5)); // Required in project specifications

		// Create a list of the possible conditions to add to the quest
		List<String> availableConditions = new ArrayList<>();
		availableConditions.add(MeetSpecificCharacterCondition.class.getSimpleName());

		for (int i = conditions.size(); i < nbConditions; ++i) {
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
			if(item.getClass().getSimpleName().equals("Jewel")){
				valeurtotale += ((Jewel)item).getRarity().getGoldValue();
			}
		}

		int goldminimal = 0;

		for(QuestCondition condition : this.quest.getWinningConditions() ){
			if (condition.getClass().getSimpleName().equals("EarnGoldCondition")){
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
	private void createItems() {
		/*
		 * Compute the number of items to create
		 * There must be the same amount of characters and items, and their total must equal half the number of cells
		 */
		int nbItemsToCreate = this.maze.getLength() * this.maze.getHeight() / 2 - this.getNonPlayerCharacters().size();
		this.items = new ArrayList<>();

		// Create all jewels
		for (int i = 0; i < nbItemsToCreate; ++i) {
			Cell jewelPosition = this.maze.getRandomCell();
			JewelRarity[] rarities = JewelRarity.values();
			JewelRarity rarity = rarities[Random.randInt(0, rarities.length-1)];// (les bornes sont inclues)
			this.items.add(new Jewel(rarity, jewelPosition));
		}
	}

	private void createEnigmas() {
		try {
			EnigmaParser parser = new EnigmaParser();
			this.enigmaManager = new EnigmaManager(parser.parse("data/enigmas.json"));
		}

		catch (IOException | ParseException e) {
			System.out.println(e.getMessage());
		}
	}

	private List<NonPlayerCharacter> getNonPlayerCharacters(){
		List<NonPlayerCharacter> NPCs = new ArrayList<>();
		NPCs.addAll(this.traders);
		NPCs.addAll(this.sphinxes);
		NPCs.addAll(this.fools);
		NPCs.addAll(this.altruists);
		return NPCs;
	}

	private void createCharacters() {
		int nbDifferentNPCs = 4; // Trader, sphinx, fool, altruist = 4 different NPCs
		int nbNPCPerType = (int)(this.maze.getHeight() * this.maze.getLength() / 4 / nbDifferentNPCs);

		if (nbNPCPerType == 0) {
			this.createCharacters(1, 1, 0, 0);
		}

		else {
			this.createCharacters(nbNPCPerType, nbNPCPerType, nbNPCPerType, nbNPCPerType);
		}
	}

	private void createCharacters(int nbTrader, int nbSphinx, int nbFool, int nbAltruist) {
		this.traders = new ArrayList<>();
		this.sphinxes = new ArrayList<>();
		this.fools = new ArrayList<>();
		this.altruists = new ArrayList<>();
		List<Cell> cellsUsed = new ArrayList<>();

		// Create all traders
		for (int i = 0; i < nbTrader; ++i) {
			int basePrice = Random.randInt(1, 5);
			int priceMultiplicator = Random.randInt(2, 3);
			this.traders.add(new Trader(this.randomCellNotAlreadyUsed(cellsUsed), basePrice, priceMultiplicator));
		}

		// Create all sphinx
		for (int i = 0; i < nbSphinx; ++i) {
			this.sphinxes.add(new Sphinx(this.randomCellNotAlreadyUsed(cellsUsed), this.enigmaManager));
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
