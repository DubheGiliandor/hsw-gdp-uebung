package de.hsw;

import java.util.Scanner;

public class Starter {

    public static void main(String[] args) {
        Palindrom palindrom = new Palindrom();
        boolean inputIsFine = false;
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!inputIsFine) {
            try {
                System.out.println("\n--------------------------------\n");
                System.out.println("Eingabe erfolderlich!");
                System.out.println("Geben Sie ein beliebiges Wort ein!\n");

                input = scanner.nextLine();

                inputIsFine = palindrom.isAlphaNumeric(input);

                if (inputIsFine) {
                    if (palindrom.palindromicCheck(input)) {
                        System.out.println("Es ist EIN Palindrom!");
                    } else {
                        System.out.println("Es ist KEIN Palindrom!");
                    }
                } else {
                    System.out.println("Keine Sonderzeichen!");
                }
            } catch (Exception e) {
                System.out.println("Ein Fehler ist aufgetreten: " + e);
            }
        }
        scanner.close();
    }
}
