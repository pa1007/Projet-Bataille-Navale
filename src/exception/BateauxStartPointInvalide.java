package exception;

public class BateauxStartPointInvalide extends Exception {

    public BateauxStartPointInvalide() {
        super("la place du bateau n'est pas valide (en dehors, sur un autre bateau ou Obstuer)");
    }

    public BateauxStartPointInvalide(String message) {
        super(message);
    }
}