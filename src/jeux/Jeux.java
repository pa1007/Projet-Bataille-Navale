package jeux;

import java.util.Scanner;

public class Jeux {


    /**
     * methode statique qui lance un menu interactif qui permet de choisir les différents mode de jeux
     * et d'accéder aux paramètres
     */
    public static void menuInteractif(){
        int choix;

        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans la bataille navale :");
        System.out.println("1 : Jouer contre une autre joueur");
        System.out.println("2 : Jouer contre l'intelligence artificielle");
        System.out.println("3 : Choix de la taille de la grille");
        choix = sc.nextInt();

        switch (choix){
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
    public static void modifTailleGrille(){
        Scanner sc = new Scanner(System.in);
            System.out.println("Nombre de case horizontales : ");
            int horizontale = sc.nextInt();

            System.out.println("Nombre de case verticales : ");
            int verticale = sc.nextInt();

            Grille g = new Grille(horizontale, verticale);
    }


    /**
     * methode statique qui nettoie la console pour une meilleure lisibilité
     */
    public static void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }
}

