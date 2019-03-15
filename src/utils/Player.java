package utils;

import jeux.Grille;

public class Player {

    /**
     * Si le joueur est une IA.
     *
     * @since 1.0
     */
    private boolean AI;


    /**
     * La grille du joueur.
     *
     * @since 1.0
     */
    private Grille grille;


    public Player() {
    }

    /**
     * @return La grille du joueur.
     * @since 1.0
     */
    public Grille getGrille() {
        return this.grille;
    }

    /**
     * Sets the <code>grille</code> field.
     *
     * @param grille La grille du joueur.
     * @since 1.0
     */
    public void setGrille(Grille grille) {
        this.grille = grille;
    }


    public void placerBateau() {

    }

    /**
     * @return Si le joueur est une IA.
     * @since 1.0
     */
    public boolean getAI() {
        return this.AI;
    }

    /**
     * Sets the <code>AI</code> field.
     *
     * @param AI Si le joueur est une IA.
     * @since 1.0
     */
    public void setAI(boolean AI) {
        this.AI = AI;
    }
}
