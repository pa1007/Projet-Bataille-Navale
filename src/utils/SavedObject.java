package utils;

import exception.LoadSaveException;
import jeux.Jeux;
import java.io.*;

/**
 * Dans cette classe, chaque attribut permetra de recuperer un object important
 */
public class SavedObject implements Serializable {

    private static String path = "Saved.dat";

    private Jeux jeux;

    public SavedObject(Jeux j) {
        this.jeux = j;
    }

    public void save() throws IOException {
        ObjectOutputStream oIS = new ObjectOutputStream(new FileOutputStream(path));
        oIS.writeObject(this);
        oIS.close();
    }

    public Jeux getJeux() {
        return jeux;
    }

    @Override
    public String toString() {
        return "SavedObject{" + "jeux=" + jeux
               + '}';
    }

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

    public static void changePath(String path) {
        SavedObject.path = path;
    }
}
