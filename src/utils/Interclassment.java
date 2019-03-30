package utils;

import bateaux.Bateaux;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Interclassment {

    /**
     * Methode qui trie une liste de bateaux en fonction de leurs tailles.
     * @param batLis liste de bateau a trier
     * @return la liste de bateau trie par taille.
     */
    public static List<Bateaux> sortByTaille(List<Bateaux> batLis) {
        int           tmp  = batLis.size();
        List<Bateaux> res  = new ArrayList<>(tmp);
        List<Bateaux> temp = new ArrayList<>(batLis);
        int           min  = 1;
        while (res.size() != tmp) {
            Iterator<Bateaux> it = temp.iterator();
            while (it.hasNext()) {
                Bateaux b = it.next();
                if (b.taille() <= min) {
                    res.add(b);
                    it.remove();
                    min = b.taille() + 1;
                }
            }
        }
        return res;
    }

    /**
     * Methode qui trie une liste de bateaux en fonction de leurs degats.
     * @param batLis liste de bateau a trier
     * @return la liste de bateau trie par degats.
     */
    public static List<Bateaux> sortByDamage(List<Bateaux> batLis) {
        int           tmp  = batLis.size();
        List<Bateaux> res  = new ArrayList<>(tmp);
        List<Bateaux> temp = new ArrayList<>(batLis);
        double        min  = 1;
        while (res.size() != tmp) {
            Iterator<Bateaux> it = temp.iterator();
            while (it.hasNext()) {
                Bateaux b = it.next();
                if (b.taille() <= min) {
                    res.add(b);
                    it.remove();
                    min = b.getPurcentDamage() + 1;
                }
            }
        }
        return res;
    }
}
