package test;

import bateaux.*;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import jeux.Grille;
import jeux.Jeux;
import jeux.ModeDeJeux;
import jeux.Place;
import org.junit.Test;
import utils.Interclassment;
import utils.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertArrayEquals;

public class TestInterclassement {

    @Test
    public void sortByTaille() throws BateauxStartPointInvalide, BateauxMembreInvalide {
        List<Bateaux> res;
        Player        p  = new Player();
        List<Player>  pL = new ArrayList<>();
        pL.add(p);
        Jeux          j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille        g = new Grille(15, 15, j, p);
        List<Bateaux> b = new ArrayList<>();
        p.setGrille(g);
        b.add(new ContreTorpilleur(j.checkPlace("A1", g, 5, true), j, p, true));
        g.setListBateaux(b);
        b.add(new Croiseur(j.checkPlace("A2", g, 4, true), j, p, true));
        b.add(new PorteAvion(j.checkPlace("A3", g, 3, true), j, p, true));
        b.add(new SousMarin(j.checkPlace("A4", g, 2, true), j, p, true));
        b.add(new Torpilleur(j.checkPlace("A5", g, 1, true), j, p, true));
        List<Bateaux> att = new ArrayList<>(b);
        Collections.reverse(att);


        res = Interclassment.sortByTaille(b);


        assertArrayEquals(
                "La liste n'est pas correctement triée",
                att.toArray(new Bateaux[0]),
                res.toArray(new Bateaux[0])
        );
    }

    @Test
    public void sortByDamage() throws BateauxMembreInvalide, BateauxStartPointInvalide {
        List<Bateaux> res;
        Player        p  = new Player();
        List<Player>  pL = new ArrayList<>();
        pL.add(p);
        Jeux          j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille        g = new Grille(15, 15, j, p);
        List<Bateaux> b = new ArrayList<>();
        p.setGrille(g);
        b.add(new ContreTorpilleur(j.checkPlace("A1", g, 5, true), j, p, true));
        g.setListBateaux(b);
        b.add(new Croiseur(j.checkPlace("A2", g, 4, true), j, p, true));
        b.add(new PorteAvion(j.checkPlace("A3", g, 3, true), j, p, true));
        b.add(new SousMarin(j.checkPlace("A4", g, 2, true), j, p, true));
        b.add(new Torpilleur(j.checkPlace("A5", g, 1, true), j, p, true));
        b.get(0).toucher(new Place("A1"));
        b.get(0).toucher(new Place("B1"));
        b.get(2).toucher(new Place("A3"));
        b.get(2).toucher(new Place("B3"));
        b.get(4).toucher(new Place("A5"));
        List<Bateaux> att = new ArrayList<>();
        att.add(b.get(4));
        att.add(b.get(2));
        att.add(b.get(0));
        att.add(b.get(3));
        att.add(b.get(1));
        res = Interclassment.sortByDamage(b);
        assertArrayEquals("Le trie n'est pas bon", att.toArray(new Bateaux[0]), res.toArray(new Bateaux[0]));
    }
}