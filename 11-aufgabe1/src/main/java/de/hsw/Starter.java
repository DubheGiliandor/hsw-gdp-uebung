package de.hsw;

import java.util.Set;
import java.util.TreeSet;


    //In dieser Klasse werden sechs Lottozahlen, eine Zusatzzahl und eine Superzahl zufällig generiert
public class Starter {
    public static void main(String[] args) /*throws Exception*/ {

        //Treesets ordnen die Zufallszahlen der Größe nach, doppelte Zahlen werden überschrieben
        Set<Integer> zufaelligeZall = new TreeSet<>();
        Lottospiel lottospiel = new Lottospiel();
        zufaelligeZall = lottospiel.lotto( 6, "sechs Lottozahlen");
        System.out.println(zufaelligeZall);
        Set<Integer> mitSuperzahl;

        do {
            mitSuperzahl = lottospiel.lotto( 1, "einen Zusatzzahl");
            mitSuperzahl.removeAll(zufaelligeZall);
        } while (mitSuperzahl.size() == 0);

        System.out.println(mitSuperzahl);
        lottospiel.gibSuperzahl();
    }
}
