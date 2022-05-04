package game.maze;

import game.character.Altruist;
import game.character.Character;
import game.character.Fool;
import game.hint.FixedHint;
import game.hint.fake.FakeHint;
import game.hint.Hint;
import game.hint.ItemPositionHint;
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
		assertEquals(0, cell.getNonPlayerCharactersInCell().size());
	}

	/* Methods tests */

	@Test
	public void testPossibleOrientationsAreCorrects() {
		Cell cell = new Cell(3, 5);
		assertFalse(cell.possibleOrientations().contains(Orientation.NORTH));
		assertFalse(cell.possibleOrientations().contains(Orientation.SOUTH));
		assertFalse(cell.possibleOrientations().contains(Orientation.EAST));
		assertFalse(cell.possibleOrientations().contains(Orientation.WEST));

		cell.setNorthWall(false);
		cell.setWestWall(false);

		assertTrue(cell.possibleOrientations().contains(Orientation.NORTH));
		assertFalse(cell.possibleOrientations().contains(Orientation.SOUTH));
		assertFalse(cell.possibleOrientations().contains(Orientation.EAST));
		assertTrue(cell.possibleOrientations().contains(Orientation.WEST));
	}

	@Test
	public void testItemIsCorrectlyAdded() {
		Cell cell = new Cell(3, 5);
		Item greenJewel = new Jewel(JewelRarity.GREEN, null);
		Item blueJewel = new Jewel(JewelRarity.BLUE, null);

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
		Item greenJewel = new Jewel(JewelRarity.GREEN, null);
		Item blueJewel = new Jewel(JewelRarity.BLUE, null);

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
		Item jewel = new Jewel(JewelRarity.GREEN, null);

		cell.removeItem(jewel);
	}

	@Test
	public void testCharacterIsCorrectlyAdded() {
		Cell cell = new Cell(3, 5);

		Altruist altruist = new Altruist(null);
		Fool fool = new Fool(null);

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

		Altruist altruist = new Altruist(null);
		Fool fool = new Fool(null);

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
		Altruist altruist = new Altruist(null);

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
}
