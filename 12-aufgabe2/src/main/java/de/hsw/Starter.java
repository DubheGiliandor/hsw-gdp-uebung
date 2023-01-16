package de.hsw;

import java.util.Scanner;

public class Starter {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String file;
        String newEnding;
        FileNameRechner rechner = new FileNameRechner();

        System.out.println("Wie heißt die Datei?");
        file = rechner.eingabeString(input);
        do {
            System.out.println("In welchen Datei-Typ soll sie geändert werden?");
            newEnding = rechner.eingabeString(input);
        } while (!rechner.endeChecken(newEnding));
        file = rechner.endeLoeschen(file);
        file = rechner.endeAnfuegen(file, newEnding);
        System.out.println("Die Datei heißt nun: \n" + file);
    }
}
