package de.hswJH;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
Diese Klasse ermöglicht die Validierung und Generierung von Ibans anhand von Kontonummern und BLZ
 */
public class Ibanrechner implements Serializable {
    // Attribute
    private static Scanner eingabe;
    // Main-Methode
    public static void main(String[] args) {
        eingabe = new Scanner(System.in);
    }

    // Eingabe einer validen BLZ
    public  int blzeingabe(Scanner eingabe) {
        System.out.println("Bankleitzahl?");
        int blz;
        try {
            blz = eingabe.nextInt();
        } catch (InputMismatchException e) {
            eingabe.next();
            System.out.println("Eingabe ist kein Integer, bitte erneut eingeben");
            blz = blzeingabe(eingabe);
        }
        if (!blzValid(blz)) {
            blz = blzeingabe(eingabe);
        }
        return blz;
    }

    // Methode kontrolliert, ob die BLZ den offiziellen Richtlinien entspricht
    public boolean blzValid(int blz) {
        boolean valid = Integer.toString(blz).length() == 8;
        if ((blz > 8999_9999)) {
            valid = false;
        }
        return valid;
    }

    // Eingabe einer validen Kontonummer
    public  long kontonreingabe(Scanner eingabe) {
        System.out.println("Bitte geben Sie eine Kontonummer ein\n10Stellige Zahlenfolge:");
        long kontonr;
        try {
            kontonr = eingabe.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Eingabe ist ungültig, bitte erneut eingeben");
            eingabe.nextLine();
            kontonr = kontonreingabe(eingabe);
        }
        if (!kontonrValid(kontonr)) {
            kontonr = kontonreingabe(eingabe);
        }
        return kontonr;
    }

    // Methode kontrolliert die Kontonummer auf richtige Länge
    public boolean kontonrValid(long kontonr) {
        return kontonr <= 9999999999L;
    }

    // Methode berechnet die Prüfungsziffer
    public  int pruefzifferberechner (int blz, long kontonr) {
        if (blz < 1000_0000 || blz > 8999_9999) {
            System.out.println("BLZ  ist nicht zulässig, bitte erneut eingeben");
            pruefzifferberechner(blzeingabe(eingabe), kontonr);
        }
        if (kontonr > 9_999_999_999L) {
            System.out.println("Kontonummer ist nicht zulässig, bitte erneut eingeben");
            pruefzifferberechner(blz, kontonreingabe(eingabe));
        }
        BigInteger blzBigInt = BigInteger.valueOf(blz);
        BigInteger kontonrBigInt = BigInteger.valueOf(kontonr);
        BigInteger pruefziffer = blzBigInt.multiply(new BigInteger("10000000000")).add(kontonrBigInt).multiply(new BigInteger("1000000")).add(new BigInteger("131400")).mod(new BigInteger("97"));
        pruefziffer = new BigInteger("98").subtract(pruefziffer);
        return pruefziffer.intValue();
    }

    // Methode erstellt die vollständige Iban
    public  String ibanschreiber(int pruefziffer, int blz, long kontonr) {
        String blzString = Integer.toString(blz);

        String kontonrString = String.format("%1$010d", kontonr);
        String iban = "DE" + pruefziffer + blzString + kontonrString;
        String formattedIban = "";
        for (int i = 0; i < 5; i++) {
            formattedIban = formattedIban.concat(iban.substring(4*i,(4*i)+4)).concat(" ");
        }
        formattedIban = formattedIban.concat(iban.substring(20));
        return formattedIban;
    }
}