package game.system;

import org.junit.*;

import game.system.input.ConsoleInputSystem;

import static org.junit.Assert.*;
import utils.Tester;


public class TestConsoleInputSystem extends Tester {

    @Test
    public void testGetInteger() {
        this.provideInput("2");
        ConsoleInputSystem system = new ConsoleInputSystem();
        assertEquals(2, system.getInteger());   
    }

    @Test
    public void testGetIntegerWithManyEnter() {
        this.provideInput("d" + System.lineSeparator() + "2" + System.lineSeparator());
        ConsoleInputSystem system = new ConsoleInputSystem();
        assertEquals(2, system.getInteger());
    }

    @Test
    public void testGetLetter() {
        this.provideInput("D");
        ConsoleInputSystem system = new ConsoleInputSystem();
        assertEquals('D', system.getLetter());
    }

    @Test
    public void testGetLetterWithManyLetter() {
        this.provideInput("2" + System.lineSeparator() + "Damien" + System.lineSeparator() + "f" + System.lineSeparator());
        ConsoleInputSystem system = new ConsoleInputSystem();
        assertEquals('F', system.getLetter());
    }
}
