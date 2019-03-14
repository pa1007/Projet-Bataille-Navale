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

    public Grille(int verticale, int horizontal) {
        this.verticale = verticale;
        this.horizontal = horizontal;
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
}
