package game.util;

import java.lang.Math;

public class Random {
	/**
	* Generate a random integer N between min and max (both included)
	* @param min the minimum value that can be returned
	* @param max the maximum value that can be returned
	* @return a random integer matching the boundaries
	*/
	public static int randInt(int min, int max) {
		return (int)(Math.random() * (max - min + 1) + min);
	}
}
