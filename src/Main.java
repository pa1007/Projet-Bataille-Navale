import exception.GrilleNonCreeException;
import exception.LoadSaveException;
import jeux.Grille;
import jeux.Jeux;
import jeux.ModeDeJeux;
import utils.Player;
import utils.SavedObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws GrilleNonCreeException, IOException, LoadSaveException {

        menuInteractif();
    }

    /**
     * methode statique qui lance un menu interactif qui permet de choisir les différents mode de jeux
     * et d'accéder aux paramètres
     */
    public static void menuInteractif() throws GrilleNonCreeException, IOException, LoadSaveException {
        int     choix;
        Player  p1 = new Player();
        Jeux    j;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans la bataille navale :");
        System.out.println("1 : MonoJoeur");
        System.out.println("9 : Changer le path de sauvegarde (A garder pour retrouver les sauvegaedes )");
        System.out.println("10 : Reprendre la derniere partie from savedFile");
        System.out.println("11 : Reprendre la derniere partie from un autre fichier");
        System.out.println("100 : Quitter");
        choix = sc.nextInt();
        sc.nextLine();
        switch (choix) {
            case 1:
                List<Player> pL = new ArrayList<>();
                pL.add(p1);
                j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
                modifTailleGrille(j);
                j.lancerPartie();
                break;
            case 9:
                System.out.println("Le path de la sauvegarde :");
                SavedObject.changePath(sc.nextLine());
                menuInteractif();
                break;
            case 11:
                System.out.println("Le path de la sauvegarde :");
                SavedObject.changePath(sc.nextLine());
            case 10:
                SavedObject o = SavedObject.load();
                o.getJeux().reprendrePartie();
                break;
            case 100:
                System.exit(0);

        }
    }

    /**
     * methode statique qui permet au joueur de modifier la taille de la grille avant de jouer
     */
    public static void modifTailleGrille(Jeux j) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de case horizontales : ");
        int horizontale = sc.nextInt();
        while (horizontale < 10 || horizontale >= 26) {
            System.out.println("Horizontale non valide");
            horizontale = sc.nextInt();
        }

        System.out.println("Nombre de case verticales : ");
        int verticale = sc.nextInt();
        while (verticale < 10 || verticale >= 26) {
            System.out.println("Verticale non valide");
            verticale = sc.nextInt();
        }
        for (Player p : j.getListPlayer()) {
            Grille g = new Grille(horizontale, verticale, j, p);
            p.setGrille(g);
        }
    }

}
