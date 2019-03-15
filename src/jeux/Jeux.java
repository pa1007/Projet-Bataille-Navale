package jeux;

import exception.GrilleNonCreeException;
import utils.Player;
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

    public void lancerPartie() {

    }

    private void ajouterBateaux(Jeux j) throws GrilleNonCreeException {
        Scanner sc = new Scanner(System.in);
        for (Player p : j.getListPlayer()) {
            if (p.getGrille() == null) {
                throw new GrilleNonCreeException();
            }
            Grille g = p.getGrille();
            System.out.println(g);
            System.out.println("Ajouter le bateux de 5 de Longeur ex: A6");
            String s = sc.nextLine();
            System.out.println("true = Horizontale, false = Vertiacale");
            Place pl = checkPlace(s, g, 5, sc.nextBoolean(), sc);

            System.out.println("Ajouter le bateux de 4 de Longeur ex: A6");
            s = sc.nextLine();
            System.out.println("true = Horizontale, false = Vertiacale");
            Place p2 = checkPlace(s, g, 4, sc.nextBoolean(), sc);

            System.out.println("Ajouter le bateux de 3 de Longeur ex: A6");
            s = sc.nextLine();
            System.out.println("true = Horizontale, false = Vertiacale");
            Place p3 = checkPlace(s, g, 3, sc.nextBoolean(), sc);

            System.out.println("Ajouter le bateux de 2 de Longeur ex: A6");
            s = sc.nextLine();
            System.out.println("true = Horizontale, false = Vertiacale");
            Place p4 = checkPlace(s, g, 2, sc.nextBoolean(), sc);
            System.out.println("Ajouter le bateux de 1 de Longeur ex: A6");
            s = sc.nextLine();
            System.out.println("true = Horizontale, false = Vertiacale");
            Place p5 = checkPlace(s, g, 1, sc.nextBoolean(), sc);
        }
    }

    private Place checkPlace(String s, Grille g, int tailleBat, boolean b, Scanner sc) {
        Place pl = new Place(s);
        while (!g.placeValide(pl)) {
            System.out.println("Place non valide");
            pl = new Place();
        }
    }
}

