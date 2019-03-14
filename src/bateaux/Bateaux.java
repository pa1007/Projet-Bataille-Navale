package bateaux;

import jeux.Jeux;
import jeux.Place;
import utils.Player;
import java.util.Arrays;
import java.util.List;

public abstract class Bateaux {

    protected List<Place> place;

    protected Jeux game;

    protected Player player;

    public Bateaux(Place[] place, Jeux game, Player player) {
        this.place = Arrays.asList(place);
        this.game = game;
        this.player = player;
    }


    /**
     * Permet d'avoir la place d'un bateau sous la forme de l'object {@link Place}
     *
     * @return une place
     */
    public List<Place> getPlaces() {
        return place;
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
     * @return
     */
    public Player getPlayer() {
        return player;
    }
}
