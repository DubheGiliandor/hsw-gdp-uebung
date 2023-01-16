package de.hswJH;

import java.io.*;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StarterJH implements Serializable {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        new BankbetriebJH("AshmannBanking Inc.", "Am Stockhof 2, 31785 Hameln", 12345678);
        BankbetriebJH ashmannBanking;
        boolean logout = false;
        int option;

        KundeJH userKunde = new KundeJH("Admin", 0);
        BigInteger betrag;
        String iBanEmpfaenger;
        String iBanSender;
        Ibanrechner rechner = new Ibanrechner();


        System.out.println("Wollen Sie den alten Speicherstand laden?\n[1] Ja\n[2] Nein");
        int test = intEingabe(input);
        boolean oldSave = test != 2;
        if (oldSave) {
            try {
                ashmannBanking = load();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Load didnt work");
                // e.getStackTrace();
                ashmannBanking = new BankbetriebJH("AshmannBanking Inc.", "Am Stockhof 2, 31785 Hameln", 12345678);
                e.printStackTrace();
            }
        } else {
            ashmannBanking = new BankbetriebJH("AshmannBanking Inc.", "Am Stockhof 2, 31785 Hameln", 12345678);
        }

        // Dem User wird ein Kunde zugeordnet
        System.out.print("Herzlich Willkommen bei Ashmann Banking Inc.\n User: ");
        input.nextLine();
        String userInput = input.nextLine();
        for (int i = 0; i < ashmannBanking.getKunden().size(); i++) {
            if (ashmannBanking.getKunden().get(i).getName().equals(userInput)) {
                userKunde = ashmannBanking.getKunden().get(i);
            }
        }



        if (userKunde.getName().equals("Admin")){
            do {
                // Benutzer
                System.out.println("Hallo " + userKunde.getName() + "!\n\n Ihre Konten:\n");
                ashmannBanking.kundeAnzeigen(userKunde); // Methode soll Konten anzeigen
                System.out.println("[1] Einzahlung\n[2] Auszahlung\n[3] Umbuchung\n[4] Neues Konto erstellen\n[5] Konto löschen\n[6] Kunde hinzufügen\n[7] Kunde löschen\n[8] Log out");
                option = intEingabe(input);
                switch (option) {
                    case 1 -> {
                        // Einzahlen
                        System.out.println("Auf welches Konto wollen Sie einzahlen?\nIban:");
                        iBanEmpfaenger = stringEingabe(input);
                        if (!ashmannBanking.testIban(iBanEmpfaenger)) {
                            System.out.println("Die Iban ist unbekannt.");
                        } else {
                            System.out.println("Wie viel wollen Sie einzahlen?\nBetrag:");
                            betrag = bigIntEingabe(input);
                            ashmannBanking.einzahlenBank(iBanEmpfaenger, betrag);
                        }
                    }
                    case 2 -> {
                        // Auszahlen
                        System.out.println("Von welchem Konto wollen Sie abheben?\nIban:");
                        iBanEmpfaenger = stringEingabe(input);
                        if (!ashmannBanking.testIban(iBanEmpfaenger)) {
                            System.out.println("Die Iban ist unbekannt.");
                        } else {
                            System.out.println("Wie viel wollen Sie einzahlen?\nBetrag:");
                            betrag = bigIntEingabe(input);
                            ashmannBanking.auszahlenBank(iBanEmpfaenger, betrag);
                        }
                    }
                    case 3 -> {
                        // Umbuchen
                        System.out.println("Welche Konto soll belastet werden?\nIban:");
                        iBanSender = stringEingabe(input);
                        if (!ashmannBanking.testIban(iBanSender)) {
                            System.out.println("Die Iban ist unbekannt.");
                        } else {
                            System.out.println("Auf welches Konto soll überwiesen werden?\nIban:");
                            iBanEmpfaenger = stringEingabe(input);
                            if (!ashmannBanking.testIban(iBanEmpfaenger)) {
                                System.out.println("Die Iban ist unbekannt.");
                            } else {
                                System.out.println("Wie viel wollen Sie überweisen?");
                                betrag = bigIntEingabe(input);
                                ashmannBanking.umbuchen(iBanSender, iBanEmpfaenger, betrag);
                            }
                        }
                    }
                    case 4 -> {
                        // Konto eröffnen
                        System.out.println("Was für ein Konto wollen Sie eröffnen?\nGirokonto oder Tagesgeldkonto:");
                        String typ = stringEingabe(input);
                        if (typ.equals("Girokonto")) {
                            long kontonummer = rechner.kontonreingabe(input);
                            ashmannBanking.girokontoErstellen(kontonummer, userKunde, userKunde.getId());
                        } else if (typ.equals("Tagesgeldkonto")) {
                            long kontonummer = rechner.kontonreingabe(input);
                            ashmannBanking.tasgesgeldkontoErstellen(kontonummer, userKunde, userKunde.getId());
                        } else {
                            System.out.println("Fehlerhafte Eingabe des Kontotypen, der Vorgang wird abgebrochen!");
                        }
                    }
                    case 5 -> {
                        // Konto löschen
                        System.out.println("Welches Konto wollen Sie löschen?\nIban:");
                        String iBanLoeschen = stringEingabe(input);
                        System.out.println("Konto: " + iBanLoeschen + "\nSind Sie sich sicher, dass Sie das Konto löschen wollen?\n Zum Bestätigen, tippen Sie \"LOESCHEN\"");
                        String loeschen = stringEingabe(input);
                        if (loeschen.equals("LOESCHEN")) {
                            ashmannBanking.kontoLoeschen(iBanLoeschen);
                        } else {
                            System.out.println("Fehlerhafte Eingabe, der Vorgang wird abgebrochen!");
                        }
                    }
                    case 6 -> {
                        boolean idFree = true;
                        System.out.println("Wie heißt der neue Kunde?\nName:");
                        String neuerKundeName = stringEingabe(input);
                        System.out.println("Welche ID soll der neue Kunde haben?");
                        int neuerKundeID;
                        do {
                            neuerKundeID = intEingabe(input);
                            for (int i = 0; i < ashmannBanking.getKunden().size(); i++) {
                                if (ashmannBanking.getKunden().get(i).getId() == neuerKundeID) {
                                    System.out.println("ID bereits vergeben, bitte geben Sie eine neue ID ein");
                                    idFree = false;
                                }
                            }
                        } while (!idFree);
                        ashmannBanking.kundeHinzufuegen(neuerKundeID, new KundeJH(neuerKundeName, neuerKundeID));
                    }
                    case 7 -> {
                        boolean idVergeben = false;
                        System.out.println("Welche ID hat der Kunde, den Sie entfernen wollen?\nID:");
                        int verlorenerKundeID;
                        do {
                            verlorenerKundeID = intEingabe(input);
                            for (int i = 0; i < ashmannBanking.getKunden().size(); i++) {
                                if (ashmannBanking.getKunden().get(i).getId() == verlorenerKundeID) {
                                    verlorenerKundeID = ashmannBanking.getKunden().get(i).getId();
                                    idVergeben = true;
                                }
                            }
                            if (!idVergeben) {
                                System.out.println("Kein Kunde mit dieser ID bekannt, bitte wählen Sie eine neue.");
                            }
                        } while (!idVergeben);
                        ashmannBanking.kundeLoeschen(verlorenerKundeID);
                    }
                    case 8 ->
                        // LogOut
                            logout = true;
                    default -> System.out.println("Option nicht erkannt, bitte wählen Sie erneut");
                } // switch case Optionen
                // Benutzer logged out
                // switch case userKunde
            } while (!logout);
        }else{
            do {
                // Benutzer
                System.out.println("Hallo " + userKunde.getName() + "!\n\n Ihre Konten:\n");
                ashmannBanking.kundeAnzeigen(userKunde); // Methode soll Konten anzeigen
                System.out.println("[1] Einzahlung\n[2] Auszahlung\n[3] Umbuchung\n[4] Neues Konto erstellen\n[5] Konto löschen\n[6] Log out");
                option = intEingabe(input);
                switch (option) {
                    case 1 -> {
                        // Einzahlen
                        System.out.println("Auf welches Konto wollen Sie einzahlen?\nIban:");
                        iBanEmpfaenger = stringEingabe(input);
                        if (!ashmannBanking.testIban(iBanEmpfaenger)) {
                            System.out.println("Die Iban ist unbekannt.");
                        } else {
                            System.out.println("Wie viel wollen Sie einzahlen?\nBetrag:");
                            betrag = bigIntEingabe(input);
                            ashmannBanking.einzahlenBank(iBanEmpfaenger, betrag);
                        }
                    }
                    case 2 -> {
                        // Auszahlen
                        System.out.println("Von welchem Konto wollen Sie abheben?\nIban:");
                        iBanEmpfaenger = stringEingabe(input);
                        if (!ashmannBanking.testIban(iBanEmpfaenger)) {
                            System.out.println("Die Iban ist unbekannt.");
                        } else {
                            System.out.println("Wie viel wollen Sie einzahlen?\nBetrag:");
                            betrag = bigIntEingabe(input);
                            ashmannBanking.auszahlenBank(iBanEmpfaenger, betrag);
                        }
                    }
                    case 3 -> {
                        // Umbuchen
                        System.out.println("Welche Konto soll belastet werden?\nIban:");
                        iBanSender = stringEingabe(input);
                        if (!ashmannBanking.testIban(iBanSender)) {
                            System.out.println("Die Iban ist unbekannt.");
                        } else {
                            System.out.println("Auf welches Konto soll überwiesen werden?\nIban:");
                            iBanEmpfaenger = stringEingabe(input);
                            if (!ashmannBanking.testIban(iBanEmpfaenger)) {
                                System.out.println("Die Iban ist unbekannt.");
                            } else {
                                System.out.println("Wie viel wollen Sie überweisen?");
                                betrag = bigIntEingabe(input);
                                ashmannBanking.umbuchen(iBanSender, iBanEmpfaenger, betrag);
                            }
                        }
                    }
                    case 4 -> {
                        // Konto eröffnen
                        System.out.println("Was für ein Konto wollen Sie eröffnen?\nGirokonto oder Tagesgeldkonto:");
                        String typ = stringEingabe(input);
                        if (typ.equals("Girokonto")) {
                            long kontonummer = rechner.kontonreingabe(input);
                            ashmannBanking.girokontoErstellen(kontonummer, userKunde, userKunde.getId());
                        } else if (typ.equals("Tagesgeldkonto")) {
                            long kontonummer = rechner.kontonreingabe(input);
                            ashmannBanking.tasgesgeldkontoErstellen(kontonummer, userKunde, userKunde.getId());
                        } else {
                            System.out.println("Fehlerhafte Eingabe des Kontotypen, der Vorgang wird abgebrochen!");
                        }
                    }
                    case 5 -> {
                        // Konto löschen
                        System.out.println("Welches Konto wollen Sie löschen?\nIban:");
                        String iBanLoeschen = stringEingabe(input);
                        System.out.println("Konto: " + iBanLoeschen + "\nSind Sie sich sicher, dass Sie das Konto löschen wollen?\n Zum Bestätigen, tippen Sie \"LOESCHEN\"");
                        String loeschen = stringEingabe(input);
                        if (loeschen.equals("LOESCHEN")) {
                            ashmannBanking.kontoLoeschen(iBanLoeschen);
                        } else {
                            System.out.println("Fehlerhafte Eingabe, der Vorgang wird abgebrochen!");
                        }
                    }
                    case 6 ->
                        // LogOut
                            logout = true;
                    default -> System.out.println("Option nicht erkannt, bitte wählen Sie erneut");
                } // switch case Optionen
                // Benutzer logged out
                // switch case userKunde
            } while (!logout);
        }



        try {
            save(ashmannBanking);
        } catch (Exception e) {
            //dont know yet
            System.out.println("Save didnt work");
            e.getStackTrace();
            e.printStackTrace();
        }
    }

    private static int intEingabe(Scanner input) {
        int wert = 0;
        try {
            wert = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ungültige Eingabe, bitte versuchen Sie es erneut");
            input.nextLine();
            intEingabe(input);
        }
        return wert;
    }

    private static String stringEingabe(Scanner input) {
        String wert = "";
        input.nextLine();
        try {
            wert = input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Ungültige Eingabe, bitte versuchen Sie es erneut");
            input.nextLine();
            stringEingabe(input);
        }
        return wert;
    }

    private static BigInteger bigIntEingabe(Scanner input) {
        BigInteger wert = new BigInteger("0");
        try {
            wert = input.nextBigInteger();
        } catch (InputMismatchException e) {
            System.out.println("Ungültige Eingabe, bitte versuchen Sie es erneut");
            input.nextLine();
            bigIntEingabe(input);
        }
        return wert;
    }

    public static BankbetriebJH load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("point.dat"));
        // Point muss java.io.Serializable implementieren, damit writeObject/readObject funktioniert
        BankbetriebJH point = (BankbetriebJH) ois.readObject();
        ois.close();
        return point;
    }

    public static void save(BankbetriebJH bank) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("point.dat"));
        // Point muss java.io.Serializable implementieren, damit writeObject/readObject funktioniert
        oos.writeObject(bank);
        oos.close();
    }
}
