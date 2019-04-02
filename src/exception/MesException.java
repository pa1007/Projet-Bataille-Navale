package exception;

public abstract class MesException extends Exception {

    /**
     * Clase principale des exception, permet de toutes les captuer avec MesException mais avec un message particulier
     *
     * @param s le message a montrer
     */
    public MesException(String s) {
        super(s);
    }

    /**
     * Clase principale des exception, permet de toutes les captuer avec MesException
     */
    public MesException() {
        super();
    }
}
