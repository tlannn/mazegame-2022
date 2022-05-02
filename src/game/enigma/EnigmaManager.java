package game.enigma;

import java.util.ArrayList;
import java.util.List;

public class EnigmaManager {
    //private Map<Enigma, Boolean> enigmas;
    private List<Enigma> enigmas;

    public EnigmaManager() {
        this.enigmas = new ArrayList<>();
    }

    public EnigmaManager(List<Enigma> enigmas) {
        this();
        this.enigmas.addAll(enigmas);
    }

    public void addEnigma(Enigma enigma) {
        this.enigmas.add(enigma);
    }

    public Enigma getEnigma() {
        Enigma enigma = null;

        for (Enigma e : this.enigmas) {
            if (!e.isResolved()) {
                enigma = e; break;
            }
        }

        return enigma;
    }
}