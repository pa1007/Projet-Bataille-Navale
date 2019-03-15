package bateaux;

import jeux.Jeux;
import jeux.Place;
import utils.Player;

public class Croiseur extends Bateaux {

    public Croiseur(Place place, Jeux game, Player player, boolean orientation) {
        super(place, game, player, orientation);
    }
}
