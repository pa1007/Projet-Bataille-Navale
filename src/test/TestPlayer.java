package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utils.Player;

public class TestPlayer {

	@Test
	public void placerBateau() {
	}

	@Test
	public void play() {
	}

	@Test
	public void obstruerCase() {
	}

	@Test
	public void randBetween() {
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
	public void placerBateauRandom() {
	}

	@Test
	public void getRandomPlace() {
	}
}