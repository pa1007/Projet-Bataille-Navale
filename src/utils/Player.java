package utils;

import bateaux.*;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import exception.GrilleNonCreeException;
import exception.PlacementInvalid;
import jeux.Grille;
import jeux.Jeux;
import jeux.Place;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player implements Serializable {

    /**
     * Si le joueur est une IA.
     *
     * @since 1.0
     */
    protected boolean AI;


    /**
     * La grille du joueur.
     *
     * @since 1.0
     */
    protected Grille grille;


    public Player() {
    }

    /**
     * @return La grille du joueur.
     * @since 1.0
     */
    public Grille getGrille() {
        return this.grille;
    }

    /**
     * Sets the <code>grille</code> field.
     *
     * @param grille La grille du joueur.
     * @since 1.0
     */
    public void setGrille(Grille grille) {
        this.grille = grille;
    }


    public void placerBateau(Jeux j) throws GrilleNonCreeException {
        String        s;
        Scanner       sc = new Scanner(System.in);
        List<Bateaux> bl = new ArrayList<>();
        if (grille == null) {
            throw new GrilleNonCreeException();
        }
        System.out.println(grille.consolBateauFormat());
        Bateaux p1, p2, p3, p4, p5;
        while (true) {
            try {
                System.out.println("Ajouter le bateux de 5 de Longeur ex: A6");
                s = sc.nextLine();
                System.out.println("true = Horizontale, false = Vertiacale");
                boolean b = sc.nextBoolean();
                sc.nextLine();
                Place[] pl = j.checkPlace(s, grille, 5, b);
                p5 = new ContreTorpilleur(pl, j, this, b);
                break;
            }
            catch (BateauxStartPointInvalide | BateauxMembreInvalide | RuntimeException e) {
                e.printStackTrace();
                System.err.println("Erreur, merci de recommencer : " + e.getMessage());
            }
        }
        bl.add(p5);
        grille.setListBateaux(bl);
        Jeux.clearScreen();
        System.out.println(grille.consolBateauFormat());
        while (true) {
            try {
                System.out.println("Ajouter le bateux de 4 de Longeur ex: A6");
                s = sc.nextLine();
                System.out.println("true = Horizontale, false = Vertiacale");
                boolean b = sc.nextBoolean();
                sc.nextLine();
                Place[] pl = j.checkPlace(s, grille, 4, b);
                p4 = new PorteAvion(pl, j, this, b);
                break;
            }
            catch (BateauxStartPointInvalide | BateauxMembreInvalide | RuntimeException e) {
                System.err.println("Erreur, merci de recommencer : " + e.getMessage());
            }

        }
        bl.add(p4);
        Jeux.clearScreen();
        System.out.println(grille.consolBateauFormat());
        while (true) {
            try {
                System.out.println("Ajouter le bateux de 3 de Longeur ex: A6");
                s = sc.nextLine();
                System.out.println("true = Horizontale, false = Vertiacale");
                boolean b = sc.nextBoolean();
                sc.nextLine();
                Place[] pl = j.checkPlace(s, grille, 3, b);
                p3 = new SousMarin(pl, j, this, b);
                break;
            }
            catch (BateauxStartPointInvalide | BateauxMembreInvalide | RuntimeException e) {
                System.err.println("Erreur, merci de recommencer : " + e.getMessage());
            }

        }
        bl.add(p3);
        Jeux.clearScreen();
        System.out.println(grille.consolBateauFormat());
        while (true) {
            try {
                System.out.println("Ajouter le bateux de 2 de Longeur ex: A6");
                s = sc.nextLine();
                System.out.println("true = Horizontale, false = Vertiacale");
                boolean b = sc.nextBoolean();
                sc.nextLine();
                Place[] pl = j.checkPlace(s, grille, 2, b);
                p2 = new Torpilleur(pl, j, this, b);
                break;
            }
            catch (BateauxStartPointInvalide | BateauxMembreInvalide | RuntimeException e) {
                System.err.println("Erreur, merci de recommencer : " + e.getMessage());
            }

        }
        bl.add(p2);
        Jeux.clearScreen();
        System.out.println(grille.consolBateauFormat());
        while (true) {
            try {
                System.out.println("Ajouter le bateux de 1 de Longeur ex: A6");
                s = sc.nextLine();
                Place[] pl = j.checkPlace(s, grille, 1, true);
                p1 = new Croiseur(pl, j, this, true);
                break;
            }
            catch (BateauxStartPointInvalide | BateauxMembreInvalide | RuntimeException e) {
                System.err.println("Erreur, merci de recommencer : " + e.getMessage());
            }

        }
        bl.add(p1);
    }

    /**
     * @return Si le joueur est une IA.
     * @since 1.0
     */
    public boolean getAI() {
        return this.AI;
    }

    /**
     * Sets the <code>ai</code> field.
     *
     * @param AI Si le joueur est une IA.
     * @since 1.0
     */
    public void setAI(boolean AI) {
        this.AI = AI;
    }

    public void play(Jeux jeux) {
        System.out.println(getGrille().consolBateauFormat());
        Scanner sc = new Scanner(System.in);
        while (true) {
            Jeux.clearScreen();
            System.out.println(getGrille().consolTireFormat());
            try {
                new SavedObject(jeux).save();
            }
            catch (IOException e) {
                System.out.println("Erreur a la sauvegarde");
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
                    boolean b = jeux.tire(p, this);
                    System.out.println(b ? "Vous avez touché un bateau" : "Vous tomber dans l'eau, dommage");
                }
                catch (PlacementInvalid placementInvalid) {
                    System.out.println(placementInvalid.getMessage());
                    continue;
                }
                boolean allDead = true;
                for (Bateaux b : getGrille().getListBateaux()) {
                    allDead = allDead && b.dead();
                }
                if (allDead) {
                    System.out.println("Tout les bateaux sont mort, vous avez gagner");
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Player{" + "ai=" + AI
               + ", grille=" + grille
               + '}';
    }
}
