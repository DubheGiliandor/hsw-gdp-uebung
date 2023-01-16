package de.hsw;

import java.util.Scanner;

public class Starter {

    public static void main(String[] args) {

        // Benötigte Objekte werden erstellt
        Scanner eingabe = new Scanner(System.in);
        IbanRechner rechner = new IbanRechner();

        // Eingabe und Berechnung der Einzelteile der Iban und Zusammführung
        int blz = rechner.blzeingabe(eingabe);
        long kontonr = rechner.kontonreingabe(eingabe);
        int pruefziffer = rechner.pruefzifferberechner(blz, kontonr);
        String iban = rechner.ibanschreiber(pruefziffer, blz, kontonr);

        // Ausgabe aller Daten
        System.out.println("Ländererkennung:\nDE\nPrüfziffern:\n" + pruefziffer + "\nBLZ:\n" + blz + "\nKonto-Nr.:\n" + kontonr + "\nGesamte IBAN: \n" + iban);
    }
}