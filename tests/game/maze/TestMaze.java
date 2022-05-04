package game.maze;

import org.junit.*;

import static org.junit.Assert.*;

public class TestMaze {

    private Maze maze;

    @Before
    public void before() {
        // Create an anonymous concrete class of Maze
        this.maze = new Maze(3, 3) {
            @Override
            protected void generate() {}
        };
    }

    @Test
    public void testAllWallInMazeWhenCreated() {
        for (int i = 0; i < maze.getLength(); i++) {
            for (int j = 0; j < maze.getHeight(); j++) {
                assertTrue(maze.getCell(i, j).hasEastWall());
                assertTrue(maze.getCell(i, j).hasNorthWall());
                assertTrue(maze.getCell(i, j).hasSouthWall());
                assertTrue(maze.getCell(i, j).hasWestWall());
            }
        }
    }

    @Test
    public void testRemoveWallWithOrientation() {
        // We assume that cells have walls in all direction when created
        Cell cell = maze.getCell(1, 1);
        Cell cellNorth = maze.getCell(1, 0);
        Cell cellSouth = maze.getCell(1, 2);
        Cell cellWest = maze.getCell(0, 1);
        Cell cellEast = maze.getCell(2, 1);

        // Check walls are correctly removed
        maze.removeWall(cell, Orientation.NORTH);
        assertFalse(cell.hasNorthWall());
        assertFalse(cellNorth.hasSouthWall());

        maze.removeWall(cell, Orientation.SOUTH);
        assertFalse(cell.hasSouthWall());
        assertFalse(cellSouth.hasNorthWall());

        maze.removeWall(cell, Orientation.WEST);
        assertFalse(cell.hasWestWall());
        assertFalse(cellWest.hasEastWall());

        maze.removeWall(cell, Orientation.EAST);
        assertFalse(cell.hasEastWall());
        assertFalse(cellEast.hasWestWall());
    }

    @Test
    public void testRemoveWallWithCellAdjacent() throws InvalidAdjacentCellException {
        // We assume that cells have walls in all direction when created
        Cell cell = maze.getCell(1, 1);
        Cell cellNorth = maze.getCell(1, 0);
        Cell cellSouth = maze.getCell(1, 2);
        Cell cellWest = maze.getCell(0, 1);
        Cell cellEast = maze.getCell(2, 1);

        // Check walls are correctly removed
        maze.removeWall(cell, cellNorth);
        assertFalse(cell.hasNorthWall());
        assertFalse(cellNorth.hasSouthWall());

        maze.removeWall(cell, cellSouth);
        assertFalse(cell.hasSouthWall());
        assertFalse(cellSouth.hasNorthWall());

        maze.removeWall(cell, cellWest);
        assertFalse(cell.hasWestWall());
        assertFalse(cellWest.hasEastWall());

        maze.removeWall(cell, cellEast);
        assertFalse(cell.hasEastWall());
        assertFalse(cellEast.hasWestWall());
    }

    @Test(expected = InvalidAdjacentCellException.class)
    public void testRemoveWallBetweenNonAdjacentCellsThrowsException() throws InvalidAdjacentCellException {
        Cell cell = maze.getCell(0, 0);
        Cell nonAdjacentCell = maze.getCell(2, 2);

        maze.removeWall(cell, nonAdjacentCell);
    }

    @Test
    public void testIsExternalWall() {
        Cell cell = maze.getCell(0, 0);
        Cell cell2 = maze.getCell(2, 2);

        assertTrue(maze.isExternalWall(cell, Orientation.WEST));
        assertTrue(maze.isExternalWall(cell, Orientation.NORTH));
        assertFalse(maze.isExternalWall(cell, Orientation.EAST));
        assertFalse(maze.isExternalWall(cell, Orientation.SOUTH));

        assertFalse(maze.isExternalWall(cell2, Orientation.WEST));
        assertFalse(maze.isExternalWall(cell2, Orientation.NORTH));
        assertTrue(maze.isExternalWall(cell2, Orientation.EAST));
        assertTrue(maze.isExternalWall(cell2, Orientation.SOUTH));
    }

    @Test
    public void testGetCell() {
        Cell cell = maze.getCell(1, 2);
        Cell cell2 = maze.getCell(2, 1);

        assertEquals(1, cell.getX());
        assertEquals(2, cell.getY());

        assertEquals(2, cell2.getX());
        assertEquals(1, cell2.getY());
    }

    @Test
    public void testGetNbCell() {
        assertEquals(9, maze.getNbCell());
    }
}
