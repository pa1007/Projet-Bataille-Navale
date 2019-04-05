package test;

import org.junit.Test;

import jeux.Place;
import jeux.Tire;

import static org.junit.Assert.*;

public class TestTire {

    @Test
    public void testToStringTouche() {
    	Place p = new Place("A6");
    	Tire t = new Tire(true, p);
    	
    	assertEquals("Mauvais symbole dans la console","┼",t.toString() );
    }
    
    @Test
    public void testToStringNonTouche() {
    	Place p = new Place("A6");
    	Tire t = new Tire(false, p);
    	
    	assertEquals("Mauvais symbole dans la console","Ø",t.toString() );
    }
}