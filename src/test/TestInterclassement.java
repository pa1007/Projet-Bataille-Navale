package test;
import bateaux.Bateaux;
import bateaux.ContreTorpilleur;
import bateaux.Croiseur;
import bateaux.PorteAvion;
import bateaux.SousMarin;
import bateaux.Torpilleur;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import jeux.Grille;
import jeux.Jeux;
import jeux.ModeDeJeux;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.*;

import utils.Interclassment;
import utils.Player;

public class TestInterclassement {

    @Test
    public void sortByTaille() throws BateauxStartPointInvalide, BateauxMembreInvalide {
    	Interclassment ic = new Interclassment();
    	List<Bateaux> res = new ArrayList<>();
    	Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
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
    	List<Bateaux> att =  new ArrayList<>(b);
    	Collections.reverse(att);
    	
    	
        res = Interclassment.sortByTaille(b);
        
        
    	assertArrayEquals("La liste n'est pas correctement triée",att.toArray(new Bateaux[0]), res.toArray(new Bateaux[0]));
    }

    @Test
    public void sortByDamage() {
    }
}