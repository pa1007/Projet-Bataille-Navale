package bateaux;

import jeux.Jeux;
import jeux.Place;
import utils.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Bateaux implements Serializable {

    /**
     * L'orientation.
     */
    private boolean orientation;

    /**
     * Le joueur proprietaire
     */
    private Player player;

    /**
     * La ou le bateau a été touche
     */
    private List<Place> touchedPlace;

    /**
     * Listes des places qu'occupe le bateau
     */
    private List<Place> places;

    /**
     * L'instance du jeu
     */
    private Jeux game;

    /**
     * Constructeur de bateau (test effectuer avant)
     *
     * @param place       las places prises par les parties du bateau
     * @param game        l'instance du jeu
     * @param player      le joueur propriétaire
     * @param orientation l'orientation du bateau
     */
    public Bateaux(Place[] place, Jeux game, Player player, boolean orientation) {
        this.places = Arrays.asList(place);
        this.game = game;
        this.player = player;
        this.orientation = orientation;
        touchedPlace = new ArrayList<>();
    }

    /**
     * @return L'orientatiion.
     */
    public boolean getOrientation() {
        return this.orientation;
    }

    /**
     * Sets the <code>orientation</code> field.
     *
     * @param orientation L'orientatiion.
     */
    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    /**
     * Permet d'avoir la place d'un bateau sous la forme de l'object {@link Place}
     *
     * @return une place
     */
    public List<Place> getPlaces() {
        return places;
    }

    /**
     * Getter de l'atribue jeux
     *
     * @return le jeux
     */
    public Jeux getJeux() {
        return game;
    }

    /**
     * Getter du player propri
     *
     * @return le joueur
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Ajoute facilement un tire sur un bateau
     *
     * @param place la place touché
     */
    public void toucher(Place place) {
        touchedPlace.add(place);
    }

    /**
     * Retourne la liste de place touche pour les tests
     *
     * @return la liste de places touchees.
     */
    public List<Place> getTouchedPlace() {
        return this.touchedPlace;
    }

    /**
     * @return le nombre de bout de bateau toucher
     */
    public int nbToucher() {
        return touchedPlace.size();
    }

    /**
     * @return le poucentage de damage du bateau
     */
    public double getPurcentDamage() {
        return ((double) nbToucher()) / taille();
    }

    /**
     * Savoir si le bateau est mort (coulé) ou non
     *
     * @return true si mort, false si non mort
     */
    public boolean dead() {
        return nbToucher() == taille();
    }

    /**
     * La lettre du bateau , "B" si il n'est pas mort <br>
     * "฿" Si il est coulé (Ne s'affiche pas sur toutes les consoles, surement changer en "C")
     *
     * @return un string qui correspond a l'affichage du bateau
     */
    public String getLetter() {
        return dead() ? "฿" : "B";
    }

    /**
     * @return La taille du bateau
     */
    public int taille() {
        return places.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bateaux)) {
            return false;
        }
        Bateaux bateaux = (Bateaux) o;
        return orientation == bateaux.orientation &&
               Objects.equals(touchedPlace, bateaux.touchedPlace) &&
               Objects.equals(places, bateaux.places);
    }

    /**
     * Le toString, qui permet de debug
     *
     * @return un string
     */
    @Override
    public String toString() {
        return "Bateaux{" + "places=" + places
               + ", ListTire=" + touchedPlace
               + ", orientation=" + orientation
               + '}';
    }

}
