package de.hsw;


import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Lottospiel {

    private Random random;

    public  Lottospiel(long seed){

        random = new Random(seed);
    }
    public Lottospiel(){

        random = new Random();
    }


    //Diese Methode generiert sechs zufällige Lottozahlen und eine Zusatzzahl zwischen 1 und 49
    public Set<Integer> lotto(int anzahl, String name) {

        Set<Integer> zusatzzahl = new TreeSet<>();
        System.out.println("\nZiehung der " + name + "- 1 aus 49");

            while (zusatzzahl.size() < anzahl) {
                int n = random.nextInt(50);
                if (n > 0) {
                    zusatzzahl.add(n);
                }
            }
            return zusatzzahl;
        }

    //Diese Methode generiert eine zufällige Superzahl zwischen 1 und 9
    public  int gibSuperzahl() {
        int superzahl;
        superzahl = random.nextInt(1,9);
            System.out.println("\nZiehung der Superzahl - 1 aus 9");
            System.out.println("["+ superzahl + "]");
        return superzahl;
    }
}
