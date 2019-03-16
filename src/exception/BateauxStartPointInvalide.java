package exception;

public class BateauxStartPointInvalide extends Exception {

    public BateauxStartPointInvalide() {
        super("la place du bateau n'est pas valide (en dehors ou sur un autre bateau");
    }

    public BateauxStartPointInvalide(String message) {
        super(message);
    }
}