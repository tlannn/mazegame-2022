package game.enigma;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class EnigmaManager {
    private List<Enigma> enigmas;
    private ListIterator<Enigma> it;

    public EnigmaManager() {
        this.enigmas = new ArrayList<>();
        this.it = this.enigmas.listIterator();
    }

    public EnigmaManager(List<Enigma> enigmas) {
        this();
        this.enigmas.addAll(enigmas);
        this.it = this.enigmas.listIterator();
    }

    public void addEnigma(Enigma enigma) {
        this.it.add(enigma);
        this.it.previous();
    }

    public Enigma getEnigma() {
        int itemsLooked = 0;

        while (itemsLooked < this.enigmas.size()) { // Look through all enigmas
            if (!this.it.hasNext()) this.it = this.enigmas.listIterator(); // Reset the iterator to the beginning if end is reached
            Enigma enigma = this.it.next(); // Take next enigma
            ++itemsLooked;

            // When an unresolved enigma is reached, return it
            if (!enigma.isResolved()) return enigma;
        }

        // Reached if all enigmas are resolved
        return null;
    }
}