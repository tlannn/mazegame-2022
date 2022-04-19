package game.system.input;

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
				integer = this.scanner.nextInt();
				success = true;
			} catch (NumberFormatException e) {

			}
		}

		return integer;
	}

	@Override
	public char getLetter() {
		return this.scanner.nextLine().charAt(0);
	}
}
