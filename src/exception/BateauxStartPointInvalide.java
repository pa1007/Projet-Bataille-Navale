package exception;

public class BateauxStartPointInvalide extends MesException {

    /**
     * Si la place de depart du bateau n'est pas valide
     */
    public BateauxStartPointInvalide() {
        super("la place du bateau n'est pas valide (en dehors, sur un autre bateau ou Obstuer)");
    }

}