package game.character;

import game.Level;
import game.maze.Cell;
import game.maze.KruskalMaze;
import game.maze.Maze;
import game.quest.Quest;
import utils.Tester;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestNonPlayerCharacter extends Tester {
    @Test
    public void testNPCMovesWhenPlayerNotInSameCell() {
        Maze maze = new KruskalMaze(2, 1);

        Player player = new Player("Sid");
        NonPlayerCharacter npc = new NonPlayerCharacter("NPC", maze.getCell(1, 0)) {};

        List<NonPlayerCharacter> NPCs = new ArrayList<>();
        NPCs.add(npc);

        Level level = new Level(
                player,
                maze,
                new Quest(maze.getCell(0, 0), new ArrayList<>()),
                NPCs,
                new ArrayList<>(),
                new ArrayList<>()
        );

        Cell npcOldCell = npc.getCurrentCell();
        npc.update(level); // The NPC moves

        assertNotEquals(npcOldCell, npc.getCurrentCell());
    }
}
