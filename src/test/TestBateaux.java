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
import java.util.List;
import static org.junit.Assert.*;

public class TestBateaux {

    @Test
    public void testToucher() throws BateauxStartPointInvalide, BateauxMembreInvalide {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux        j   = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille      g   = new Grille(15, 15, j, p);
        List<Place> att = new ArrayList<>();
        att.add(new Place("A6"));

        Bateaux b = new ContreTorpilleur(j.checkPlace("A6", g, 5, true), j, p, true);

        b.toucher(new Place("A6"));

        assertEquals("La liste de cases touchee n'est pas la meme", att, b.getTouchedPlace());
    }

    @Test
    public void testNbToucher() throws BateauxMembreInvalide, BateauxStartPointInvalide {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        Bateaux b = new ContreTorpilleur(j.checkPlace("A6", g, 5, true), j, p, true);

        b.toucher(new Place("A6"));

        assertEquals("Il y a un tire", 1, b.nbToucher());
    }

    @Test
    public void testGetPurcentDamage() throws BateauxMembreInvalide, BateauxStartPointInvalide {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        Bateaux b = new ContreTorpilleur(j.checkPlace("A6", g, 5, true), j, p, true);

        b.toucher(new Place("A6"));

        assertEquals("Il y a un tire", (double) 1 / 5, b.getPercentDamage(), 0);
    }

    /**
     * Le bateau est mort
     */
    @Test
    public void testDeadVrai() throws BateauxMembreInvalide, BateauxStartPointInvalide {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        Bateaux b = new ContreTorpilleur(j.checkPlace("A6", g, 1, true), j, p, true);

        b.toucher(new Place("A6"));

        assertTrue("le bateau est mort", b.dead());
    }

    /**
     * Le bateau n'est pas mort
     */
    @Test
    public void testDeadFaux() throws BateauxMembreInvalide, BateauxStartPointInvalide {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        Bateaux b = new ContreTorpilleur(j.checkPlace("A6", g, 2, true), j, p, true);

        b.toucher(new Place("A6"));

        assertFalse("le bateau n'est pas mort", b.dead());
    }

    /**
     * La lettre retourner est un B
     *
     * @throws BateauxMembreInvalide      /
     * @throws BateauxStartPointInvalide/
     */
    @Test
    public void testGetLetterNormal() throws BateauxMembreInvalide, BateauxStartPointInvalide {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        Bateaux b = new ContreTorpilleur(j.checkPlace("A6", g, 2, true), j, p, true);

        assertEquals("La lettre n'est pas la bonne", "B", b.getLetter());
    }

    /**
     * La lettre retourner est un B barrée (฿) car le bateau est mort
     *
     * @throws BateauxMembreInvalide      /
     * @throws BateauxStartPointInvalide/
     */
    @Test
    public void testGetLetterMort() throws BateauxMembreInvalide, BateauxStartPointInvalide {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        Bateaux b = new ContreTorpilleur(j.checkPlace("A6", g, 1, true), j, p, true);

        b.toucher(new Place("A6"));

        assertEquals("La lettre n'est pas la bonne", "฿", b.getLetter());
    }

    /**
     * Test la taille
     *
     * @throws BateauxMembreInvalide     /
     * @throws BateauxStartPointInvalide /
     */
    @Test
    public void testTaille() throws BateauxMembreInvalide, BateauxStartPointInvalide {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        Bateaux b = new ContreTorpilleur(j.checkPlace("A6", g, 1, true), j, p, true);

        assertEquals("La taille n'est pas bonne", 1, b.taille());


    }
}