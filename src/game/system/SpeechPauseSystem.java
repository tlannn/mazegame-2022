package game.system;

import game.util.Sleeper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * System that prints a text with pauses between each letter
 */
public class SpeechPauseSystem {
    // Common delays
    public static final long FAST_PAUSE_DELAY = 20;
    public static final long QUICK_PAUSE_DELAY = 50;
    public static final long NORMAL_PAUSE_DELAY = 150;
    public static final long SLOW_PAUSE_DELAY = 250;
    public static final long LONG_PAUSE_DELAY = 350;

    // Common tags to make pauses
    public static final String FAST_PAUSE_TAG = "[" + QUICK_PAUSE_DELAY + "]";
    public static final String QUICK_PAUSE_TAG = "[" + QUICK_PAUSE_DELAY + "]";
    public static final String NORMAL_PAUSE_TAG = "[" + NORMAL_PAUSE_DELAY + "]";
    public static final String SLOW_PAUSE_DELAY_TAG = "[" + SLOW_PAUSE_DELAY + "]";
    public static final String LONG_PAUSE_DELAY_TAG = "[" + LONG_PAUSE_DELAY + "]";

    public static boolean enable = true; // Make false to deactivate this system

    /**
     * Print a text with a pause between each letter. Tags are consumed and make a longer pause,
     * according to the value in the tag
     * @param text the text to display
     * @param skipTags don't execute tag pauses if set to false
     */
    public static void say(String text, boolean skipTags) {
        if (enable) {
            String[] parse = text.split("((?=\\[[0-9]{1,4}\\])|(?<=\\[[0-9]{1,4}\\]))");

            for (String str : parse) {
                if (!skipTags && str.matches("\\[[0-9]{1,4}\\]")) {
                    Pattern pattern = Pattern.compile("[0-9]{1,4}");
                    Matcher matcher = pattern.matcher(str);

                    if (matcher.find()) {
                        long delay = Long.parseLong(matcher.group(0));
                        Sleeper.sleep(delay);
                    }
                } else {
                    for (int i = 0; i < str.length(); ++i) {
                        System.out.print(str.charAt(i));

                        if (!skipTags)
                            Sleeper.sleep(SpeechPauseSystem.FAST_PAUSE_DELAY);
                    }
                }
            }

            System.out.print("\n");
        }

        else
            System.out.println(text);
    }
}
