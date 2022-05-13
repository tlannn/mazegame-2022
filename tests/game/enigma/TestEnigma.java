package game.enigma;

import org.junit.*;
import utils.Tester;

import static org.junit.Assert.*;

public class TestEnigma extends Tester {
    @Test
    public void testEnigmaIsUnresolvedWhenCreated() {
        Enigma enigma = new Enigma("Question") {
            @Override
            public void resolve() {}
        };

        assertFalse(enigma.isResolved());
    }
}
