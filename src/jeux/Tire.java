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

    public Tire(boolean touche, Place place) {
        this.touche = touche;
        this.place = place;
    }

    /**
     * @return Si le tire a toucher ou non.
     */
    public boolean isTouche() {
        return this.touche;
    }

    /**
     * Initialise si le tire a <code>touche</code>
     * 
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
     * Initialise la <code>place</code>
     *
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
