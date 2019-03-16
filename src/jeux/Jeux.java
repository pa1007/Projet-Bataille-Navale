package jeux;

import bateaux.*;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import exception.GrilleNonCreeException;
import utils.Player;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Jeux {


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

    public void lancerPartie() throws GrilleNonCreeException {
        ajouterBateaux();
        System.out.println(listPlayer.get(0).getGrille());
    }

    private void ajouterBateaux() throws GrilleNonCreeException {
        String s;
        for (Player p : getListPlayer()) {
            List<Bateaux> bl = new ArrayList<>();
            if (p.getGrille() == null) {
                throw new GrilleNonCreeException();
            }
            Grille g = p.getGrille();
            System.out.println(g);
            Bateaux p1, p2, p3, p4, p5;
            while (true) {
                Scanner sc = new Scanner(System.in);
                try {
                    System.out.println("Ajouter le bateux de 5 de Longeur ex: A6");
                    s = sc.nextLine();
                    System.out.println("true = Horizontale, false = Vertiacale");
                    boolean b  = sc.nextBoolean();
                    Place[] pl = checkPlace(s, g, 5, b);
                    p5 = new ContreTorpilleur(pl, this, p, b);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide | InputMismatchException e) {
                    System.err.println("Erreur, merci de recommencer : " + e.getMessage());
                }
            }
            bl.add(p5);
            while (true) {
                Scanner sc = new Scanner(System.in);
                try {
                    System.out.println("Ajouter le bateux de 4 de Longeur ex: A6");
                    s = sc.nextLine();
                    System.out.println("true = Horizontale, false = Vertiacale");
                    boolean b  = sc.nextBoolean();
                    Place[] pl = checkPlace(s, g, 4, b);
                    p4 = new PorteAvion(pl, this, p, b);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide | InputMismatchException e) {
                    System.err.println("Erreur, merci de recommencer : " + e.getMessage());
                }

            }
            bl.add(p4);
            while (true) {
                Scanner sc = new Scanner(System.in);
                try {
                    System.out.println("Ajouter le bateux de 3 de Longeur ex: A6");
                    s = sc.nextLine();
                    System.out.println("true = Horizontale, false = Vertiacale");
                    boolean b  = sc.nextBoolean();
                    Place[] pl = checkPlace(s, g, 3, b);
                    p3 = new SousMarin(pl, this, p, b);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide | InputMismatchException e) {
                    System.err.println("Erreur, merci de recommencer : " + e.getMessage());
                }

            }
            bl.add(p3);
            while (true) {
                Scanner sc = new Scanner(System.in);
                try {
                    System.out.println("Ajouter le bateux de 2 de Longeur ex: A6");
                    s = sc.nextLine();
                    System.out.println("true = Horizontale, false = Vertiacale");
                    boolean b  = sc.nextBoolean();
                    Place[] pl = checkPlace(s, g, 2, b);
                    p2 = new Torpilleur(pl, this, p, b);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide | InputMismatchException e) {
                    System.err.println("Erreur, merci de recommencer : " + e.getMessage());
                }

            }
            bl.add(p2);
            while (true) {
                Scanner sc = new Scanner(System.in);
                try {
                    System.out.println("Ajouter le bateux de 1 de Longeur ex: A6");
                    s = sc.nextLine();
                    System.out.println("true = Horizontale, false = Vertiacale");
                    boolean b  = sc.nextBoolean();
                    Place[] pl = checkPlace(s, g, 1, b);
                    p1 = new Croiseur(pl, this, p, b);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide | InputMismatchException e) {
                    System.err.println("Erreur, merci de recommencer : " + e.getMessage());
                }

            }
            bl.add(p1);
            g.setListBateaux(bl);
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
}

