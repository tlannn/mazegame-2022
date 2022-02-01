package game.maze;

import org.junit.*;
import static org.junit.Assert.*;

import game.maze.*;

public class TestCell {
	/* Constructor tests */
	@Test
	public void testPossessWallsWhenCreated() {
		Cell cell = new Cell(0, 0);
		assertTrue(cell.hasNorthWall());
		assertTrue(cell.hasSouthWall());
		assertTrue(cell.hasEastWall());
		assertTrue(cell.hasWestWall());
	}

	@Test
	public void testCorrectCoordinatesWhenCreated() {
		Cell cell = new Cell(3, 5);
		assertEquals(cell.getX(), 3);
		assertEquals(cell.getY(), 5);

		cell = new Cell(8, 2);
		assertEquals(cell.getX(), 8);
		assertEquals(cell.getY(), 2);
	}

	/* Getters tests */

	@Test
	public void testGetX() {
		Cell cell = new Cell(3, 5);
		assertEquals(cell.getX(), 3);

		cell = new Cell(8, 2);
		assertEquals(cell.getX(), 8);
	}

	@Test
	public void testGetY() {
		Cell cell = new Cell(3, 5);
		assertEquals(cell.getY(), 5);

		cell = new Cell(8, 2);
		assertEquals(cell.getY(), 2);
	}

	@Test
	public void testHasNorthWall() {
		Cell cell = new Cell(3, 5);
		assertTrue(cell.hasNorthWall());

		cell.setNorthWall(false);
		assertFalse(cell.hasNorthWall());

		cell.setNorthWall(true);
		assertTrue(cell.hasNorthWall());
	}

	@Test
	public void testHasSouthWall() {
		Cell cell = new Cell(3, 5);
		assertTrue(cell.hasSouthWall());

		cell.setSouthWall(false);
		assertFalse(cell.hasSouthWall());

		cell.setSouthWall(true);
		assertTrue(cell.hasSouthWall());
	}

	@Test
	public void testHasEastWall() {
		Cell cell = new Cell(3, 5);
		assertTrue(cell.hasEastWall());

		cell.setEastWall(false);
		assertFalse(cell.hasEastWall());

		cell.setEastWall(true);
		assertTrue(cell.hasEastWall());
	}

	@Test
	public void testHasWestWall() {
		Cell cell = new Cell(3, 5);
		assertTrue(cell.hasWestWall());

		cell.setWestWall(false);
		assertFalse(cell.hasWestWall());

		cell.setWestWall(true);
		assertTrue(cell.hasWestWall());
	}

	/* Setters tests */

	@Test
	public void testSetNorthWall() {
		Cell cell = new Cell(3, 5);
		cell.setNorthWall(false);
		assertFalse(cell.hasNorthWall());

		cell.setNorthWall(true);
		assertTrue(cell.hasNorthWall());
	}
	
	@Test
	public void testSetSouthWall() {
		Cell cell = new Cell(3, 5);
		cell.setSouthWall(false);
		assertFalse(cell.hasSouthWall());

		cell.setSouthWall(true);
		assertTrue(cell.hasSouthWall());
	}

	@Test
	public void testSetEastWall() {
		Cell cell = new Cell(3, 5);
		cell.setEastWall(false);
		assertFalse(cell.hasEastWall());

		cell.setEastWall(true);
		assertTrue(cell.hasEastWall());
	}

	@Test
	public void testSetWestWall() {
		Cell cell = new Cell(3, 5);
		cell.setWestWall(false);
		assertFalse(cell.hasWestWall());

		cell.setWestWall(true);
		assertTrue(cell.hasWestWall());
	}

	@Test
	public void testEquals() {
		Cell cell = new Cell(3, 5);
		Cell cell2 = new Cell(8, 2);
		assertFalse(cell.equals(cell2));

		cell2 = cell;
		assertEquals(cell, cell2);
	}

	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestCell.class);
    }
}
