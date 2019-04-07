package test;

import bateaux.Bateaux;
import bateaux.ContreTorpilleur;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import jeux.Grille;
import jeux.Jeux;
import jeux.ModeDeJeux;
import jeux.Place;
import org.junit.Test;
import utils.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class TestJeux {

    /**
     * Test de la methode checkPlace en horizontale
     *
     * @throws BateauxMembreInvalide     /
     * @throws BateauxStartPointInvalide /
     */
    @Test
    public void testCheckPlaceValideHorizontale() throws BateauxMembreInvalide, BateauxStartPointInvalide {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        Place[] pl  = j.checkPlace("A6", g, 5, true);
        Place[] att = new Place[]{new Place("A6"), new Place("B6"), new Place("C6"), new Place("D6"), new Place("E6")};

        assertArrayEquals("Les places ne sont pas bonne", att, pl);
    }

    /**
     * Test de la methode checkPlace en verticale
     *
     * @throws BateauxMembreInvalide     /
     * @throws BateauxStartPointInvalide /
     */
    @Test
    public void testCheckPlaceValideVerticale() throws BateauxMembreInvalide, BateauxStartPointInvalide {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        Place[] pl  = j.checkPlace("A6", g, 5, false);
        Place[] att = new Place[]{new Place("A6"), new Place("A7"), new Place("A8"), new Place("A9"), new Place("A10")};

        assertArrayEquals("Les places ne sont pas bonne", att, pl);
    }

    /**
     * Test de la methode checkPlace en horizontale en dehors
     */
    @Test
    public void testCheckPlaceHorizontaleMembreDehors() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(10, 10, j, p);

        try {
            j.checkPlace("G6", g, 5, true);
            fail("Une exception devrai etre lancée");
        }
        catch (Exception e) {
            assertTrue("L'exception n'est pas bonne", e instanceof BateauxMembreInvalide);
        }
    }

    /**
     * Test de la methode checkPlace en verticale en dehors
     */
    @Test
    public void testCheckPlaceVerticaleMembreDehors() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(10, 10, j, p);

        try {
            j.checkPlace("A8", g, 5, false);
            fail("Une exception devrai etre lancée");
        }
        catch (Exception e) {
            assertTrue("L'exception n'est pas bonne", e instanceof BateauxMembreInvalide);
        }
    }

    /**
     * Test de la methode checkPlace en horizontale en dehors
     */
    @Test
    public void testCheckPlaceHorizontaleTeteDehors() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(10, 10, j, p);

        try {
            j.checkPlace("Z6", g, 5, true);
            fail("Une exception devrai etre lancée");
        }
        catch (Exception e) {
            assertTrue("L'exception n'est pas bonne", e instanceof BateauxStartPointInvalide);
        }
    }

    /**
     * Test de la methode checkPlace en verticale en dehors
     */
    @Test
    public void testCheckPlaceVerticaleTeteDehors() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(10, 10, j, p);

        try {
            j.checkPlace("A50", g, 5, false);
            fail("Une exception devrai etre lancée");
        }
        catch (Exception e) {
            assertTrue("L'exception n'est pas bonne", e instanceof BateauxStartPointInvalide);
        }
    }

    /**
     * Test quand il n'y a pas de bateau
     */
    @Test
    public void bateauAtNoBateau() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(10, 10, j, p);

        Bateaux ba = new ContreTorpilleur(new Place[]{new Place("A5")}, j, p, true);
        g.setListBateaux(Collections.singletonList(ba));

        Bateaux b = j.bateauAt(new Place("A6"), g);

        assertNull("Il n'y a pas de bateau ici", b);
    }

    /**
     * Test quand il y a un bateau
     */
    @Test
    public void bateauAtBateau() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(10, 10, j, p);

        Bateaux ba = new ContreTorpilleur(new Place[]{new Place("A5")}, j, p, true);
        g.setListBateaux(Collections.singletonList(ba));

        Bateaux b = j.bateauAt(new Place("A5"), g);

        assertEquals("Il n'y a pas de bateau ici", ba, b);
    }

    /**
     * Test quand il n'y a pas de bateau
     */
    @Test
    public void bateauAtPasDansGrille() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(10, 10, j, p);

        Bateaux b = j.bateauAt(new Place("Z84897"), g);

        assertNull("Il n'y a pas de bateau ici", b);
    }

    /**
     * Test qui retourne le joueur adverse en cas de 1 joueur, retourne celui mis en param
     */
    @Test
    public void testGetOtherPlayer1Player() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);

        Player l = j.getOtherPlayer(p);

        assertEquals("Le player devrai etre le même", p, l);
    }

    /**
     * Test qui retourne le joueur adverse
     */
    @Test
    public void testGetOtherPlayer2Player() {
        Player       p  = new Player();
        Player       p2 = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        pL.add(p2);
        Jeux j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);

        Player l = j.getOtherPlayer(p);

        assertEquals("Le player ne devrai pas etre le même que celui mis dans la fonction", p2, l);
    }
}