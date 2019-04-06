package test;

import bateaux.Bateaux;
import bateaux.ContreTorpilleur;
import exception.BateauxMembreInvalide;
import exception.BateauxStartPointInvalide;
import exception.LoadSaveException;
import jeux.Grille;
import jeux.Jeux;
import jeux.ModeDeJeux;
import org.junit.Test;
import utils.Player;
import utils.SavedObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class TestSavedObject {

    /**
     * Test de sauvegarde et de load
     *
     * @throws IOException               /
     * @throws LoadSaveException         /
     * @throws BateauxMembreInvalide     /
     * @throws BateauxStartPointInvalide /
     */
    @Test
    public void testSaveAndLoad()
    throws IOException, LoadSaveException, BateauxMembreInvalide, BateauxStartPointInvalide {
        saveAndLoad("A1");
    }

    /**
     * Test de sauvegarde et de load en ayant changer le path
     */
    @Test
    public void testChangePath()
    throws BateauxMembreInvalide, BateauxStartPointInvalide, IOException, LoadSaveException {
        SavedObject.changePath("Test.dat");
        saveAndLoad("B1");
    }

    /**
     * Test de sauvegarde et de load en ayant changer le path
     */
    @Test
    public void testLoadWithoutSave() {
        SavedObject.changePath("g");
        try {
            SavedObject.load();
            fail("Il devrait y avoir un erreur ici, car pas de sauvegarde ");
        }
        catch (LoadSaveException | IOException e) {

        }
    }


    private void saveAndLoad(String b1)
    throws BateauxStartPointInvalide, BateauxMembreInvalide, IOException, LoadSaveException {
        Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = new Grille(15, 15, j, p);
        p.setGrille(g);
        List<Bateaux> b = new ArrayList<>();
        b.add(new ContreTorpilleur(j.checkPlace(b1, g, 5, true), j, p, true));
        g.setListBateaux(b);
        SavedObject so = new SavedObject(j);

        so.save();
        SavedObject s  = SavedObject.load();
        Grille      gr = s.getJeux().getListPlayer().get(0).getGrille();

        assertArrayEquals(
                "Les deux grilles doivent avoir les meme bateaux",
                g.getListBateaux().toArray(new Bateaux[0]),
                gr.getListBateaux().toArray(new Bateaux[0])
        );
        assertEquals("La grille n'as pas le meme verticale", g.getVerticale(), gr.getVerticale());
        assertEquals("La grille n'as pas le meme horrizontale", g.getHorizontal(), gr.getHorizontal());
    }
}