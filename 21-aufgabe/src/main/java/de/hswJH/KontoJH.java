package de.hswJH;
import java.io.Serializable;
import java.math.BigInteger;
/*
Diese Klasse repräsentiert die Eigenschaften und Methoden unserer Konten
 */
public class KontoJH implements Serializable {
    // Attribute
    private BigInteger kontostand;
    private final String iban;
    private final KundeJH besitzer;
    // Konstruktor
    public KontoJH(String iban, KundeJH benutzer) {
        this.kontostand = new BigInteger("0");
        this.iban = iban;
        this.besitzer = benutzer;
    }

    // Methoden
    public void auszahlenKonto(BigInteger summe) {
        if (summe.compareTo(new BigInteger("0")) > 0) {
            if (summe.compareTo(this.kontostand.add(new BigInteger("1000"))) < 1) {
                if (this.kontostand.compareTo(summe) > -1) {
                    this.kontostand = kontostand.subtract(summe);
                } else {
                    BigInteger unterschied = summe.subtract(this.kontostand);
                    this.kontostand = unterschied.negate();
                }
            } else {
                System.out.println("Auszahlung nicht möglich, das Konto hat ein Limit von -1.000€");
            }
        }
    }
    public void einzahlenKonto(BigInteger summe) {
        if (summe.compareTo(new BigInteger("0")) > 0) {
            this.kontostand = kontostand.add(summe);
        }
    }

    //Getter
    public BigInteger getKontostand(){
        return this.kontostand;
    }
    public String getIban() {
        return iban;
    }
    public KundeJH getBesitzer() {
        return this.besitzer;
    }

    //Setter
    public void setKontostand(BigInteger kontostand){
        this.kontostand = kontostand;
    }
    // Zweiter Konstruktor mit Kontostand statt Setter
}
