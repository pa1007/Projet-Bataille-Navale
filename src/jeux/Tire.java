package jeux;

import java.io.Serializable;

public class Tire implements Serializable {


    /**
     * Si le tire a toucher ou non.
     *
     * @since 1.0
     */
    private boolean touche;
    /**
     * La place ou le tire a été effectuer.
     *
     * @since 1.0
     */
    private Place   place;

    public Tire(boolean touche, Place place) {
        this.touche = touche;
        this.place = place;
    }

    /**
     * @return Si le tire a toucher ou non.
     * @since 1.0
     */
    public boolean isTouche() {
        return this.touche;
    }

    /**
     * Sets the <code>touche</code> field.
     *
     * @param touche Si le tire a toucher ou non.
     * @since 1.0
     */
    public void setTouche(boolean touche) {
        this.touche = touche;
    }

    /**
     * @return La place ou le tire a été effectuer.
     * @since 1.0
     */
    public Place getPlace() {
        return this.place;
    }

    /**
     * Sets the <code>place</code> field.
     *
     * @param place La place ou le tire a été effectuer.
     * @since 1.0
     */
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return touche ? "┼" : "Ø";
    }
}
