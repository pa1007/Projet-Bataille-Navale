package test;

import jeux.Place;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPlace {

    /**
     * Test de fonctionnement normal
     */
    @Test
    public void testSetColumn() {
        Place p = new Place("A1");

        p.setColumn("B");

        assertEquals("La colone n'est pas la bonne", "B", p.getColumn());
        assertEquals("Le nom n'est pas bien regenerer", "B1", p.getName());
        assertEquals("la ligne ne devrait pas changer", 1, p.getRow());
    }

    /**
     * Test ou la colone mise est 2 lettres
     */
    @Test
    public void testSetColumnFaux() {
        Place p = new Place("A1");

        p.setColumn("BB");

        assertEquals("La colone devrait etre la valeur d'erreur", "A", p.getColumn());
        assertEquals("Le nom devrait etre la valeur d'erreur", "A1", p.getName());
        assertEquals("la ligne devrait etre la valeur d'erreur", 1, p.getRow());
    }


    /**
     * Test de fonctionnement normal
     */
    @Test
    public void testSetRow() {
        Place p = new Place("A1");

        p.setRow(2);

        assertEquals("La colone ne devrait pas avoir changer ", "A", p.getColumn());
        assertEquals("Le nom n'est pas bien regenerer", "A2", p.getName());
        assertEquals("la ligne  devrait avoir changer", 2, p.getRow());
    }

    /**
     * Test de fonctionnement normal
     */
    @Test
    public void testSetName() {
        Place p = new Place("A1");

        p.setName("C5");

        assertEquals("La colone devrait avoir changer ", "C", p.getColumn());
        assertEquals("Le nom n'est pas bien regenerer", "C5", p.getName());
        assertEquals("la ligne  devrait avoir changer", 5, p.getRow());
    }

    /**
     * Test de fonctionnement ou le nom ajouter est faux
     */
    @Test
    public void testSetNameFaux() {
        Place p = new Place("A1");

        p.setName("AC5");

        assertEquals("La colone devrait etre la valeur d'erreur", "A", p.getColumn());
        assertEquals("Le nom devrait etre la valeur d'erreur", "A0", p.getName());
        assertEquals("la ligne devrait etre la valeur d'erreur", 0, p.getRow());
    }

    /**
     * Test dans le cas ou les deux sont egales
     */
    @Test
    public void testIsPlaceFonctionnementEgal() {
        Place p = new Place("A1");
        Place t = new Place("A1");

        boolean res = p.is(t);

        assertTrue("Les deux places sont les meme", res);
    }

    /**
     * Test dans le cas ou les deux ne sont pas egales
     */
    @Test
    public void testIsPlaceFonctionnementPasEgal() {
        Place p = new Place("A1");
        Place t = new Place("B1");

        boolean res = p.is(t);

        assertFalse("Les deux places ne sont pas les meme", res);
    }

    /**
     * Test dans le cas ou le string et la place sont egales
     */
    @Test
    public void testIsStringFonctionnementEgal() {
        Place p = new Place("A1");

        boolean res = p.is("A1");

        assertTrue("Les deux places sont les meme", res);
    }

    /**
     * Test dans le cas ou le string et la place ne sont pas egales
     */
    @Test
    public void testIsStringFonctionnementPasEgal() {
        Place p = new Place("A1");

        boolean res = p.is("B1");

        assertFalse("Les deux places ne sont pas les meme", res);
    }


    /**
     * Test de fonctionnement normal
     */
    @Test
    public void testMoreNormal() {
        Place p = new Place("A1");

        p = p.more(1, 1);

        assertEquals("La colone n'est pas la bonne", "B", p.getColumn());
        assertEquals("Le nom n'est pas bien regenerer", "B2", p.getName());
        assertEquals("la ligne devrait changer", 2, p.getRow());
    }

    /**
     * Test en cas de depassement de limite (Z)
     */
    @Test
    public void testMoreDepassement() {
        Place p = new Place("Z1");

        p = p.more(0, 1);

        assertEquals("La colone devrait etre la valeur d'erreur", "A", p.getColumn());
        assertEquals("Le nom devrait etre la valeur d'erreur", "A0", p.getName());
        assertEquals("la ligne devrait etre la valeur d'erreur", 0, p.getRow());
    }

    /**
     * Test de depassemnt par negation de ligne
     */
    @Test
    public void testMoreDepassementAvantA() {
        Place p = new Place("A1");

        p = p.more(-1, 1);

        assertEquals("La colone devrait etre la valeur d'erreur", "A", p.getColumn());
        assertEquals("Le nom devrait etre la valeur d'erreur", "A0", p.getName());
        assertEquals("la ligne devrait etre la valeur d'erreur", 0, p.getRow());
    }

    /**
     * Test en cas de depassement de limite par move de plus de 26
     */
    @Test
    public void testJumpEnDehors() {
        Place p = new Place("A1");

        p = p.more(500, 500);

        assertEquals("La colone devrait etre la valeur d'erreur", "A", p.getColumn());
        assertEquals("Le nom devrait etre la valeur d'erreur", "A0", p.getName());
        assertEquals("la ligne devrait etre la valeur d'erreur", 0, p.getRow());
    }

    /**
     * Test en cas de depassement de limite par move de plus de 26
     */
    @Test
    public void testMoreValeurNegative() {
        Place p = new Place("B1");

        p = p.more(1, -1);

        assertEquals("La colone n'est pas la bonne", "A", p.getColumn());
        assertEquals("Le nom n'est pas bien regenerer", "A2", p.getName());
        assertEquals("la ligne devrait changer", 2, p.getRow());
    }

    /**
     * Test du constructeur avec le string nom, fonctionnement normal
     */
    @Test
    public void testConstructeurAvecNom() {
        Place p = new Place("B1");

        assertEquals("La colone n'est pas la bonne", "B", p.getColumn());
        assertEquals("Le nom n'est pas bien regenerer", "B1", p.getName());
        assertEquals("la ligne devrait changer", 1, p.getRow());
    }

    /**
     * Test du constructeur avec le string nom, fonctionnement faux car 2 lettre
     */
    @Test
    public void testConstructeurAvecNomFaux() {
        Place p = new Place("BB1");

        assertEquals("La colone devrait etre la valeur d'erreur", "A", p.getColumn());
        assertEquals("Le nom devrait etre la valeur d'erreur", "A0", p.getName());
        assertEquals("la ligne devrait etre la valeur d'erreur", 0, p.getRow());
    }

    /**
     * Test du constructeur avec la colone et la ligne, fonctionnement normal
     */
    @Test
    public void testConstructeurAvecColoneEtLigne() {
        Place p = new Place("B", 1);

        assertEquals("La colone n'est pas la bonne", "B", p.getColumn());
        assertEquals("Le nom n'est pas bien regenerer", "B1", p.getName());
        assertEquals("la ligne devrait changer", 1, p.getRow());
    }

    /**
     * Test du constructeur avec la colone et la ligne, fonctionnement faux car double lettre
     */
    @Test
    public void testConstructeurAvecColoneEtLigneFaux() {
        Place p = new Place("BB", 1);

        assertEquals("La colone devrait etre la valeur d'erreur", "A", p.getColumn());
        assertEquals("Le nom devrait etre la valeur d'erreur", "A0", p.getName());
        assertEquals("la ligne devrait etre la valeur d'erreur", 0, p.getRow());
    }

    /**
     * Test du constructeur avec le nom (x,y), fonctionnement normal
     */
    @Test
    public void testConstructeurAvecNombre() {
        Place p = new Place(2, 1);

        assertEquals("La colone n'est pas la bonne", "B", p.getColumn());
        assertEquals("Le nom n'est pas bien regenerer", "B1", p.getName());
        assertEquals("la ligne devrait changer", 1, p.getRow());
    }

    /**
     * Test du constructeur avec le nom (x,y), fonctionnement avec une valeur superieur au max
     */
    @Test
    public void testConstructeurAvecNombreSupMax() {
        Place p = new Place(30, 1);

        assertEquals("La colone devrait etre la valeur d'erreur", "A", p.getColumn());
        assertEquals("Le nom devrait etre la valeur d'erreur", "A0", p.getName());
        assertEquals("la ligne devrait etre la valeur d'erreur", 0, p.getRow());
    }

    /**
     * Test de fonctionnement normal
     */
    @Test
    public void testGetCollumnNumber() {
        Place p = new Place("A1");

        int clN = p.getColumnNumber();

        assertEquals("Le numero ne correspond pas a la colone", 0, clN);
    }


}