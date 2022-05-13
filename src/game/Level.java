package game;

import game.character.Character;
import game.character.NonPlayerCharacter;
import game.character.Player;
import game.hint.Hint;
import game.item.Item;
import game.maze.Cell;
import game.maze.Maze;
import game.maze.Orientation;
import game.quest.Quest;
import game.quest.QuestCondition;
import game.observer.Observer;
import game.observer.Observable;
import game.observer.ObservableEvent;
import static game.observer.ObservableEvent.EVENT_HINT_DISCOVERED;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Level implements Observer{
    private Quest quest;
    private Maze maze;
    private Player player;
    private List<NonPlayerCharacter> NPCs;
    private List<Item> items;
    private List<Hint> hintsSeen;

    private Map<Hint, Boolean> hints;

    private List<Cell> cellsVisited;

    public Level(Player player, Maze maze, Quest quest, List<NonPlayerCharacter> NPCs, List<Item> items, List<Hint> hints) {
        this.player = player;
        this.maze = maze;
        this.quest = quest;
        this.NPCs = NPCs;
        this.items = items;
        this.hintsSeen = new ArrayList<Hint>();

        // on initialise les observers
        for (QuestCondition condition : this.quest.getWinningConditions()){
            player.addObserver(condition);
            for (NonPlayerCharacter npc : this.NPCs) {
                npc.addObserver(condition);
            }
        }

        for (Hint hint : hints){
            hint.addObserver(this);
        }

        this.player.setCurrentCell(this.maze.getCell(0, 0));
        this.maze.getCell(0, 0).addCharacter(this.player);

        this.cellsVisited = new ArrayList<>();
        this.cellsVisited.add(this.player.getCurrentCell());
    }

    /**
     * When we discovered a hint we add this hint to attribut of level "hintsSeen"
     * @param observable the hint we want add
     * @param event the event received (in this case is always EVENT_HINT_DISCOVERED)
     */
    public void onNotify(Observable observable, ObservableEvent event) {
      boolean present = false;
        if(event == EVENT_HINT_DISCOVERED) {
            Hint hint = (Hint) observable;
            for (Hint monHint : this.hintsSeen){
              if (monHint.toString().equals(hint.toString())){
                present = true;
              }
            }
            if (!present){
              this.hintsSeen.add(hint);
            }



            //
            // if (!this.hintsSeen.contains(hint))
            //     this.hintsSeen.add(hint);
        }
    }

    public List<Hint> getHints(){
        return this.hintsSeen;
    }

    public boolean move(Character character, Orientation orientation) {
        if (character.isMovable() && character.getCurrentCell().possibleOrientations().contains(orientation) ) {
            int x = character.getCurrentCell().getX();
            int y = character.getCurrentCell().getY();

            // On supprime le character de la liste des persos présents dans la cellule courante
            try {
                character.getCurrentCell().removeCharacter(character);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (orientation == Orientation.EAST) {
                character.setCurrentCell(this.maze.getCell(x + 1, y));
            } else if (orientation == Orientation.WEST) {
                character.setCurrentCell(this.maze.getCell(x - 1, y));
            } else if (orientation == Orientation.NORTH) {
                character.setCurrentCell(this.maze.getCell(x, y - 1));
            } else if (orientation == Orientation.SOUTH) {
                character.setCurrentCell(this.maze.getCell(x, y + 1));
            }

            if (character instanceof Player) {
                //character.getCurrentCell().markVisited();
                this.cellsVisited.add(character.getCurrentCell());
            }

            // On ajoute le character à la liste des persos dans la nouvelle cellule
            character.getCurrentCell().addCharacter(character);

            return true;
        }

        return false;
    }

    public void pickUpItem(Character character, Item item) {
        try {
            item.getCurrentCell().removeItem(item);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ((Player) character).getInventory().addItem(item);
    }

    public boolean isCellVisited(Cell cell) {
        return this.cellsVisited.contains(cell);
    }

    public Quest getQuest() {
        return this.quest;
    }

    public Maze getMaze() {
        return this.maze;
    }

    public Player getPlayer() {
        return this.player;
    }

    public List<NonPlayerCharacter> getNPCs() {
        return this.NPCs;
    }

    public List<Item> getItems() {
        return this.items;
    }
}
