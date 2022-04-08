package game;

import game.character.Character;
import game.enigma.Enigma;
import game.hint.Hint;
import game.item.Item;
import game.item.Jewel;
import game.item.JewelRarity;
import game.item.Parchment;
import game.quest.*;
import game.maze.*;
import game.character.*;
import game.util.Random;

import java.util.ArrayList;
import java.util.List;

// quand on ajoute le player il ne faut pas oublier que la case sur laquelle on l'ajoute le player doit avoir player dans ses characters

public class GameGenerator {

	private Maze maze;
	private Quest quest;
	private Player player;
	private List<Character> characters;
	private List<Hint> hints;
	private List<Item> items;

	public GameGenerator() {
		// 1. Créer le labyrinthe
		this.maze = new KruskalMaze(5, 5);

		// 2. Créer les personnages
		this.characters = this.createCharacters(1, 1, 1, 1);

		// 3. Créer les items
		this.items = this.createItems(5);

		// 4. Créer la quête

		// 5. Assigner les items

	}

	private Quest createQuest() {
		Cell winningCell = this.maze.getRandomCell();
		List<QuestCondition> conditions = this.createQuestConditions();

		return new Quest(winningCell, conditions);
	}

	private List<QuestCondition> createQuestConditions() {
		int nbConditions = Random.randInt(0, 2); // The number of conditions to fulfill we want for the quest
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
					int goldRequired = Random.randInt(5, 20);
					conditions.add(new EarnGoldCondition(this.player, goldRequired));
					break;
				case "MeetSpecificCharacterCondition":
					int randomCharacterIndex = Random.randInt(0, this.characters.size()-1);
					conditions.add(new MeetSpecificCharacterCondition(this.characters.get(randomCharacterIndex)));
					break;
			}
		}

		return conditions;
	}

	private List<Hint> createHints() {
		// TODO
		return new ArrayList<>();
	}

	private List<Item> createItems(int nbJewels) {
		List<Item> items = new ArrayList<>();

		for (int i = 0; i < nbJewels; ++i) {
			Cell jewelPosition = this.maze.getRandomCell();

			JewelRarity[] rarities = JewelRarity.values();
			JewelRarity rarity = rarities[Random.randInt(0, rarities.length)];
			items.add(new Jewel(rarity, jewelPosition));
		}

		for (int i = 0; i < this.hints.size(); ++i) {
			items.add(new Parchment(this.hints.get(i)));
		}

		return items;
	}

	/*private <T> void createCharacterType(List<T> characters, int number) {
		for (int i = 0; i < number; ++i) {
			characters.add(new T(this.maze.getRandomCell()));
		}
	}*/

	private List<Character> createCharacters(int nbTrader, int nbSphinx, int nbFool, int nbAltruist) {
		List<Character> characters = new ArrayList<>();
		// Create all traders
		for (int i = 0; i < nbTrader; ++i) {
			characters.add(new Trader(this.maze.getRandomCell()));
		}

		// Create all sphinx
		for (int i = 0; i < nbSphinx; ++i) {
			characters.add(new Sphinx(this.maze.getRandomCell()));
		}

		// Create all fools
		for (int i = 0; i < nbFool; ++i) {
			characters.add(new Fool(this.maze.getRandomCell()));
		}

		// Create all altruists
		for (int i = 0; i < nbAltruist; ++i) {
			characters.add(new Altruist(this.maze.getRandomCell()));
		}

		return characters;
	}
}
