package ai;

import bateaux.Bateaux;
import exception.GrilleNonCreeException;
import jeux.Grille;
import jeux.Jeux;
import jeux.Place;
import jeux.Tire;
import utils.Player;

public class AI extends Player {

    public AI() {
        super();
        setAI(true);
    }

    public void placerBateau(Jeux jeux) throws GrilleNonCreeException {
        placerBateauRandom(jeux);
    }


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
            for (Place pl : jeux.getObstrue()){
                if (pl.is(p)){
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
