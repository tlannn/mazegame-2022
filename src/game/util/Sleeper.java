package game.util;

/**
 * Helper class to pause the program
 */
public class Sleeper {
    /**
     * Pause the thread during a certain amount of time
     * @param millis the pause duration in milliseconds
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
