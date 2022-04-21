package game.system.input;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provide an input system for a console application
 */
public class ConsoleInputSystem implements InputSystem {

	private Scanner scanner;

	/**
	 * Class constructor
	 */
	public ConsoleInputSystem() {
		this.scanner = new Scanner(System.in);
	}

	@Override
	public String getMessage() {
		return this.scanner.nextLine();
	}

	@Override
	public int getInteger() {
		int integer = -1;
		boolean success = false;

		while (!success) {
			try {
				integer = Integer.parseInt(this.scanner.nextLine());
				success = true;
			} catch (NumberFormatException e) {
				System.out.println("Vous devez entrer un nombre.");
			}
		}

		return integer;
	}

	@Override
	public char getLetter() {
		String input = "";
		boolean success = false;

		while (!success) {
			input = this.scanner.nextLine();

			if (input.length() == 1) {
				char character = input.charAt(0);

				if (character >= 'A' && character <= 'Z' || character >= 'a' && character <= 'z')
					success = true;
			}

			if (!success)
				System.out.println("Vous devez entrer une lettre.");
		}

		return Character.toUpperCase(input.charAt(0)); // Return the letter upper-cased
	}

	@Override
	public int getIntegerFromLetter() {
		char letter = this.getLetter();

		return (int) letter - 'A';
	}
}
