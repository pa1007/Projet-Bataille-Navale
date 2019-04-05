package test;

import org.junit.Test;

import bateaux.Bateaux;
import bateaux.ContreTorpilleur;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import jeux.Grille;
import jeux.Jeux;
import jeux.ModeDeJeux;
import jeux.Place;

import static org.junit.Assert.assertEquals;

import java.util.*;
import utils.Player;

public class TestBateaux {

    @Test
    public void toucher() throws BateauxStartPointInvalide, BateauxMembreInvalide {
    	Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux          j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille        g = new Grille(15, 15, j, p);
        List<Place> att = new ArrayList<>();
        att.add(new Place("A6"));
        
        Bateaux b = new ContreTorpilleur(j.checkPlace("A6", g, 5, true), j, p, true);
        
        b.toucher(new Place("A6"));
        
        assertEquals("La liste de cases touchee n'est pas la meme",att,b.getTouchedPlace());
    }

    @Test
    public void nbToucher() {
    	
    }

    @Test
    public void getPurcentDamage() {
    }

    @Test
    public void dead() {
    }

    @Test
    public void getLetter() {
    }

    @Test
    public void taille() {
    }
}