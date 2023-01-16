package de.hsw;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class StarterTest {
    Fakultaetrechner rechner = new Fakultaetrechner();

    // Richtige Eingabe für longFakultaet
    @Test
    public void testlongTrue(){
        int test = 4;
        assertEquals(new BigInteger("24"),rechner.fakultaet(test));
    }

    // Richtige Eingabefür BigFakultaet
    @Test
    public void testBigTrue(){
        int test = 23;
        assertEquals(new BigInteger("25852016738884976640000"),rechner.fakultaet(test));
    }

    // Test des minimalwertes für die long Methode
    @Test
    public void randwertlongMin(){
        int min = 0;
        assertEquals(new BigInteger("1"),rechner.fakultaet(min));
    }

    // Test des maximalwertes für die long Methode
    @Test
    public void randwertlongMax(){
        int max = 19;
        assertEquals(new BigInteger("121645100408832000"), rechner.fakultaet(max));
    }

    // Test des minimalwertes für die Big Methode
    @Test
    public void randwertBigMin(){
        int min = 20;
        assertEquals(new BigInteger("2432902008176640000"), rechner.fakultaet(min));
    }
}
















