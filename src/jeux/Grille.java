package jeux;

import Bateaux;
import utils.Player;
import java.util.List;

public class Grille {

    /**
     * Nombre de case verticale.
     */
    private int verticale;
    /**
     * L'attribut horizontale.
     *
     * @since 1.0
     */
    private int horizontal;


    /**
     * Le player linker a la grille.
     *
     * @since 1.0
     */
    private Player player;


    /**
     * La liste des bateaux.
     *
     * @since 1.0
     */
    private List<Bateaux> listBateaux;

    /**
     * Le parametre permetant de relier je jeux avec la grille
     */
    private Jeux jeux;

    public Grille(int verticale, int horizontal, Jeux jeux, Player p) {
        this.verticale = verticale;
        this.horizontal = horizontal;
        this.jeux = jeux;
        this.player = p;
    }

    /**
     * @return La liste des bateaux.
     * @since 1.0
     */
    public List<Bateaux> getListBateaux() {
        return this.listBateaux;
    }

    /**
     * Sets the <code>listBateaux</code> field.
     *
     * @param listBateaux La liste des bateaux.
     * @since 1.0
     */
    public void setListBateaux(List<Bateaux> listBateaux) {
        this.listBateaux = listBateaux;
    }

    /**
     * @return Le player linker a la grille.
     * @since 1.0
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the <code>player</code> field.
     *
     * @param player Le player linker a la grille.
     * @since 1.0
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return Nombre de case verticale.
     */
    public int getVerticale() {
        return this.verticale;
    }


    /**
     * @return L'attribut horizontale.
     */
    public int getHorizontal() {
        return this.horizontal;
    }

    public boolean placeValide(Place pl) {

    }


    private String consolForamt() {
        char[]        alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb    = new StringBuilder("   ");
        int           t     = horizontal;
        for (int i = 0; i < t; i++) {
            sb.append(alpha[i]).append(" ");
        }
        sb.append("\n");
        for (int i = 1; i < verticale + 1; i++) {
            sb.append(i < 10 ? "0" + i : i);
            sb.append(" ");
            for (int j = 0; j < horizontal; j++) {
                sb.append("x ");
            }
            sb.append("\n");
        }
        return sb.toString();

    }

    @Override
    public String toString() {
        return consolForamt();
    }
}
