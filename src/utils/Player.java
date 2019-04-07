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
import java.util.Random;
import java.util.Scanner;

public class Player implements Serializable {

    /**
     * Si le joueur est une IA.
     */
    protected boolean AI;


    /**
     * La grille du joueur.
     */
    protected Grille grille;

    /**
     * Constructeur de Player.
     */
    public Player() {
    }

    /**
     * @return La grille du joueur.
     */
    public Grille getGrille() {
        return this.grille;
    }

    /**
     * Sets the <code>grille</code> field.
     *
     * @param grille La grille du joueur.
     */
    public void setGrille(Grille grille) {
        this.grille = grille;
    }


    /**
     * Methode qui permet de placer un bateau dans un jeux.
     *
     * @param j jeux sur lequel placer le bateau.
     * @throws GrilleNonCreeException si la grille n'a pas pu etre cree.
     */
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
     */
    public boolean getAI() {
        return this.AI;
    }

    /**
     * Initialise le <code>ai</code>.
     *
     * @param AI Si le joueur est une IA.
     */
    public void setAI(boolean AI) {
        this.AI = AI;
    }

    /**
     * Methode qui permet de lancer le jeux, et de le sauvegarder.
     *
     * @param jeux jeux a lancer.
     */
    public void play(Jeux jeux) {
        System.out.println(getGrille().consolBateauFormat());
        Scanner sc = new Scanner(System.in);
        while (true) {

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
                    Jeux.clearScreen();
                    System.err.println("Place non valide !");
                    continue;
                }
                Jeux.clearScreen();
                try {
                    boolean b = jeux.tire(p, jeux.getOtherPlayer(this));
                    System.out.println(b ? "Vous avez touché un bateau" : "Vous tomber dans l'eau, dommage");
                }
                catch (PlacementInvalid placementInvalid) {
                    Jeux.clearScreen();
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

    /**
     * Methode qui permet d'obstruer un case (ile).
     *
     * @param jeux jeux auxquel ajouter un cas obstrue.
     * @throws GrilleNonCreeException si la grille n'a pas pu etre cree.
     */
    public void obstruerCase(Jeux jeux) throws GrilleNonCreeException {
        if (grille != null) {
            System.out.println(grille.consolBateauFormat());
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println(
                        "Choisisez la place que vous voulez obstuer (ex A6), dites STOP pour passer/coninuer !");
                String s = scanner.nextLine();
                if (s.equalsIgnoreCase("Stop")) {
                    break;
                }
                else {
                    try {
                        Place pl = new Place(s);
                        if (grille.placeValide(pl)) {
                            jeux.addPlaceObstue(pl);
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Place non valide, retenter");
                    }
                }
            }
        }
        else {
            throw new GrilleNonCreeException();
        }
    }

    /**
     * Methode qui permet d'obtenir un nombre aleatoire entre deux autres entiers.
     *
     * @param min borne inferieur du random.
     * @param max borne superieur du random.
     * @return l'entier compris entre les deux parametres.
     */
    public int randBetween(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }

    /**
     * Methode qui permet de placer un bateau de maniere aleatoir sur la grille d'un jeux.
     *
     * @param j jeux sur lequel placer un bateau.
     * @throws GrilleNonCreeException si la grille n'a pas ete cree.
     */
    public void placerBateauRandom(Jeux j) throws GrilleNonCreeException {
        if (grille != null) {
            Bateaux       b1, b2, b3, b4, b5;
            List<Bateaux> bateauxList = new ArrayList<>();
            while (true) {
                Place   p = getRandomPlace();
                boolean b = randBetween(0, 1) != 0;
                Place[] places;
                try {
                    places = j.checkPlace(p.toString(), grille, 5, b);
                    b1 = new ContreTorpilleur(places, j, this, b);
                    bateauxList.add(b1);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide ignored) {
                }
            }
            grille.setListBateaux(bateauxList);
            while (true) {
                Place   p = getRandomPlace();
                boolean b = randBetween(0, 1) != 0;
                Place[] places;
                try {
                    places = j.checkPlace(p.toString(), grille, 4, b);
                    b2 = new PorteAvion(places, j, this, b);
                    bateauxList.add(b2);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide ignored) {
                }
            }
            while (true) {
                Place   p = getRandomPlace();
                boolean b = randBetween(0, 1) != 0;
                Place[] places;
                try {
                    places = j.checkPlace(p.toString(), grille, 3, b);
                    b3 = new SousMarin(places, j, this, b);
                    bateauxList.add(b3);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide ignored) {
                }
            }
            while (true) {
                Place   p = getRandomPlace();
                boolean b = randBetween(0, 1) != 0;
                Place[] places;
                try {
                    places = j.checkPlace(p.toString(), grille, 2, b);
                    b4 = new SousMarin(places, j, this, b);
                    bateauxList.add(b4);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide ignored) {
                }
            }
            while (true) {
                Place   p = getRandomPlace();
                boolean b = randBetween(0, 1) != 0;
                Place[] places;
                try {
                    places = j.checkPlace(p.toString(), grille, 1, b);
                    b5 = new SousMarin(places, j, this, b);
                    bateauxList.add(b5);
                    break;
                }
                catch (BateauxStartPointInvalide | BateauxMembreInvalide ignored) {
                }
            }
        }
        else {
            throw new GrilleNonCreeException();
        }
    }

    protected Place getRandomPlace() {
        return new Place(randBetween(1, grille.getVerticale()), randBetween(1, grille.getHorizontal()));
    }

    @Override
    public String toString() {
        return "Player{" + "ai=" + AI
               + ", grille=" + grille
               + '}';
    }
}
