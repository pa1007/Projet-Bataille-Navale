package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exception.GrilleNonCreeException;
import jeux.Grille;
import jeux.Jeux;
import jeux.ModeDeJeux;
import utils.Player;

public class TestPlayer {

	@Test
	public void testPlacerBateau() {
		Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = null;
		boolean test  = false;
		
		try {
			p.placerBateau(j);
			test = false;
		} catch (GrilleNonCreeException e) {
			test = true;
		}
		assertTrue(test == true);
			
	}

	@Test
	public void testPlay() {
		
	}

	@Test
	public void testObstruerCase() {
		Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = null;
		boolean test  = false;
		
		try {
			p.obstruerCase(j);
			test = false;
		} catch (GrilleNonCreeException e) {
			test = true;
		}
		assertTrue(test == true);
			
	}

	@Test
	public void testRandBetween() {
		Player p = new Player();
		boolean un = false;
		boolean deux = false; 
		boolean trois = false;
		for(int i = 0; i<50; i++) {
			int test = p.randBetween(1, 3);
			switch(test) {
				case 1:
					un = true;
					break;
				case 2:
					deux = true; 
					break; 
				case 3:
					trois = true;
					break;
			}
		}
		assertTrue("random mauvais", un && deux && trois);
	}

	@Test
	public void testPlacerBateauRandom() {
		Player       p  = new Player();
        List<Player> pL = new ArrayList<>();
        pL.add(p);
        Jeux   j = new Jeux(ModeDeJeux.MONO_JOUEUR, pL);
        Grille g = null;
		boolean test  = false;
		
		try {
			p.placerBateauRandom(j);
			test = false;
		} catch (GrilleNonCreeException e) {
			test = true;
		}
		
		assertTrue(test == true);
			
	}

	@Test
	public void getRandomPlace() {
	}
}