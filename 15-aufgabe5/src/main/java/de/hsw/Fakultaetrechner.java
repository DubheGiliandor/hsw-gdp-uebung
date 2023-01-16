package de.hsw;

import java.math.BigInteger;

public class Fakultaetrechner {

    // Berechnung der Fakultät für die Zahlen unter 20 mittels Rekursion
    private long longFakultaet(long i){
        if (i == 1){
            return  1;
        }
        else if (i == 0){
            return 1;
        }
        else{
            return i * longFakultaet(i-1);
        }
    }

    // Berechnung der Fakultät für die Zahlen größer gleich 20 mittels for-Schleife
    private BigInteger BigFakultaet(int eingabe) {
        BigInteger ergebnis = new BigInteger("1");
        for (int i = 1; i <= eingabe; i++) {
            ergebnis = ergebnis.multiply(BigInteger.valueOf(i));
        }
        return ergebnis;
    }

    // Unterscheidung der Methoden longFakultaet, BigFakultaet und zu kleiner Angabe
    public BigInteger fakultaet(int eingabe){
        if (eingabe < 0){
            System.out.println("Falsche Eingabe: Zahl zu klein");
            Starter.eingabe();
        }
        else if (eingabe > 0 && eingabe < 20){
            return BigInteger.valueOf(longFakultaet(eingabe));
        }
        else{
            return BigFakultaet(eingabe);
        }
        return null;
    }
}
