package bateaux;

import jeux.Jeux;
import jeux.Place;
import utils.Player;
import java.util.Arrays;
import java.util.List;

public abstract class Bateaux {

    protected List<Place> places;

    protected Jeux game;

    protected Player  player;
    /**
     * La place en bas a gauche.
     *
     * @since 1.0
     */
    protected Place   place;
    /**
     * L'orientation.
     */
    protected boolean orientation;

    public Bateaux(Place[] place, Jeux game, Player player, boolean orientation) {
        this.places = Arrays.asList(place);
        this.game = game;
        this.player = player;
        this.orientation = orientation;
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
     * @return La place en bas a gauche.
     * @since 1.0
     */
    public Place getPlace() {
        return this.place;
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
}
