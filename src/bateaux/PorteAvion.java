package bateaux;

import jeux.Jeux;
import jeux.Place;
import utils.Player;

public class PorteAvion extends Bateaux {


    public PorteAvion(Place[] place, Jeux game, Player player, boolean orientation) {
        super(place, game, player, orientation);
    }
}
