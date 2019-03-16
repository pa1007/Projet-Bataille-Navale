package exception;

import jeux.Place;

public class BateauxMembreInvalide extends Exception {

    public BateauxMembreInvalide(Place place, int i) {
        super("La place " + place + " n'est pas valide (en dehors ou sur un autre bateau) calcule n " + i);
    }
}
