package jeux;

import bateaux.Bateaux;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import exception.GrilleNonCreeException;
import exception.PlacementInvalid;
import graphic.GraphicMain;
import utils.Player;
import java.io.Serializable;
import java.util.List;

public class Jeux implements Serializable {


    /**
     * Liste des players en jeux.
     */
    private List<Player> listPlayer;

    /**
     * Le mode de jeux.
     */
    private int modeDeJeux;

    public Jeux(int modeDeJeux, List<Player> listPlayer) {
        this.modeDeJeux = modeDeJeux;
        this.listPlayer = listPlayer;
    }

    /**
     * @return Liste des players en jeux.
     */
    public List<Player> getListPlayer() {
        return this.listPlayer;
    }

    /**
     * @return Le mode de jeux.
     */
    public int getModeDeJeux() {
        return modeDeJeux;
    }

    public void reprendrePartie() {
        for (Player p : getListPlayer()) {
            p.play(this);
        }
    }

    public void lancerPartie() throws GrilleNonCreeException {
        ajouterBateaux();
        reprendrePartie();
    }

    public boolean tire(Place place, Player p) throws PlacementInvalid {
        Grille g = p.getGrille();
        if (g.inBound(place)) {
            for (Tire t : g.getTires()) {
                if (t.getPlace().is(place)) {
                    throw new PlacementInvalid("Place déja tirer");
                }
            }
            Bateaux b       = bateauAt(place, g);
            boolean toucher = false;
            if (b != null) {
                toucher = true;
                b.toucher(place);
                System.out.println(b.dead() ? "Coulé" : "Touché");
            }
            else {
                System.out.println("Miss");
            }
            g.addNewTire(new Tire(toucher, place));
            return toucher;
        }
        else {
            throw new PlacementInvalid();
        }
    }

    public void lancerPartieGraph(GraphicMain graphicMain) throws GrilleNonCreeException {
        ajouterBateaux();
        while (true) {
            for (Player p : listPlayer) {
                p.play(this);
            }
        }

    }

    public Place[] checkPlace(String s, Grille g, int tailleBat, boolean b)
    throws BateauxStartPointInvalide, BateauxMembreInvalide {
        Place pl = new Place(s);
        if (!g.placeValide(pl)) {
            throw new BateauxStartPointInvalide();
        }
        Place[] needPlace = new Place[tailleBat];
        needPlace[0] = pl;
        int x = b ? 0 : 1;
        int y = b ? 1 : 0;
        for (int i = 1; i < tailleBat; i++) {
            needPlace[i] = needPlace[i - 1].more(x, y);
            if (!g.placeValide(needPlace[i])) {
                throw new BateauxMembreInvalide(needPlace[i], i);
            }
        }
        return needPlace;
    }


    private Bateaux bateauAt(Place p, Grille g) {
        if (g.inBound(p)) {
            for (Bateaux b : g.getListBateaux()) {
                if (b.getPlaces().contains(p)) {
                    return b;
                }
            }
        }
        return null;
    }

    private void ajouterBateaux() throws GrilleNonCreeException {
        for (Player p : getListPlayer()) {
            p.placerBateau(this);
        }
    }

    @Override
    public String toString() {
        return "Jeux{" + "listPlayer=" + listPlayer
               + ", modeDeJeux=" + modeDeJeux
               + '}';
    }

    /**
     * methode statique qui nettoie la console pour une meilleure lisibilité
     */
    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }
}

