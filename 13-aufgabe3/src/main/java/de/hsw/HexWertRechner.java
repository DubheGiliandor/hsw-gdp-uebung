package de.hsw;

import java.util.Scanner;

public class HexWertRechner {


    public void main(String[] args) {
        //
    }

    // Kontrollmethode für den ersten Char des Inputs
    private boolean checkStart (String text){
        char start = text.charAt(0);
        return !Character.isDigit(start);
    }

    // Berechnet den Wert entsprechend der vorgegebenen Logik
    public long wertBerechnen(String text, Scanner eingabe) {
        long wert = 0;
        String konsonanten = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        String vokale = "aeiouAEIOU";
        String zahlen = "0123456789";

        if (checkStart(text)) {
            for (int i = 0; i < text.length(); i++) {
                if (konsonanten.contains("" + text.charAt(i))) {
                    wert = wert + 1;
                } else if (vokale.contains("" + text.charAt(i))) {
                    wert = wert -1;
                } else if (zahlen.contains("" + text.charAt(i))){
                    wert = wert * 2;
                }
            }
        } else {
            System.out.println("Eingabe ist kein valider String, bitte geben Sie einen neuen String ein");
            wert = wertBerechnen(eingabe.next(), eingabe);
        }
        return wert;

    }
}