package jeux;

import bateaux.Bateaux;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import exception.GrilleNonCreeException;
import exception.PlacementInvalid;
import graphic.GraphicMain;
import utils.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jeux implements Serializable {


    /**
     * Les places obstruer.
     */
    private List<Place>  obstrue;

    /**
     * Liste des players en jeux.
     */
    private List<Player> listPlayer;

    /**
     * Le mode de jeux.
     */
    private int          modeDeJeux;

    /**
     * Constructeur du Jeux.
     * @param modeDeJeux mode de jeux utilise
     * @param listPlayer liste de joueur
     */
    public Jeux(int modeDeJeux, List<Player> listPlayer) {
        this.modeDeJeux = modeDeJeux;
        this.listPlayer = listPlayer;
        this.obstrue = new ArrayList<>();
    }

    /**
     * @return Les places obstruer.
     */
    public List<Place> getObstrue() {
        return this.obstrue;
    }

    /**
     * Methode qui ajoute une place obstrue au Jeux.
     * @param p place 
     */
    public void addPlaceObstue(Place p) {
        obstrue.add(p);
    }

    /**
     * @return Liste des players en jeux.
     */
    public List<Player> getListPlayer() {
        return this.listPlayer;
    }

    /**
     * @return le mode de jeux.
     */
    public int getModeDeJeux() {
        return modeDeJeux;
    }
    /**
     * Methode qui permet de reprendre la partie.
     */
    public void reprendrePartie() {
        for (Player p : getListPlayer()) {
            p.play(this);
        }
    }
    /**
     * Methode qui permet de lancer une partie.
     * @throws GrilleNonCreeException si la grille n'est pas crée.
     */
    public void lancerPartie() throws GrilleNonCreeException {
        askObstuerCase();
        ajouterBateaux();
        reprendrePartie();
    }

    /**
     * Methode qui permet de tirer sur une place.
     * @param place sur laquelle on tire 
     * @param p joueur
     * @return true si un bateau a ete touche.
     * @throws PlacementInvalid si la place est invalide.
     */
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

    /**
     * Methode qui permet de lancer une partie avec les graphismes.
     * @param graphicMain classe principale de la partie graphique.
     * @throws GrilleNonCreeException si la grille n'est pas crée.
     */
    public void lancerPartieGraph(GraphicMain graphicMain) throws GrilleNonCreeException {
        askObstuerCase();
        ajouterBateaux();
        while (true) {
            for (Player p : listPlayer) {
                p.play(this);
            }
        }

    }

    /**
     * Methode qui retourne toutes les place occupes par un bateau dont la taille est mis en parametre.<br>
     * Methode qui teste la place mis en parametre "s" et permet de tester les places qui en découle grace a l'orientatin et a la taille du bateau.
     * @param s place principale du bateau.
     * @param g grille sur laquelle le bateau est place.
     * @param tailleBat taille du bateau.
     * @param b orientation du bateau.
     * @return toutes les places occupees par un bateau.
     * @throws BateauxStartPointInvalide si la place principale du bateau est invalide.
     * @throws BateauxMembreInvalide si les places du bateau sont invalides.
     */
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

    /**
     * Methode qui permet de savoir si un bateau est bien sur la grille et a la place donne
     * @param p place a verifie
     * @param g grille 
     * @return bateau a la place sur la grille.
     */
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

    /**
     * Methode qui permet d'ajouter un bateau au joueur.
     * @throws GrilleNonCreeException si la grille n'a pas ete cree.
     */
    private void ajouterBateaux() throws GrilleNonCreeException {
        for (Player p : getListPlayer()) {
            p.placerBateau(this);
        }
    }

    /**
     * Methode qui demande a l'utilisateur de placer des cases a obstruer (iles).
     * @throws GrilleNonCreeException si la grille n'a pas ete cree.
     */
    private void askObstuerCase() throws GrilleNonCreeException {
        for (Player p : getListPlayer()) {
            p.obstruerCase(this);
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

