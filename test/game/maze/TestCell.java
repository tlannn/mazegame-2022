package game.maze;

import game.character.Altruist;
import game.character.Character;
import game.character.Fool;
import game.enigma.FakeHint;
import game.enigma.Hint;
import game.enigma.ItemPositionHint;
import game.item.Item;
import game.item.Jewel;
import game.item.JewelRarity;

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

	@Test
	public void testNoItemsInCellWhenCreated() {
		Cell cell = new Cell(3, 5);
		assertEquals(0, cell.getItemsInCell().size());
	}

	@Test
	public void testNoCharactersInCellWhenCreated() {
		Cell cell = new Cell(3, 5);
		assertEquals(0, cell.getCharactersInCell().size());
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
	public void testPossibleOrientationsAreCorrects() {
		Cell cell = new Cell(3, 5);
		assertTrue(cell.possibleOrientations().contains(Orientation.NORTH));
		assertTrue(cell.possibleOrientations().contains(Orientation.SOUTH));
		assertTrue(cell.possibleOrientations().contains(Orientation.EAST));
		assertTrue(cell.possibleOrientations().contains(Orientation.WEST));

		cell.setNorthWall(false);
		cell.setWestWall(false);

		assertFalse(cell.possibleOrientations().contains(Orientation.NORTH));
		assertTrue(cell.possibleOrientations().contains(Orientation.SOUTH));
		assertTrue(cell.possibleOrientations().contains(Orientation.EAST));
		assertFalse(cell.possibleOrientations().contains(Orientation.WEST));
	}

	@Test
	public void testItemIsCorrectlyAdded() {
		Cell cell = new Cell(3, 5);
		Item greenJewel = new Jewel(cell, JewelRarity.GREEN);
		Item blueJewel = new Jewel(cell, JewelRarity.BLUE);

		cell.addItem(greenJewel);
		assertTrue(cell.getItemsInCell().contains(greenJewel));
		assertFalse(cell.getItemsInCell().contains(blueJewel));

		cell.addItem(blueJewel);
		assertTrue(cell.getItemsInCell().contains(greenJewel));
		assertTrue(cell.getItemsInCell().contains(blueJewel));
	}

	@Test
	public void testItemIsCorrectlyRemoved() {
		Cell cell = new Cell(3, 5);
		Item greenJewel = new Jewel(cell, JewelRarity.GREEN);
		Item blueJewel = new Jewel(cell, JewelRarity.BLUE);

		try {
			cell.addItem(greenJewel);
			cell.addItem(blueJewel);
			assertTrue(cell.getItemsInCell().contains(greenJewel));
			assertTrue(cell.getItemsInCell().contains(blueJewel));

			cell.removeItem(blueJewel);
			assertTrue(cell.getItemsInCell().contains(greenJewel));
			assertFalse(cell.getItemsInCell().contains(blueJewel));

			cell.removeItem(greenJewel);
			assertFalse(cell.getItemsInCell().contains(greenJewel));
			assertFalse(cell.getItemsInCell().contains(blueJewel));
		}

		catch (ItemNotInCellException e) {
			fail();
		}
	}

	@Test(expected = game.maze.ItemNotInCellException.class)
	public void testRemoveItemNotInCellThrowsException() throws ItemNotInCellException {
		Cell cell = new Cell(3, 5);
		Item jewel = new Jewel(cell, JewelRarity.GREEN);

		cell.removeItem(jewel);
	}

	@Test
	public void testCharacterIsCorrectlyAdded() {
		Cell cell = new Cell(3, 5);
		Hint hint = new ItemPositionHint(new Jewel(cell, JewelRarity.GREEN));
		FakeHint fakeHint = new FakeHint("Je suis le meilleur");
		Character altruist = new Altruist(hint, cell);
		Character fool = new Fool(fakeHint, cell);

		assertFalse(cell.getCharactersInCell().contains(altruist));
		assertFalse(cell.getCharactersInCell().contains(fool));

		cell.addCharacter(altruist);
		assertTrue(cell.getCharactersInCell().contains(altruist));
		assertFalse(cell.getCharactersInCell().contains(fool));

		cell.addCharacter(fool);
		assertTrue(cell.getCharactersInCell().contains(altruist));
		assertTrue(cell.getCharactersInCell().contains(fool));
	}

	@Test
	public void testCharacterIsCorrectlyRemoved() {
		Cell cell = new Cell(3, 5);
		Hint hint = new ItemPositionHint(new Jewel(cell, JewelRarity.GREEN));
		FakeHint fakeHint = new FakeHint("Je suis le meilleur");
		Character altruist = new Altruist(hint, cell);
		Character fool = new Fool(fakeHint, cell);

		try {
			cell.addCharacter(altruist);
			cell.addCharacter(fool);
			assertTrue(cell.getCharactersInCell().contains(altruist));
			assertTrue(cell.getCharactersInCell().contains(fool));

			cell.removeCharacter(fool);
			assertTrue(cell.getCharactersInCell().contains(altruist));
			assertFalse(cell.getCharactersInCell().contains(fool));

			cell.removeCharacter(altruist);
			assertFalse(cell.getCharactersInCell().contains(altruist));
			assertFalse(cell.getCharactersInCell().contains(fool));
		}

		catch (CharacterNotInCellException e) {
			fail();
		}
	}

	@Test(expected = game.maze.CharacterNotInCellException.class)
	public void testRemoveCharacterNotInCellThrowsException() throws CharacterNotInCellException {
		Cell cell = new Cell(3, 5);
		Hint hint = new ItemPositionHint(new Jewel(cell, JewelRarity.GREEN));
		Character altruist = new Altruist(hint, cell);

		cell.removeCharacter(altruist);
	}

	@Test
	public void testEquals() {
		Cell cell = new Cell(3, 5);
		Cell cell2 = new Cell(8, 2);
		assertFalse(cell.equals(cell2));

		cell2 = new Cell(3,5);
		assertEquals(cell, cell2);

	}

	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestCell.class);
    }
}
