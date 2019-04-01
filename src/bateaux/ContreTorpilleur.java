package bateaux;

import jeux.Jeux;
import jeux.Place;
import utils.Player;

public class ContreTorpilleur extends Bateaux {

    /**
     * Constructeur de bateau (test effectuer avant)
     *
     * @param place       las places prises par les parties du bateau
     * @param game        l'instance du jeu
     * @param player      le joueur propriétaire
     * @param orientation l'orientation du bateau
     */
    public ContreTorpilleur(Place[] place, Jeux game, Player player, boolean orientation) {
        super(place, game, player, orientation);
    }
}
