package ai;

import bateaux.Bateaux;
import exception.GrilleNonCreeException;
import jeux.Grille;
import jeux.Jeux;
import jeux.Place;
import jeux.Tire;
import utils.Player;

public class AI extends Player {

    /**
     * Constucteur de l'ia, prend le constructeur de Player puis et le boolean ai sur true
     */
    public AI() {
        super();
        setAI(true);
    }

    /**
     * {@inheritDoc} <br>Placer aléatoirement grace a la methode {@link Player#placerBateauRandom(Jeux)}
     *
     * @param jeux l'instance du jeu
     * @throws GrilleNonCreeException si la grille est null
     * @see Player#placerBateauRandom(Jeux)
     */
    public void placerBateau(Jeux jeux) throws GrilleNonCreeException {
        placerBateauRandom(jeux);
    }


    /**
     * Ceci permet a l'ia de jouer
     *
     * <br> Cette methode est faite aleatoirement
     *
     * @param jeux jeux a lancer.
     */
    @Override
    public void play(Jeux jeux) {
        while (true) {
            grille.consolTireFormat();
            boolean good = true;
            Place   p    = getRandomPlace();
            for (Tire tire : grille.getTires()) {
                if (tire.getPlace().is(p)) {
                    good = false;
                    break;
                }
            }
            for (Place pl : jeux.getObstrue()) {
                if (pl.is(p)) {
                    good = false;
                    break;
                }
            }

            if (good) {
                Grille  g = jeux.getOtherPlayer(this).getGrille();
                Bateaux b = jeux.bateauAt(p, g);
                Tire    t = new Tire(b != null, p);
                if (b != null) {
                    b.toucher(p);
                }
                grille.addNewTire(t);
                g.addFire(t.getPlace());
                break;
            }

        }
    }

    /**
     * Permet d'obstuer une case aléatoirement entre 0 et 10, si une case déja obstrue est selectionnee, rien ne se passe
     *
     * @param jeux jeux auxquel ajouter un cas obstrue.
     * @throws GrilleNonCreeException si la grille est null
     */
    public void obstruerCase(Jeux jeux) throws GrilleNonCreeException {
        int r = randBetween(0, 10);
        for (int i = 0; i < r; i++) {
            Place p = getRandomPlace();
            if (!jeux.getObstrue().contains(p)) {
                jeux.addPlaceObstue(p);
            }
        }

    }
}
