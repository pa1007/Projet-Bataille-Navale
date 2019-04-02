package exception;

public class PlacementInvalid extends MesException {

    /**
     * Cree une exception Placement invalide avec un message pres defini
     */
    public PlacementInvalid() {
        super("Place non valide pour tirer");
    }

    /**
     * Cree une exception Placement invalide avec un message a metre
     *
     * @param message le message d'erreur
     */
    public PlacementInvalid(String message) {
        super(message);
    }
}
