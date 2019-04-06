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

    /**
     * Test inBound fonctionnel
     */
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

    /**
     * Test inBound quand le X est trop grand
     */
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

    /**
     * Test inBound quand le Y est trop grand
     */
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

    /**
     * Test de l'affichage des bateaux vide
     */
    @Test
    public void testConsolBateauFormatSansBateau() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        String s = g.consolBateauFormat();

        assertEquals("Les strings ne sont pas pareil, il y a une erreur dans l'affichage",
                     "   a b c d e f g h i j k l m n o \n"
                     + "01 O O O O O O O O O O O O O O O \n"
                     + "02 O O O O O O O O O O O O O O O \n"
                     + "03 O O O O O O O O O O O O O O O \n"
                     + "04 O O O O O O O O O O O O O O O \n"
                     + "05 O O O O O O O O O O O O O O O \n"
                     + "06 O O O O O O O O O O O O O O O \n"
                     + "07 O O O O O O O O O O O O O O O \n"
                     + "08 O O O O O O O O O O O O O O O \n"
                     + "09 O O O O O O O O O O O O O O O \n"
                     + "10 O O O O O O O O O O O O O O O \n"
                     + "11 O O O O O O O O O O O O O O O \n"
                     + "12 O O O O O O O O O O O O O O O \n"
                     + "13 O O O O O O O O O O O O O O O \n"
                     + "14 O O O O O O O O O O O O O O O \n"
                     + "15 O O O O O O O O O O O O O O O \n", s
        );
    }

    /**
     * Test de l'affichage des bateaux avec des bateaux
     */
    @Test
    public void testConsolBateauFormatAvecBateauEtIle() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);
        j.addPlaceObstue(new Place("A6"));
        Bateaux       b  = new ContreTorpilleur(new Place[]{new Place("C5")}, j, p, true);
        List<Bateaux> bl = new ArrayList<>();
        Bateaux       b2 = new ContreTorpilleur(new Place[]{new Place("I5")}, j, p, true);
        b2.toucher(new Place("I5"));
        bl.add(b);
        bl.add(b2);
        g.setListBateaux(bl);

        String s = g.consolBateauFormat();

        assertEquals("Les strings ne sont pas pareil, il y a une erreur dans l'affichage",
                     "   a b c d e f g h i j k l m n o \n"
                     + "01 O O O O O O O O O O O O O O O \n"
                     + "02 O O O O O O O O O O O O O O O \n"
                     + "03 O O O O O O O O O O O O O O O \n"
                     + "04 O O O O O O O O O O O O O O O \n"
                     + "05 O O B O O O O O ฿ O O O O O O \n"
                     + "06 p O O O O O O O O O O O O O O \n"
                     + "07 O O O O O O O O O O O O O O O \n"
                     + "08 O O O O O O O O O O O O O O O \n"
                     + "09 O O O O O O O O O O O O O O O \n"
                     + "10 O O O O O O O O O O O O O O O \n"
                     + "11 O O O O O O O O O O O O O O O \n"
                     + "12 O O O O O O O O O O O O O O O \n"
                     + "13 O O O O O O O O O O O O O O O \n"
                     + "14 O O O O O O O O O O O O O O O \n"
                     + "15 O O O O O O O O O O O O O O O \n", s
        );
    }

    /**
     * Test de l'affichage de tire vide
     */
    @Test
    public void testConsolTireFormatSansBateau() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);

        String s = g.consolTireFormat();

        assertEquals("Les strings ne sont pas pareil, il y a une erreur dans l'affichage",
                     "   a b c d e f g h i j k l m n o \n"
                     + "01 O O O O O O O O O O O O O O O \n"
                     + "02 O O O O O O O O O O O O O O O \n"
                     + "03 O O O O O O O O O O O O O O O \n"
                     + "04 O O O O O O O O O O O O O O O \n"
                     + "05 O O O O O O O O O O O O O O O \n"
                     + "06 O O O O O O O O O O O O O O O \n"
                     + "07 O O O O O O O O O O O O O O O \n"
                     + "08 O O O O O O O O O O O O O O O \n"
                     + "09 O O O O O O O O O O O O O O O \n"
                     + "10 O O O O O O O O O O O O O O O \n"
                     + "11 O O O O O O O O O O O O O O O \n"
                     + "12 O O O O O O O O O O O O O O O \n"
                     + "13 O O O O O O O O O O O O O O O \n"
                     + "14 O O O O O O O O O O O O O O O \n"
                     + "15 O O O O O O O O O O O O O O O \n", s
        );
    }

    /**
     * Test de l'affichage de tire avec des bateaux
     */
    @Test
    public void testConsolTireFormatAvecBateau() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);
        j.addPlaceObstue(new Place("A6"));
        g.addNewTire(new Tire(false, new Place("A1")));
        g.addNewTire(new Tire(true, new Place("B1")));

        String s = g.consolTireFormat();

        assertEquals("Les strings ne sont pas pareil, il y a une erreur dans l'affichage",
                     "   a b c d e f g h i j k l m n o \n"
                     + "01 Ø ┼ O O O O O O O O O O O O O \n"
                     + "02 O O O O O O O O O O O O O O O \n"
                     + "03 O O O O O O O O O O O O O O O \n"
                     + "04 O O O O O O O O O O O O O O O \n"
                     + "05 O O O O O O O O O O O O O O O \n"
                     + "06 p O O O O O O O O O O O O O O \n"
                     + "07 O O O O O O O O O O O O O O O \n"
                     + "08 O O O O O O O O O O O O O O O \n"
                     + "09 O O O O O O O O O O O O O O O \n"
                     + "10 O O O O O O O O O O O O O O O \n"
                     + "11 O O O O O O O O O O O O O O O \n"
                     + "12 O O O O O O O O O O O O O O O \n"
                     + "13 O O O O O O O O O O O O O O O \n"
                     + "14 O O O O O O O O O O O O O O O \n"
                     + "15 O O O O O O O O O O O O O O O \n", s
        );
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

    /**
     * On test le constructeur
     */
    @Test
    public void testConstructeur() {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);

        Grille g = new Grille(12, 23, j, p);

        assertEquals("Le verticale n'est pas bon", 12, g.getVerticale());
        assertEquals("L'horisontale n'est pas bon", 23, g.getHorizontal());
        assertEquals("Le joueur n'est pas le bon", p, g.getPlayer());
    }
}