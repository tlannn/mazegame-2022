package game;

import game.character.Altruist;
import game.character.Character;
import game.character.NonPlayerCharacter;
import game.character.Player;
import game.character.Sphinx;
import game.hint.Hint;
import game.hint.ItemPositionHint;
import game.item.Item;
import game.item.Jewel;
import game.item.JewelRarity;
import game.maze.Cell;
import game.maze.KruskalMaze;
import game.maze.Maze;
import game.maze.Orientation;
import game.observer.ObservableEvent;
import game.quest.Quest;
import game.quest.QuestCondition;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestLevel {
    private Level level;
    private Maze maze;
    private Player player;
    private Altruist altruist;
    private Sphinx sphinx;
    private Jewel jewel;

    @Before
    public void before() {
        this.maze = new KruskalMaze(3, 3);
        this.player = new Player("Samy");
        this.altruist = new Altruist(maze.getCell(0, 0));
        this.sphinx = new Sphinx(maze.getCell(0, 0), null);
        this.jewel = new Jewel(JewelRarity.BLUE, maze.getCell(0, 0));

        List<NonPlayerCharacter> NPCs = new ArrayList<>();
        NPCs.add(this.altruist);
        NPCs.add(this.sphinx);
        List<Item> items = new ArrayList<>();
        items.add(this.jewel);

        this.level = new Level(
                this.player,
                this.maze,
                new Quest(maze.getCell(1, 1), new ArrayList<QuestCondition>()),
                NPCs,
                items,
                new ArrayList<Hint>()
        );
    }

    @Test
    public void testPlayerIsOnTopLeftCellWhenCreated() {
        assertEquals(this.player.getCurrentCell(), this.maze.getCell(0, 0));
    }

    @Test
    public void testCharacterGoToRightDirectionWhenMoving() {
        Cell currentCell = this.player.getCurrentCell();
        Orientation orientation = currentCell.possibleOrientations().get(0);
        this.level.move(this.player, orientation);

        int nextCellX = currentCell.getX();
        int nextCellY = currentCell.getY();

        switch (orientation) {
            case NORTH:
                nextCellY = currentCell.getY() - 1;
                break;
            case SOUTH:
                nextCellY = currentCell.getY() + 1;
                break;
            case EAST:
                nextCellX = currentCell.getX() + 1;
                break;
            case WEST:
                nextCellX = currentCell.getX() - 1;
                break;
        }

        assertEquals(this.maze.getCell(nextCellX, nextCellY), this.player.getCurrentCell());
    }

    @Test
    public void testCharacterStayInPlaceWhenMovingToImpossibleDirection() {
        // Make SOUTH the only possible direction
        Cell currentCell = this.player.getCurrentCell();
        currentCell.setNorthWall(true);
        currentCell.setSouthWall(false);
        currentCell.setEastWall(true);
        currentCell.setWestWall(true);

        // Move player to south
        Orientation orientation = Orientation.SOUTH;
        this.level.move(this.player, orientation);

        assertEquals(this.maze.getCell(currentCell.getX(), currentCell.getY() + 1), this.player.getCurrentCell());
    }

    @Test
    public void testNonMovableCharacterStayInPlaceWhenMoving() {
        Cell currenCell = this.sphinx.getCurrentCell();

        // Move sphinx multiple times
        this.level.move(this.sphinx, Orientation.SOUTH);
        this.level.move(this.sphinx, Orientation.EAST);
        this.level.move(this.sphinx, Orientation.NORTH);

        assertEquals(currenCell, this.sphinx.getCurrentCell());
    }

    @Test
    public void testCharacterChangeCellWhenMoving() {
        Player player = this.level.getPlayer();
        Cell currentCell = player.getCurrentCell();
        assertTrue(currentCell.getCharactersInCell().contains(player));

        Orientation orientation = currentCell.possibleOrientations().get(0);
        this.level.move(player, orientation);

        assertFalse(currentCell.getCharactersInCell().contains(player));
        assertTrue(this.level.getPlayer().getCurrentCell().getCharactersInCell().contains(player));
    }

    @Test
    public void testItemPickedUpRemovedFromCell() {
        Cell jewelCell = this.level.getMaze().getCell(0, 0);
        assertTrue(jewelCell.getItemsInCell().contains(this.jewel));
        this.level.pickUpItem(this.player, this.jewel);

        assertFalse(jewelCell.getItemsInCell().contains(this.jewel));
    }

    @Test
    public void testItemPickedUpGoesToInventory() {
        assertFalse(this.player.getInventory().getItems().contains(this.jewel));
        this.level.pickUpItem(this.player, this.jewel);
        assertTrue(this.player.getInventory().getItems().contains(this.jewel));
    }

    @Test
    public void testHintDiscoveredIsSaved() {
        Hint hint = new ItemPositionHint(jewel);
        assertFalse(this.level.getHints().contains(hint));
        this.level.onNotify(hint, ObservableEvent.EVENT_HINT_DISCOVERED);
        assertTrue(this.level.getHints().contains(hint));
    }
}
