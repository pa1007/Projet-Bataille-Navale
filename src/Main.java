import exception.GrilleNonCreeException;
import exception.LoadSaveException;
import graphic.GraphicMain;
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
     * Methode statique qui lance un menu interactif qui permet de choisir les différents modes de jeux
     * et d'accéder aux paramètres.
     */
    public static void menuInteractif() throws GrilleNonCreeException, IOException, LoadSaveException {
        int     choix;
        Player  p1 = new Player();
        Jeux    j;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans la bataille navale :");
        System.out.println("1 : MonoJoueur");
        System.out.println("2 : Lancer une partie en mode graphique!!");
        System.out.println("9 : Changer le path de sauvegarde (A garder pour retrouver les sauvegaedes )");
        System.out.println("10 : Reprendre la derniere partie from savedFile");
        System.out.println("11 : Reprendre la derniere partie from un autre fichier");
        System.out.println("50 : Regles du jeux et codes couleurs de la partie graphique");
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
            case 2:
            	GraphicMain main = new GraphicMain();
                main.launchGame();
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
            case 50:
            	reglesDuJeux();
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
    
    /**
     * Methode statique qui permet d'afficher les regles du jeux dans la console.
     */
    public static void reglesDuJeux() {
    	StringBuffer b = new StringBuffer();
    	String string;
    	//regles console
    	string = "\n BIENVENUE DANS LA BATAILLE NAVALE !";
    	string += "\n Partie console :\n" + 
    			"Dans la console vous pouvez jouer en monojoueur uniquement. \n" + 
    			"Symboles :\n";
    	string += "- « 0 » : Quand la case est inconnue\n" + 
    			"- « p » : Quand la case est obstruee ‘ile par exemple‘\n" + 
    			"- « B » : Quand la case est occupee par un bateau\n" + 
    			"- « ┼ » : Quand un bateau a ete touche\n" + 
    			"- « Ø » : Quand vous avez touche l’eau";
    	
    	//regles graphique
    	string += "\n\nPartie graphique :\n" + 
    			"Dans la partie graphique vous pouvez jouer en monojoueur ou bien contre l’intelligence artificelle « VS_IA »\n" + 
    			"Vous pouvez aussi placer vos bateaux de maniere aleatoire.\n" + 
    			"Code Couleur :\n" + 
    			"- case bleu clair : Quand la case est inconnue « eau »\n" + 
    			"- croix rouge = Quand vous avez touche l’eau\n" + 
    			"- croix rouge sur fond noir = Quand un bateau a ete touche\n" + 
    			"- (mode ia seulement) case bleu fonce = Quand l’ia a tire sur votre grille";
    	b.append(string);
    	System.out.println(b);
    }

}
