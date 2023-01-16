package de.hsw;

import java.util.Scanner;

public class Starter {
    // Declaring ANSI_RESET so that we can reset the color
        public static final String ANSI_RESET = "\u001B[0m";

        // Declaring the color
        // Custom declaration
        public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner eingabe = new Scanner(System.in);
        HexWertRechner hexrechner = new HexWertRechner();

        // Inputabfrage
        System.out.println(ANSI_RED + "Bitte geben Sie einen String ein:" + ANSI_RESET);
        String eingabeString = eingabe.next();

        // Berechnung des Wertes
        long wert = hexrechner.wertBerechnen(eingabeString,eingabe);

        // Konvertierung vom long-Wert in einen hexadecimalen Wert
        String hexString = Long.toHexString(wert);

        // Ausgabe
        System.out.println("Das Ergebnis ist:\n 0x" + hexString);
        eingabe.close();
    }
}