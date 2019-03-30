package jeux;

import bateaux.Bateaux;
import utils.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grille implements Serializable {


    /**
     * Ou l'enemi a tirer, en cas de mono joueur, ça reste vide.
     */
    private List<Place>   enemyFire;
    /**
     * Nombre de cases verticales.
     */
    private int           verticale;
    /**
     * Nombre de cases horizontales.
     */
    private int           horizontal;
    /**
     * Le player lie a la grille.
     */
    private Player        player;
    /**
     * La liste des bateaux.
     */
    private List<Bateaux> listBateaux;
    /**
     * Liste des tires effectuer.
     */
    private List<Tire>    tires;
    /**
     * Le parametre permettant de relier je jeux avec la grille
     */
    private Jeux          jeux;


    /**
     * Constructeur de la grille
     *
     * @param verticale
     * @param horizontal
     * @param jeux
     * @param p
     */
    public Grille(int verticale, int horizontal, Jeux jeux, Player p) {
        this.verticale = verticale;
        this.horizontal = horizontal;
        this.jeux = jeux;
        this.player = p;
        tires = new ArrayList<>();
        listBateaux = new ArrayList<>();
    }

    /**
     * @return Ou l'enemi a tirer, en cas de mono joueur, ça reste vide.
     */
    public List<Place> getEnemyFire() {
        return this.enemyFire;
    }

    /**
     * Sets the <code>enemyFire</code> field.
     *
     * @param enemyFire Ou l'enemi a tirer, en cas de mono joueur, ça reste vide.
     */
    public void setEnemyFire(List<Place> enemyFire) {
        this.enemyFire = enemyFire;
    }

    /**
     * @return Liste des tires effectues.
     */
    public List<Tire> getTires() {
        return this.tires;
    }

    /**
     * Initialise les <code>tires</code>.
     *
     * @param tires Liste des tires effectuer.
     */
    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }

    /**
     * @return La liste des bateaux.
     */
    public List<Bateaux> getListBateaux() {
        return this.listBateaux;
    }

    /**
     * Initialise la <code>listBateaux</code>.
     *
     * @param listBateaux La liste des bateaux.
     */
    public void setListBateaux(List<Bateaux> listBateaux) {
        this.listBateaux = listBateaux;
    }

    /**
     * @return Le player lie a la grille.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Initialise le <code>player</code>.
     *
     * @param player Le player lie a la grille.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return Nombre de cases verticales.
     */
    public int getVerticale() {
        return this.verticale;
    }


    /**
     * @return Nombre de cases horizontales.
     */
    public int getHorizontal() {
        return this.horizontal;
    }

    /**
     * Methode publique qui verifie si une place est valide
     * (pas de bateaux, pas d'iles, pas le bord de la grille).
     *
     * @param pl
     * @return boolean si la place est valide
     */
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

    /**
     * Methode qui teste si une place est valide dans la grille.
     *
     * @param pl
     * @return boolean, si la place est valide
     */
    public boolean inBound(Place pl) {
        return !pl.is("A0")
               && pl.getColumnNumber() <= horizontal - 1
               && pl.getRow() <= verticale
               && pl.getColumnNumber() >= 0 && pl.getRow() > 0;
    }

    /**
     * Methode qui formate l'affichage des bateaux dans la console.
     *
     * @return String
     */
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

    /**
     * Methode qui formate l'affichage des tires dans la console.
     *
     * @return String
     */
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

    /**
     * Methode qui ajoute un nouveau tire a la grille
     * (remplace un grille.getTires().add(tire)).
     *
     * @param tire
     */
    public void addNewTire(Tire tire) {
        tires.add(tire);
    }

    /**
     * Cette method est utiliser par l'IA pour ajouter un tire effectuer et permetre l'affichage
     *
     * @param p la pla ce tirer par l'enemy
     */
    public void addFire(Place p) {
        if (enemyFire == null) {
            enemyFire = new ArrayList<>();
        }
        enemyFire.add(p);
    }

    /**
     * Methode qui valide si la place est libre
     * (ile)
     *
     * @param pl
     * @return
     */
    private boolean obstuer(Place pl) {
        return jeux.getObstrue().contains(pl);
    }

    /**
     * Methode qui renvoie la première ligne de la grille en console
     *
     * @return String
     */
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
