package utils;

import exception.LoadSaveException;
import jeux.Jeux;
import java.io.*;

/**
 * Dans cette classe, chaque attribut permetra de recuperer un object important
 */
public class SavedObject implements Serializable {

    /**
     * Attribut String qui correspond au chemin de sauvegarde.
     */
    private static String path = "Saved.dat";

    /**
     * Attribut qui correspond au jeux
     */
    private Jeux jeux;

    /**
     * Constructeur de saveobject.
     * @param j jeux a sauvegarder. 
     */
    public SavedObject(Jeux j) {
        this.jeux = j;
    }

    /**
     * Methode qui permet de sauvegarder un objet.
     * @throws IOException erreur avec l'ouverture ou les flux.
     */
    public void save() throws IOException {
        ObjectOutputStream oIS = new ObjectOutputStream(new FileOutputStream(path));
        oIS.writeObject(this);
        oIS.close();
    }

    /**
     * @return le jeux.
     */
    public Jeux getJeux() {
        return jeux;
    }

    @Override
    public String toString() {
        return "SavedObject{" + "jeux=" + jeux
               + '}';
    }

    /**
     * Methode qui renvoie un objet sauvegarde.
     * @return l'objet sauvegarde.
     * @throws IOException
     * @throws LoadSaveException
     */
    public static SavedObject load() throws IOException, LoadSaveException {
        ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(path));
        try {
            SavedObject o = (SavedObject) oIS.readObject();
            oIS.close();
            return o;
        }
        catch (ClassNotFoundException | InvalidClassException e) {
            throw new LoadSaveException();
        }

    }

    /**
     * Methode qui permet de changer le chemin de sauvegarde.
     * @param path correspond au chemin de sauvegarde.
     */
    public static void changePath(String path) {
        SavedObject.path = path;
    }
}
