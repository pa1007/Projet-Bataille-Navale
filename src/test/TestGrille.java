package test;

import bateaux.Bateaux;
import bateaux.ContreTorpilleur;
import jeux.*;
import org.junit.Test;
import utils.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

public class TestGrille {

    /**
     * Test avec une place actuelement vide
     */
    @Test
    public void testPlaceValideVrai() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        boolean r = g.placeValide(new Place("A5"));

        assertTrue("La place dois etre vide", r);
    }

    /**
     * Test avec une place en dehors
     */
    @Test
    public void testPlaceValidePasDansGrile() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        boolean r = g.placeValide(new Place("Z5"));

        assertFalse("La place n'est pas dans le grille", r);
    }

    /**
     * Test avec une place obstruer
     */
    @Test
    public void testPlaceValideObstuer() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);
        j.addPlaceObstue(new Place("A5"));

        boolean r = g.placeValide(new Place("A5"));

        assertFalse("La place est obstruer", r);
    }

    /**
     * Test avec une place ou un bateau est deçu
     */
    @Test
    public void testPlaceValideOuBateauEst() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux    j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille  g = new Grille(15, 15, j, p);
        Bateaux b = new ContreTorpilleur(new Place[]{new Place("A5")}, j, p, true);
        g.setListBateaux(Collections.singletonList(b));

        boolean r = g.placeValide(new Place("A5"));

        assertFalse("La place est ocupper par un bateau", r);
    }

    /**
     * Test avec une place avec un bateau près de cette case
     */
    @Test
    public void testPlaceValidePresBateau() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux    j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille  g = new Grille(15, 15, j, p);
        Bateaux b = new ContreTorpilleur(new Place[]{new Place("A6")}, j, p, true);
        g.setListBateaux(Collections.singletonList(b));

        boolean r = g.placeValide(new Place("A5"));

        assertTrue("La place est ocupper par un bateau", r);
    }

    @Test
    public void testInBoundNormal() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j    = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g    = new Grille(15, 15, j, p);
        Place  temp = new Place("A6");

        boolean res = g.inBound(temp);

        assertTrue("La place doit etre dans la grille", res);
    }

    @Test
    public void testInBoundOversizedXaxe() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j    = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g    = new Grille(15, 15, j, p);
        Place  temp = new Place("A55");

        boolean res = g.inBound(temp);

        assertFalse("La place ne doit pas etre dans la grille", res);
    }

    @Test
    public void testInBoundOversizedYaxe() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j    = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g    = new Grille(15, 15, j, p);
        Place  temp = new Place("Z1");

        boolean res = g.inBound(temp);

        assertFalse("La place ne doit pas etre dans la grille", res);
    }

    @Test
    public void testConsolBateauFormat() {
    }

    @Test
    public void testConsolTireFormat() {
    }

    /**
     * Ici on test la bon insertion dans une liste
     */
    @Test
    public void testAddNewTire() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);
        Tire   t = new Tire(false, new Place("A5"));

        g.addNewTire(t);

        assertEquals("La liste n'a pas augementer de taille", 1, g.getTires().size());
        assertEquals("Le tire est bien dans la list", t, g.getTires().get(0));
    }

    /**
     * Ici on test la bon insertion dans une liste
     */
    @Test
    public void testAddFire() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        g.addFire(new Place("A6"));

        assertEquals("La liste n'a pas augementer de taille", 1, g.getEnemyFire().size());
        assertEquals("Le tire est bien dans la list", new Place("A6"), g.getEnemyFire().get(0));
    }
}