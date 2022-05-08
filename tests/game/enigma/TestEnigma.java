package game.enigma;

import org.junit.*;
import static org.junit.Assert.*;

public class TestEnigma {
    @Test
    public void testEnigmaIsUnresolvedWhenCreated() {
        Enigma enigma = new Enigma("Question") {
            @Override
            public void resolve() {}
        };

        assertFalse(enigma.isResolved());
    }
}
