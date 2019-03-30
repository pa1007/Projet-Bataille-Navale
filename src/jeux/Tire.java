package jeux;

import java.io.Serializable;

public class Tire implements Serializable {


    /**
     * Si le tire a touche ou non.
     */
    private boolean touche;

    /**
     * La place ou le tire a été effectuer.
     */
    private Place   place;

    /**
     * Constructeur de Tire.
     * @param touche true si la place a ete touchee par un tire.
     * @param place place a laquel le tire a ete lance.
     */
    public Tire(boolean touche, Place place) {
        this.touche = touche;
        this.place = place;
    }

    /**
     * @return Si le tire a touche ou non.
     */
    public boolean isTouche() {
        return this.touche;
    }

    /**
     * Initialise si le tire a <code>touche</code>.
     * @param touche Si le tire a toucher ou non.
     */
    public void setTouche(boolean touche) {
        this.touche = touche;
    }

    /**
     * @return La place ou le tire a été effectuer.
     */
    public Place getPlace() {
        return this.place;
    }

    /**
     * Initialise la <code>place</code>.
     * @param place La place ou le tire a été effectuer.
     */
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return touche ? "┼" : "Ø";
    }
}
