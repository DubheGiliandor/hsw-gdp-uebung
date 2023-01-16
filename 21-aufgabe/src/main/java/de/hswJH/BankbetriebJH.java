package de.hswJH;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
/*
 * Die Klasse "Bankbetrieb" soll einige Funktionen eines Bankbetriebes simulieren.
 * Unter anderem kann sie zwischen Kunden und einem Admin als Benutzer unterscheiden.
 * Je nach Benutzer kann sie Konten erstellen/löschen, auf Konten einzahlen, von ihnen abheben oder umbuchen.
 * Zusätzlich kann ein Admin auch neue Kunden hinzufügen oder alte löschen.
 */
public class BankbetriebJH implements Serializable {
    // Attribute
    private final String bankName;
    private final String bankAdresse;
    private final int bankLeitzahl;
    //
    private final Map<Integer ,KundeJH> kunden;
    // Map: iban als identifier der gespeicherten Konten
    private final Map<String, KontoJH> konten;
    // Scanner: Konsolen-Eingabe
    private transient final Scanner konsole;

    // Konstruktor
    public BankbetriebJH(String name, String adresse, int blz) {
        this.bankName = name;
        this.bankAdresse = adresse;
        this.bankLeitzahl = blz;
        this.konsole = new Scanner(System.in);
        this.konten = new HashMap<>();
        this.kunden = new HashMap<>();
    }



