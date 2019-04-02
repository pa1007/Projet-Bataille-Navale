package exception;

public class LoadSaveException extends MesException {

    /**
     * Cree lorsqu'il y a une erreur dans la sauvegarde
     */
    public LoadSaveException() {
        super("Erreur lors du loading de la save");
    }
}
