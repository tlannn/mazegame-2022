package game.enigma;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Manager for enigmas
 *
 * Contains all enigmas, and is charged to give unresolved enigmas when an enigma is needed
 */
public class EnigmaManager {
    private List<Enigma> enigmas;
    private ListIterator<Enigma> it;

    /**
     * Class constructor
     */
    public EnigmaManager() {
        this.enigmas = new ArrayList<>();
        this.it = this.enigmas.listIterator();
    }

    /**
     * Class constructor
     * @param enigmas the default list of enigmas
     */
    public EnigmaManager(List<Enigma> enigmas) {
        this();
        this.enigmas.addAll(enigmas);
        this.it = this.enigmas.listIterator();
    }

    /**
     * Add an enigma to manage
     * @param enigma the enigma
     */
    public void addEnigma(Enigma enigma) {
        this.it.add(enigma);
        this.it.previous();
    }

    /**
     * Return the first unresolved enigma, or null if there is no unresolved enigma
     * @return an enigma
     */
    public Enigma getEnigma() {
        int itemsLooked = 0;
        Enigma enigmaUnresolved = null;

        while (itemsLooked < this.enigmas.size() && enigmaUnresolved == null) { // Look through all enigmas
            if (!this.it.hasNext()) this.it = this.enigmas.listIterator(); // Reset the iterator to the beginning if end is reached
            Enigma enigma = this.it.next(); // Take next enigma
            ++itemsLooked;

            // When an unresolved enigma is reached, return it
            if (!enigma.isResolved()) enigmaUnresolved = enigma;
        }

        // Reached if all enigmas are resolved
        return enigmaUnresolved;
    }
}