    // Methode, sucht ein Konto anhand der übergebenen Iban und erhöht den Kontostand um den übergebenen Betrag
    public void einzahlenBank(String iban, BigInteger betrag){
        if (betrag.compareTo(new BigInteger("0"))<1){
            System.out.println("Einzahlung nicht möglich");
        }
        else {
            try {
                konten.get(iban).einzahlenKonto(betrag);
            } catch (NullPointerException e) {
                System.out.println("Fehler aufgetreten, Vorgang wird abgebrochen");
            }
        }
    }
    // Methode, sucht ein Konto anhand der übergebenen Iban, kontrolliert, ob genug Geld vorhanden ist und senkt den Kontostand um den übergebenen Betrag
    public void auszahlenBank(String  iban, BigInteger betrag) {
        if (betrag.compareTo(new BigInteger("0"))<1){
            System.out.println("Auszahlung nicht möglich");
        }
        else {
            try {
                if (konten.get(iban) instanceof TagesgeldkontoJH) {
                    System.out.println("Auszahlung bei Tagesgeldkonten nicht möglich");
                }
                else if (konten.get(iban) instanceof GirokontoJH) {
                    if (!(betrag.compareTo(new BigInteger("1000")) < 1)) {
                        System.out.println("Betrag zu hoch, maximale Auszahlung bei Girokonten ist 1000€");
                    } else {
                        konten.get(iban).auszahlenKonto(betrag);
                    }
                } // else für unbekannte Kontenarten machen
            } catch (NullPointerException e) {
                System.out.println("Iban nicht gefunden, Vorgang wird abgebrochen");
            }
        }
    }
    // Methode, die zwei Konten raussucht und die einzahlen/auszahlen-Methoden auf diese anwendet
    public void umbuchen(String ibanZahler, String ibanZiel, BigInteger umbuchen) {
        try {
            // Kontrolle der beiden gegebenen Iban´s
            if (!konten.containsKey(ibanZahler)) {
                System.out.println("Es wurde kein Konto unter der Iban " + ibanZahler + " gefunden, Vorgang wird abgebrochen");
            }
            if (!konten.containsKey(ibanZiel)) {
                System.out.println("Es wurde kein Konto unter der Iban " + ibanZiel + " gefunden, Vorgang wird abgebrochen");
            }
            KontoJH kontoZahler = konten.get(ibanZahler);
            if (kontoZahler.getKontostand().compareTo(umbuchen) < 1) {
                System.out.println("Kontostand des Zahlers zu niedrig, Vorgang wird abgebrochen");
            } else {
                KontoJH kontoZiel = konten.get(ibanZiel);
                kontoZahler.auszahlenKonto(umbuchen);
                kontoZiel.einzahlenKonto(umbuchen);
            }
        } catch (Exception e) {
            System.out.println("Fehler aufgetreten, Vorgang wird abgebrochen");
        }
    }
    // Methode zum Erstellen eines Girokontos und dieses der Map Konten hinzufügt
    public void girokontoErstellen(long kontonummer, KundeJH kunde, Integer id) {
        boolean tooManyAcc = false;
        try {
            if (kunde.getGirokonten().size() > 3) {
                tooManyAcc = true;
            }
        // } catch (Exception e) {
        }finally {
            if (tooManyAcc) {
                System.out.println("Diser Kunde hat bereits drei Girokonten, Vorgang wird abgebrochen");
            } else {
                Ibanrechner ibanschreiber = new Ibanrechner();
                int pruefziffer = ibanschreiber.pruefzifferberechner(bankLeitzahl, kontonummer);
                String iBan = ibanschreiber.ibanschreiber(pruefziffer, bankLeitzahl, kontonummer);
                try {
                    if (!konten.containsKey(iBan)) {
                        // checks if an Object has the given key
                        konten.put(iBan, new GirokontoJH(iBan, kunde));
                        kunden.put(id, kunde);
                    } else {
                        System.out.println("Kontonummer bereits belegt, bitte wählen Sie eine andere");
                        long neueKontoNummer = ibanschreiber.kontonreingabe(konsole);
                        girokontoErstellen(neueKontoNummer, kunde, id);
                }
                } finally {
                    konten.put(iBan, new GirokontoJH(iBan, kunde));
                }
            }
        }
    }
    // Methode zum Erstellen eines Tagesgeldkontos und dieses der Map Konten hinzufügt
    public void tasgesgeldkontoErstellen(long kontonummer, KundeJH kunde, Integer id) {
        boolean tooManyAcc = false;
        try {
            if (kunde.getTagesKonten().size() > 2) {
                tooManyAcc = true;
            }
        // } catch (NullPointerException e) {
        } finally {
            if (tooManyAcc) {
                System.out.println("Diser Kunde hat bereits zwei Tagesgeldkonten, Vorgang wird abgebrochen");
            } else {
                Ibanrechner ibanschreiber = new Ibanrechner();
                int pruefziffer = ibanschreiber.pruefzifferberechner(bankLeitzahl, kontonummer);
                String iBan = ibanschreiber.ibanschreiber(pruefziffer, bankLeitzahl, kontonummer);
                try {
                    if (!konten.containsKey(iBan)) {
                        // checks if an Object has the given key
                        konten.put(iBan, new TagesgeldkontoJH(iBan, kunde));
                        kunden.put(id, kunde);
                    } else {
                        System.out.println("Kontonummer bereits belegt, bitte wählen Sie eine andere");
                        long neueKontoNummer = ibanschreiber.kontonreingabe(konsole);
                        tasgesgeldkontoErstellen(neueKontoNummer, kunde, id);
                    }
                } finally {
                    konten.put(iBan, new TagesgeldkontoJH(iBan, kunde));
                }
            }
        }
    }
    // Methode zum Entfernen eines Kontos aus der Map Konten
    public void kontoLoeschen(String iban) {
        try {
            konten.remove(iban);
        } catch (Exception e) {
            System.out.println("Kein Konto gefunden, Vorgang wird abgebrochen");
        }
    }
    // Methode, die alle Konten, ihre Subklasse, Iban und Kontostand eines übergebenen Kunden in der Konsole ausgibt
    public void kundeAnzeigen(KundeJH kunde) {
        int i = 1;
        String typ = "";
        for (var entry : konten.entrySet()) {
            if (entry.getValue().getBesitzer().equals(kunde)) {
                if (entry.getValue() instanceof GirokontoJH) {
                    typ = "Girokonto";
                } else if (entry.getValue() instanceof TagesgeldkontoJH) {
                    typ = "Tagesgeldkonto";
                }
                System.out.println("Konto " + i + ": " + typ + "\nIban: " + entry.getValue().getIban() + "\nKontostand: " + entry.getValue().getKontostand());
                i++;
            }
        }
    }
    // Methode zum Kontrollieren, ob ein Konto mit der übergebenen Iban in diesem Bankbetrieb vorhanden ist
    public boolean testIban(String iban){
        return konten.containsKey(iban);
    }
    // Methode zum Hinzufügen von Kunden zur List Kunden
    public void kundeHinzufuegen(Integer id, KundeJH kunde){
        try {
            kunden.put(id, kunde);
        } catch (Exception e) {
            System.out.println("ID bereits vergeben.\n Neue ID:");
            Integer neueID = konsole.nextInt();
            kundeHinzufuegen(neueID,kunde);
        }
    }
    // Methode zum Entfernen von Kunden von der List Kunden
    public void kundeLoeschen(Integer id) {
        kunden.remove(id);
        // Konten löschen!
    }



    // Getter für die Map Konten, die List Kunden und dem Integer BLZ
    public Map<String, KontoJH> getKonten() {
        return konten;
    }
    public int getBLZ() {return this.bankLeitzahl;}
    public Map<Integer, KundeJH> getKunden() {
        return kunden;
    }
}
