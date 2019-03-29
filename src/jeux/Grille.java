package jeux;

import bateaux.Bateaux;
import utils.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grille implements Serializable {

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
     * Liste des tires effectuer.
     *
     * @since 1.0
     */
    private List<Tire> tires;

    /**
     * Le parametre permetant de relier je jeux avec la grille
     */
    private Jeux jeux;

    public Grille(int verticale, int horizontal, Jeux jeux, Player p) {
        this.verticale = verticale;
        this.horizontal = horizontal;
        this.jeux = jeux;
        this.player = p;
        tires = new ArrayList<>();
        listBateaux = new ArrayList<>();
    }

    /**
     * @return Liste des tires effectuer.
     * @since 1.0
     */
    public List<Tire> getTires() {
        return this.tires;
    }

    /**
     * Sets the <code>tires</code> field.
     *
     * @param tires Liste des tires effectuer.
     * @since 1.0
     */
    public void setTires(List<Tire> tires) {
        this.tires = tires;
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
        if (inBound(pl)) {
            if (!obstuer(pl)) {
                for (Bateaux b : listBateaux) {
                    if (b.getPlaces().contains(pl)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean inBound(Place pl) {
        return !pl.is("A0")
               && pl.getColumnNumber() <= horizontal - 1
               && pl.getRow() <= verticale
               && pl.getColumnNumber() >= 0 && pl.getRow() > 0;
    }

    public String consolBateauFormat() {
        StringBuilder sb = new StringBuilder(getPremiereLigne());
        for (int i = 1; i < verticale + 1; i++) {
            sb.append(i < 10 ? "0" + i : i);
            sb.append(" ");
            for (int j = 0; j < horizontal; j++) {
                String  w     = "O";
                boolean found = false;
                for (Place p : jeux.getObstrue()) {
                    if (p.getRow() == i && p.getColumnNumber() == j) {
                        w = "p";
                        found = true;
                        break;
                    }
                }
                for (Bateaux b : listBateaux) {
                    List<Place> plist = b.getPlaces();
                    for (Place p : plist) {
                        if (found) {
                            break;
                        }
                        if (p.getRow() == i && p.getColumnNumber() == j) {
                            w = b.getLetter();
                            found = true;
                            break;
                        }
                    }
                }

                sb.append(w);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();

    }

    public String consolTireFormat() {
        StringBuilder sb = new StringBuilder(getPremiereLigne());
        for (int i = 1; i <= verticale; i++) {
            sb.append(i < 10 ? "0" + i : i);
            sb.append(" ");
            for (int j = 0; j < horizontal; j++) {
                String w = "O";
                if (jeux.getObstrue().contains(new Place(j, i))) {
                    w = "p";
                }
                else {
                    for (Tire t : tires) {
                        Place p = t.getPlace();
                        if (p.getRow() == i && p.getColumnNumber() == j) {
                            w = t.toString();
                            break;
                        }
                    }
                }
                sb.append(w);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void addNewTire(Tire tire) {
        tires.add(tire);
    }

    private boolean obstuer(Place pl) {
        return jeux.getObstrue().contains(pl);
    }

    private String getPremiereLigne() {
        char[]        alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb    = new StringBuilder("   ");
        int           t     = horizontal;
        for (int i = 0; i < t; i++) {
            sb.append(alpha[i]).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Grille{" + "verticale=" + verticale
               + ", horizontal=" + horizontal
               + ", listBateaux=" + listBateaux
               + ", tires=" + tires
               + '}';
    }
}
