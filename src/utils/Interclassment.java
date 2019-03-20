package utils;

import bateaux.Bateaux;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Interclassment {

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
