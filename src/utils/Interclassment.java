package utils;

import bateaux.Bateaux;
import java.util.*;

public class Interclassment {

    /**
     * Methode qui trie une liste de bateaux en fonction de leurs tailles.
     *
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
     * Methode qui trie une liste de bateaux en fonction de leurs degats.<br>
     * Du plus toucher au moins toucher
     *
     * @param batLis liste de bateau a trier
     * @return la liste de bateau trie par degats.
     */
    public static List<Bateaux> sortByDamage(List<Bateaux> batLis) {
        List<Bateaux> res = new ArrayList<>(batLis);
        res.sort(new Compare());
        Collections.reverse(res);
        return res;
    }

    /**
     * Class interne qui permet de classer les bateux pour leur pourcentage de degats
     */
    static class Compare implements Comparator<Bateaux> {

        /**
         * @param o1 Le premier bateau
         * @param o2 Le 2eme bateau
         * @return <il>
         * <ul>0 si leur pourcentage est egal</ul>
         * <ul>1 si le pourcnetage de dégats du premier et sup a celui du deuxieme</ul>
         * <ul>-1 si le pourcnetage de dégats du deuxieme et sup a celui du premier</ul>
         * </il>
         */
        @Override
        public int compare(Bateaux o1, Bateaux o2) {
            if (o1.getPurcentDamage() == o2.getPurcentDamage()) {
                return 0;
            }
            else if (o1.getPurcentDamage() > o2.getPurcentDamage()) {
                return 1;
            }
            return -1;
        }
    }

}
