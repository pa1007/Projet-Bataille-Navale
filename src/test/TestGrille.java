package test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.*;

import jeux.Grille;
import jeux.Jeux;
import jeux.ModeDeJeux;
import jeux.Place;
import utils.Player;

public class TestGrille {

    @Test
    public void placeValide() {
    }

    @Test
    public void inBoundNormal() {
    	Player p  = new Player();
    	List<Player> pL = new ArrayList<>();
        pL.add(p);
    	Jeux          j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille        g = new Grille(15, 15, j, p);
        Place temp = new Place("A6");
        
        boolean res = g.inBound(temp);
        
        assertEquals("La place doit etre dans la grille",true, res);
    }
    
    @Test
    public void inBoundOversizedXaxe() {
    	Player p  = new Player();
    	List<Player> pL = new ArrayList<>();
        pL.add(p);
    	Jeux          j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille        g = new Grille(15, 15, j, p);
        Place temp = new Place("A55");
        
        boolean res = g.inBound(temp);
        
        assertEquals("La place ne doit pas etre dans la grille",false, res);
    }
    
    @Test
    public void inBoundOversizedYaxe() {
    	Player p  = new Player();
    	List<Player> pL = new ArrayList<>();
        pL.add(p);
    	Jeux          j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille        g = new Grille(15, 15, j, p);
        Place temp = new Place("Z1");
        
        boolean res = g.inBound(temp);
        
        assertEquals("La place ne doit pas etre dans la grille",false, res);
    }

    @Test
    public void consolBateauFormat() {
    }

    @Test
    public void consolTireFormat() {
    }

    @Test
    public void addNewTire() {
    }

    @Test
    public void addFire() {
    }
}