package jeux;

import bateaux.*;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import exception.GrilleNonCreeException;
import exception.PlacementInvalid;
import utils.Player;
import utils.SavedObject;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println(listPlayer.get(0).getGrille().consolBateauFormat());
        Scanner sc = new Scanner(System.in);
        while (true) {
            Jeux.clearScreen();
            System.out.println(listPlayer.get(0).getGrille().consolTireFormat());
            try {
                new SavedObject(this).save();
            }
            catch (IOException e) {
                System.out.println("Erreur la sauvegarde");
            }
            System.out.println("Donnez une case pour tirer (Ex : A6)  ou dite STOP pour arreter !");
            String s = sc.nextLine();
            if (s.equalsIgnoreCase("Stop")) {
                break;
            }
            else {
                Place p;
                try {
                    p = new Place(s);
                }
                catch (Exception e) {
                    System.err.println("Place non valide !");
                    continue;
                }
                try {
                    boolean b = tire(p, listPlayer.get(0));
                    System.out.println(b ? "Vous avez touché un bateau" : "Vous tomber dans l'eau, dommage");
                }
                catch (PlacementInvalid placementInvalid) {
                    System.out.println(placementInvalid.getMessage());
                    continue;
                }
                boolean allDead = true;
                for (Bateaux b : listPlayer.get(0).getGrille().getListBateaux()) {
                    allDead = allDead && b.dead();
                }
                if (allDead) {
                    System.out.println("Tout les bateaux sont mort, vous avez gagner");
                    break;
                }
            }
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
        String  s;
        Scanner sc = new Scanner(System.in);
        for (Player p : getListPlayer()) {
            List<Bateaux> bl = new ArrayList<>();
            if (p.getGrille() == null) {
                throw new GrilleNonCreeException();
            }
            Grille g = p.getGrille();
            System.out.println(g.consolBateauFormat());
            Bateaux p1, p2, p3, p4, p5;
            while (true) {
                try {
                    System.out.println("Ajouter le bateux de 5 de Longeur ex: A6");
                    s = sc.nextLine();
                    System.out.println("true = Horizontale, false = Vertiacale");
                    boolean b = sc.nextBoolean();
                    sc.nextLine();
                    Place[] pl = checkPlace(s, g, 5, b);
                    p5 = new ContreTorpilleur(pl, this, p, b);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide | RuntimeException e) {
                    e.printStackTrace();
                    System.err.println("Erreur, merci de recommencer : " + e.getMessage());
                }
            }
            bl.add(p5);
            g.setListBateaux(bl);
            Jeux.clearScreen();
            System.out.println(g.consolBateauFormat());
            while (true) {
                try {
                    System.out.println("Ajouter le bateux de 4 de Longeur ex: A6");
                    s = sc.nextLine();
                    System.out.println("true = Horizontale, false = Vertiacale");
                    boolean b = sc.nextBoolean();
                    sc.nextLine();
                    Place[] pl = checkPlace(s, g, 4, b);
                    p4 = new PorteAvion(pl, this, p, b);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide | RuntimeException e) {
                    System.err.println("Erreur, merci de recommencer : " + e.getMessage());
                }

            }
            bl.add(p4);
            Jeux.clearScreen();
            System.out.println(g.consolBateauFormat());
            while (true) {
                try {
                    System.out.println("Ajouter le bateux de 3 de Longeur ex: A6");
                    s = sc.nextLine();
                    System.out.println("true = Horizontale, false = Vertiacale");
                    boolean b = sc.nextBoolean();
                    sc.nextLine();
                    Place[] pl = checkPlace(s, g, 3, b);
                    p3 = new SousMarin(pl, this, p, b);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide | RuntimeException e) {
                    System.err.println("Erreur, merci de recommencer : " + e.getMessage());
                }

            }
            bl.add(p3);
            Jeux.clearScreen();
            System.out.println(g.consolBateauFormat());
            while (true) {
                try {
                    System.out.println("Ajouter le bateux de 2 de Longeur ex: A6");
                    s = sc.nextLine();
                    System.out.println("true = Horizontale, false = Vertiacale");
                    boolean b = sc.nextBoolean();
                    sc.nextLine();
                    Place[] pl = checkPlace(s, g, 2, b);
                    p2 = new Torpilleur(pl, this, p, b);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide | RuntimeException e) {
                    System.err.println("Erreur, merci de recommencer : " + e.getMessage());
                }

            }
            bl.add(p2);
            Jeux.clearScreen();
            System.out.println(g.consolBateauFormat());
            while (true) {
                try {
                    System.out.println("Ajouter le bateux de 1 de Longeur ex: A6");
                    s = sc.nextLine();
                    System.out.println("true = Horizontale, false = Vertiacale");
                    boolean b = sc.nextBoolean();
                    sc.nextLine();
                    Place[] pl = checkPlace(s, g, 1, b);
                    p1 = new Croiseur(pl, this, p, b);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide | RuntimeException e) {
                    System.err.println("Erreur, merci de recommencer : " + e.getMessage());
                }

            }
            bl.add(p1);
        }
    }

    private Place[] checkPlace(String s, Grille g, int tailleBat, boolean b)
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

