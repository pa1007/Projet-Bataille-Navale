package jeux;

import bateaux.Bateaux;
import utils.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Jeux {

    /**
     * La map princiaple pour avoir un bateau.
     */
    private Map<Player, List<Bateaux>> mapPlayerListBateaux;

    public Jeux() {
        mapPlayerListBateaux = new HashMap<>();
    }

    /**
     * @return La map princiaple pour avoir un bateau.
     */
    public Map<Player, List<Bateaux>> getMapPlayerListBateaux() {
        return this.mapPlayerListBateaux;
    }

    /**
     * Sets the <code>mapPlayerListBateaux</code> field.
     *
     * @param mapPlayerListBateaux La map princiaple pour avoir un bateau.
     */
    public void setMapPlayerListBateaux(Map<Player, List<Bateaux>> mapPlayerListBateaux) {
        this.mapPlayerListBateaux = mapPlayerListBateaux;
    }


    public Bateaux getPlace(int verticale, int horizontal) {
        Place p = new Place(verticale, horizontal);
        for (List<Bateaux> bat : mapPlayerListBateaux.values()) {
            for (Bateaux b : bat) {
                if (b.getPlaces().contains(p)) {
                    return b;
                }
            }
        }
        return null;
    }


    /**
     * methode statique qui lance un menu interactif qui permet de choisir les différents mode de jeux
     * et d'accéder aux paramètres
     */
    public static void menuInteractif() {
        int choix;

        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans la bataille navale :");
        System.out.println("1 : Jouer contre une autre joueur");
        System.out.println("2 : Jouer contre l'intelligence artificielle");
        System.out.println("3 : Choix de la taille de la grille");
        choix = sc.nextInt();

        switch (choix) {
            case 1:

                break;
            case 2:

                break;
            case 3:
                Jeux.modifTailleGrille();
                Jeux.clearScreen();
                Jeux.menuInteractif();
                break;

            default:

        }
    }


    /**
     * methode statique qui permet au joueur de modifier la taille de la grille avant de jouer
     */
    public static void modifTailleGrille() {
        Scanner sc = new Scanner(System.in);
        Jeux    j  = new Jeux(); // ajout de ça,faudra modif todo modif
        System.out.println("Nombre de case horizontales : ");
        int horizontale = sc.nextInt();

        System.out.println("Nombre de case verticales : ");
        int verticale = sc.nextInt();

        Grille g = new Grille(horizontale, verticale, j);
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

