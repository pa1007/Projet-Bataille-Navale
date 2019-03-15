package utils;

public class Player {

    /**
     * Si le joueur est une IA.
     *
     * @since 1.0
     */
    private boolean AI;

    public Player() {
        System.out.println();
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
