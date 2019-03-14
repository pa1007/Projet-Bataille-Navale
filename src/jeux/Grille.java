package jeux;

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
     * Le parametre permetant de relier je jeux avec la grille
     */
    private Jeux jeux;

    public Grille(int verticale, int horizontal, Jeux jeux) {
        this.verticale = verticale;
        this.horizontal = horizontal;
        this.jeux = jeux;
    }

    /**
     * @return Nombre de case verticale.
     */
    public int getVerticale() {
        return this.verticale;
    }

    /**
     * Sets the <code>verticale</code> field.
     *
     * @param verticale Nombre de case verticale.
     */
    public void setVerticale(int verticale) {
        this.verticale = verticale;
    }

    /**
     * @return L'attribut horizontale.
     */
    public int getHorizontal() {
        return this.horizontal;
    }

    /**
     * Sets the <code>horizontal</code> field.
     *
     * @param horizontal L'attribut horizontale.
     */
    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    private String consolForamt() {
        char[]        alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb    = new StringBuilder("   ");
        int           t     = horizontal, sL = -1;
        for (int i = 0; i < t; i++) {
            if (i > 25) {
                t -= 25;
                i = 0;
                sL++;
            }
            if (sL != -1) {
                sb.append(alpha[sL]);
            }
            sb.append(alpha[i]).append(" ");
        }
        sb.append("\n");
        for (int i = 1; i < verticale + 1; i++) {
            sb.append(i < 10 ? "0" + i : i);
            for (int j = 0; j < horizontal; j++) {
                sb.append(jeux.getPlace(i, j) == null ? " x" : jeux.getPlace(i, j));
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
