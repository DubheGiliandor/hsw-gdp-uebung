package de.hsw;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;
import java.util.TreeSet;

public class StarterTest {

    // In diesem Test wird die Gültigkeit der Superzahl getestet
    @Test
    public void testZufallSuperzahl(){
        int seed = 1;
        Lottospiel lottospiel = new Lottospiel(seed);
        assertEquals(4, lottospiel.gibSuperzahl());
    }
    // In diesem Test wird die Gültigkeit der Lottozahlen getestet
    @Test
    public void testZufallLottozahlen(){
        int seed = 1;
        int anzahl = 6;
        String name = "sechs Lottozahlen";
        Lottospiel lottospiel = new Lottospiel(seed);
        Set<Integer> expected = new TreeSet<>();
        expected.add(4);
        expected.add(13);
        expected.add(34);
        expected.add(35);
        expected.add(38);
        expected.add(47);
        assertEquals(expected,lottospiel.lotto(anzahl,name));
    }
    // In diesem Test wird die Gültigkeit der Zusatzzahl getestet
    @Test
    public void testZufallZufallszahl(){
        int seed = 1;
        int anzahl = 1;
        String name = "einen Zusatzzahl";
        Lottospiel lottospiel= new Lottospiel(seed);
        Set<Integer> expected = new TreeSet<>();
        expected.add(35);
        assertEquals(expected,lottospiel.lotto(anzahl,name));
    }
}
