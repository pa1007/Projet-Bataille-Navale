package exception;

import jeux.Place;

public class BateauxMembreInvalide extends MesException {

    /**
     * Exception lorsqu'un membre du bateau est invalide au placement
     *
     * @param place la place ou il y a l'erreur
     * @param i     le numero de la place
     */
    public BateauxMembreInvalide(Place place, int i) {
        super("La place " + place + " n'est pas valide (en dehors ou sur un autre bateau) calcule n " + i + " )");
    }
}
