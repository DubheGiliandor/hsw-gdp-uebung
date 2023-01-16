package de.hsw;

import de.hswJH.*;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StarterTest {
    private final BankbetriebJH testBank = new BankbetriebJH("testBank", "Mond", 12345679);
    private final KundeJH testKunde = new KundeJH("Rainer Zufall", 1);
    private final Ibanrechner rechner = new Ibanrechner();

    //Tests Bankbetrieb

    // -> Es wird getestet, ob ein Girokonto erstellt werden kann
    @Test
    public void girokonto1Erstellen(){
        long kontonummer = 1234567891L;
        GirokontoJH testGirokonto = new GirokontoJH("DE95 1234 5679 1234 5678 91", testKunde);
        testBank.girokontoErstellen(kontonummer, testKunde,1);
        String ibantest = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer), testBank.getBLZ(), kontonummer);
        assertEquals(testGirokonto.getIban(), ibantest);
    }

    // -> Es wird getestet, ob ein Tagesgeldkonto erstellt werden kann
    @Test
    public void tagesgeldKonto1Erstellen(){
        long kontonummer = 1234567891L;
        TagesgeldkontoJH testTagesgeldkonto = new TagesgeldkontoJH("DE95 1234 5679 1234 5678 91", testKunde);
        testBank.tasgesgeldkontoErstellen(kontonummer, testKunde, 1);
        String ibanTest = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer), testBank.getBLZ(), kontonummer);
        assertEquals(testTagesgeldkonto.getIban(), ibanTest);
    }

    // → Es wird getestet ob eine Umbuchen von Girokonto zu Girokonto möglich ist
    @Test
    public void umbuchenGiroToGiro(){
        BankbetriebJH bank2 = new BankbetriebJH("Bank für Arme", "Jupiter", 87654321);
        long kontonummer1 = 1234567891L;
        long kontonummer2 = 2345678912L;

        KundeJH kundeZahler = new KundeJH("Max Mustermann", 2);
        KundeJH kundeZiel = new KundeJH("Dennis Schmidt", 3);

        BigInteger betrag = new BigInteger("100");

        bank2.girokontoErstellen(kontonummer1, kundeZahler, 2);
        bank2.girokontoErstellen(kontonummer2, kundeZiel, 3);


        String ibanZahler1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer1), bank2.getBLZ(), kontonummer1);
        String ibanZiel1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer2), bank2.getBLZ(), kontonummer2);

        bank2.getKonten().get(ibanZahler1).setKontostand(new BigInteger("1900"));
        bank2.getKonten().get(ibanZiel1).setKontostand(new BigInteger("2100"));

        testBank.girokontoErstellen(kontonummer1, kundeZahler,2);
        testBank.girokontoErstellen(kontonummer2, kundeZiel,3);

        String ibanZahler2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer1), testBank.getBLZ(), kontonummer1);
        String ibanZiel2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer2), testBank.getBLZ(), kontonummer2);

        testBank.getKonten().get(ibanZahler2).setKontostand(new BigInteger("2000"));
        testBank.getKonten().get(ibanZiel2).setKontostand(new BigInteger("2000"));
        testBank.umbuchen(ibanZahler2, ibanZiel2, betrag);

        assertEquals(bank2.getKonten().get(ibanZahler1).getKontostand(), testBank.getKonten().get(ibanZahler2).getKontostand());
        assertEquals(bank2.getKonten().get(ibanZiel1).getKontostand(), testBank.getKonten().get(ibanZiel2).getKontostand());
    }
    // -> Es wird getestet ob eine Umbuchen von Tagesgeldkonto zu Girokonto möglich ist
    @Test
    public void umbuchenTagesgeldToGiro(){
        BankbetriebJH bank2 = new BankbetriebJH("Bank für Arme", "Jupiter", 87654321);
        long kontonummer1 = 1234567891L;
        long kontonummer2 = 2345678912L;

        KundeJH kundeZahler = new KundeJH("Max Mustermann", 2);
        KundeJH kundeZiel = new KundeJH("Dennis Schmidt", 3);

        BigInteger betrag = new BigInteger("100");

        bank2.tasgesgeldkontoErstellen(kontonummer1, kundeZahler, 2);
        bank2.girokontoErstellen(kontonummer2, kundeZiel, 3);


        String ibanZahler1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer1), bank2.getBLZ(), kontonummer1);
        String ibanZiel1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer2), bank2.getBLZ(), kontonummer2);

        bank2.getKonten().get(ibanZahler1).setKontostand(new BigInteger("1900"));
        bank2.getKonten().get(ibanZiel1).setKontostand(new BigInteger("2100"));

        testBank.tasgesgeldkontoErstellen(kontonummer1, kundeZahler, 2);
        testBank.girokontoErstellen(kontonummer2, kundeZiel, 3);

        String ibanZahler2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer1), testBank.getBLZ(), kontonummer1);
        String ibanZiel2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer2), testBank.getBLZ(), kontonummer2);

        testBank.getKonten().get(ibanZahler2).setKontostand(new BigInteger("2000"));
        testBank.getKonten().get(ibanZiel2).setKontostand(new BigInteger("2000"));
        testBank.umbuchen(ibanZahler2, ibanZiel2, betrag);

        assertEquals(bank2.getKonten().get(ibanZahler1).getKontostand(), testBank.getKonten().get(ibanZahler2).getKontostand());
        assertEquals(bank2.getKonten().get(ibanZiel1).getKontostand(), testBank.getKonten().get(ibanZiel2).getKontostand());
    }
    // -> Es wird getestet ob eine Umbuchen von Girokonto zu Tagesgeldkonto möglich ist
    @Test
    public void umbuchenGiroToTagesgeld(){
        BankbetriebJH bank2 = new BankbetriebJH("Bank für Arme", "Jupiter", 87654321);
        long kontonummer1 = 1234567891L;
        long kontonummer2 = 2345678912L;

        KundeJH kundeZahler = new KundeJH("Max Mustermann", 2);
        KundeJH kundeZiel = new KundeJH("Dennis Schmidt", 3);

        BigInteger betrag = new BigInteger("100");

        bank2.girokontoErstellen(kontonummer1, kundeZahler, 2);
        bank2.tasgesgeldkontoErstellen(kontonummer2, kundeZiel, 3);

        String ibanZahler1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer1), bank2.getBLZ(), kontonummer1);
        String ibanZiel1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer2), bank2.getBLZ(), kontonummer2);

        bank2.getKonten().get(ibanZahler1).setKontostand(new BigInteger("1900"));
        bank2.getKonten().get(ibanZiel1).setKontostand(new BigInteger("2100"));

        testBank.girokontoErstellen(kontonummer1, kundeZahler, 2);
        testBank.tasgesgeldkontoErstellen(kontonummer2, kundeZiel, 3);

        String ibanZahler2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer1), testBank.getBLZ(), kontonummer1);
        String ibanZiel2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer2), testBank.getBLZ(), kontonummer2);

        testBank.getKonten().get(ibanZahler2).setKontostand(new BigInteger("2000"));
        testBank.getKonten().get(ibanZiel2).setKontostand(new BigInteger("2000"));
        testBank.umbuchen(ibanZahler2, ibanZiel2, betrag);

        assertEquals(bank2.getKonten().get(ibanZahler1).getKontostand(), testBank.getKonten().get(ibanZahler2).getKontostand());
        assertEquals(bank2.getKonten().get(ibanZiel1).getKontostand(), testBank.getKonten().get(ibanZiel2).getKontostand());
    }

    // -> Es wird getestet ob eine Umbuchen von Tagesgeldkonto zu Tagesgeldkonto möglich ist
    @Test
    public void umbuchenTagesgeldToTagesgeld(){
        BankbetriebJH bank2 = new BankbetriebJH("Bank für Arme", "Jupiter", 87654321);
        long kontonummer1 = 1234567891L;
        long kontonummer2 = 2345678912L;

        KundeJH kundeZahler = new KundeJH("Max Mustermann", 2);
        KundeJH kundeZiel = new KundeJH("Dennis Schmidt",3);

        BigInteger betrag = new BigInteger("100");

        bank2.tasgesgeldkontoErstellen(kontonummer1, kundeZahler, 2);
        bank2.tasgesgeldkontoErstellen(kontonummer2, kundeZiel, 3);


        String ibanZahler1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer1), bank2.getBLZ(), kontonummer1);
        String ibanZiel1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer2), bank2.getBLZ(), kontonummer2);

        bank2.getKonten().get(ibanZahler1).setKontostand(new BigInteger("1900"));
        bank2.getKonten().get(ibanZiel1).setKontostand(new BigInteger("2100"));

        testBank.tasgesgeldkontoErstellen(kontonummer1, kundeZahler, 2);
        testBank.tasgesgeldkontoErstellen(kontonummer2, kundeZiel, 3);

        String ibanZahler2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer1), testBank.getBLZ(), kontonummer1);
        String ibanZiel2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer2), testBank.getBLZ(), kontonummer2);

        testBank.getKonten().get(ibanZahler2).setKontostand(new BigInteger("2000"));
        testBank.getKonten().get(ibanZiel2).setKontostand(new BigInteger("2000"));
        testBank.umbuchen(ibanZahler2, ibanZiel2, betrag);

        assertEquals(bank2.getKonten().get(ibanZahler1).getKontostand(), testBank.getKonten().get(ibanZahler2).getKontostand());
        assertEquals(bank2.getKonten().get(ibanZiel1).getKontostand(), testBank.getKonten().get(ibanZiel2).getKontostand());
    }

    // -> Auszahlen von 50€
    @Test
    public void auszahlen(){
        long kontonummer = 1234567891L;
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        BigInteger betrag = new BigInteger("50");
        BankbetriebJH bank2 = new BankbetriebJH("Bank für Arme", "Jupiter", 87654321);

        bank2.girokontoErstellen(kontonummer, kunde, 1);
        String iban1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer), bank2.getBLZ(), kontonummer);
        bank2.getKonten().get(iban1).setKontostand(new BigInteger("1950"));

        testBank.girokontoErstellen(kontonummer, kunde, 4);
        String iban2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer), testBank.getBLZ(), kontonummer);
        testBank.getKonten().get(iban2).setKontostand(new BigInteger("2000"));
        testBank.auszahlenBank(iban2, betrag);

        assertEquals(bank2.getKonten().get(iban1).getKontostand(), testBank.getKonten().get(iban2).getKontostand());
    }

    // -> Einzahlen Giro von 50€
    @Test
    public void einzahlenGiro(){
        long kontonummer = 1234567891L;
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        BigInteger betrag = new BigInteger("100");
        BankbetriebJH bank2 = new BankbetriebJH("Bank für Arme", "Jupiter", 87654321);

        bank2.girokontoErstellen(kontonummer, kunde, 1);
        String iban1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer), bank2.getBLZ(), kontonummer);
        bank2.getKonten().get(iban1).setKontostand(new BigInteger("2100"));


        testBank.girokontoErstellen(kontonummer, kunde, 4);
        String iban2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer), testBank.getBLZ(), kontonummer);
        KontoJH test = testBank.getKonten().get(iban2);
        test.setKontostand(new BigInteger("2000"));
        testBank.einzahlenBank(iban2, betrag);

        assertEquals(bank2.getKonten().get(iban1).getKontostand(), testBank.getKonten().get(iban2).getKontostand());
    }

    // -> Einzahlen Tagesgeld von 50€
    @Test
    public void einzahlenTages(){
        long kontonummer = 1234567891L;
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        BigInteger betrag = new BigInteger("100");
        BankbetriebJH bank2 = new BankbetriebJH("Bank für Arme", "Jupiter", 87654321);

        bank2.tasgesgeldkontoErstellen(kontonummer, kunde, 1);
        String iban1 = rechner.ibanschreiber(rechner.pruefzifferberechner(bank2.getBLZ(), kontonummer), bank2.getBLZ(), kontonummer);
        bank2.getKonten().get(iban1).setKontostand(new BigInteger("2100"));


        testBank.tasgesgeldkontoErstellen(kontonummer, kunde, 4);
        String iban2 = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer), testBank.getBLZ(), kontonummer);
        KontoJH test = testBank.getKonten().get(iban2);
        test.setKontostand(new BigInteger("2000"));
        testBank.einzahlenBank(iban2, betrag);

        assertEquals(bank2.getKonten().get(iban1).getKontostand(), testBank.getKonten().get(iban2).getKontostand());
    }

    // -> Loeschen von Konten (über Key und/oder Size der Map testen)
    @Test
    public void kontoLoeschen(){
        long kontonummer = 1234567891L;
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        Map<String, KontoJH> leer = new HashMap<>();
        testBank.girokontoErstellen(kontonummer, kunde, 4);
        String iban = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer), testBank.getBLZ(), kontonummer);
        testBank.kontoLoeschen(iban);

        assertEquals(leer, testBank.getKonten());
    }

    //testIban true
    @Test
    public void ibanTestTrue(){
        long kontonummer = 1234567891L;
        KundeJH kunde = new KundeJH("Günther Jauch", 4);

        testBank.girokontoErstellen(kontonummer, kunde, 4);
        String iban = rechner.ibanschreiber(rechner.pruefzifferberechner(testBank.getBLZ(), kontonummer), testBank.getBLZ(), kontonummer);

        assertTrue(testBank.testIban(iban));
    }

    //testIban false mit  ungültiger Iban
    @Test
    public void ibanTestFalseuIban(){
        assertFalse(testBank.testIban("Alles ist Falsch"));
    }

    //testIban false mit gültiger Iban
    @Test
    public void ibanTestFalsegIban(){
        long kontonummer = 1234567891L;
        KundeJH kunde = new KundeJH("Günther Jauch", 4);

        testBank.girokontoErstellen(kontonummer, kunde, 4);
        //eigentliche Iban: DE95 1234 5679 1234 5678 91

        assertFalse(testBank.testIban("DE95 1234 5679 1234 5678 92"));
    }

    // Testet ob ein Kunde hinzugefügt werden kann
    @Test
    public void kundeHinzufuegen(){
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        Map<Integer, KundeJH> probe = new HashMap<>();
        probe.put(4, kunde);

        testBank.kundeHinzufuegen(kunde.getId(), kunde);

        assertEquals(probe, testBank.getKunden());
    }

    //Test Kunde löschen
    @Test
    public void kundeLoeschen(){
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        Map<Integer, KundeJH> probe = new HashMap<>();

        testBank.kundeHinzufuegen(kunde.getId(), kunde);
        testBank.kundeLoeschen(kunde.getId());

        assertEquals(probe, testBank.getKunden());
    }

    // Tests Konto
    // -> Einzahlen von 50 €
    @Test
    public void einzahlenKonto(){
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        KontoJH konto = new KontoJH("DE95 1234 5679 1234 5678 91", kunde);
        BigInteger betrag = new BigInteger("50");

        konto.einzahlenKonto(betrag);

        assertEquals(new BigInteger("50"), konto.getKontostand());
    }

    // -> Auszahlen von 50€
    @Test
    public void auszahlenKonto(){
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        KontoJH konto = new KontoJH("DE95 1234 5679 1234 5678 91", kunde);
        BigInteger betrag = new BigInteger("50");

        konto.setKontostand(new BigInteger("100"));
        konto.auszahlenKonto(betrag);

        assertEquals(new BigInteger("50"), konto.getKontostand());
    }

    // Tests Girokonto

    // -> Einzahlen von 50€
    @Test
    public void einzahlenGirokonto(){
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        GirokontoJH konto = new GirokontoJH("DE95 1234 5679 1234 5678 91", kunde);
        BigInteger betrag = new BigInteger("50");

        konto.einzahlenKonto(betrag);

        assertEquals(new BigInteger("50"), konto.getKontostand());
    }

    // -> Auszahlen von 50€
    @Test
    public void auszahlenGirokonto(){
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        GirokontoJH konto = new GirokontoJH("DE95 1234 5679 1234 5678 91", kunde);
        BigInteger betrag = new BigInteger("50");

        konto.setKontostand(new BigInteger("100"));
        konto.auszahlenKonto(betrag);

        assertEquals(new BigInteger("50"), konto.getKontostand());
    }

    // Tests TagesgeldKonto

    // -> Einzahlen von 50€
    @Test
    public void einzahlenTagesgeldkonto(){
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        TagesgeldkontoJH konto = new TagesgeldkontoJH("DE95 1234 5679 1234 5678 91", kunde);
        BigInteger betrag = new BigInteger("50");

        konto.einzahlenKonto(betrag);

        assertEquals(new BigInteger("50"), konto.getKontostand());
    }

    // -> Auszahlen von 50€
    @Test
    public void auszahlenTagesgeldkonto(){
        KundeJH kunde = new KundeJH("Günther Jauch", 4);
        TagesgeldkontoJH konto = new TagesgeldkontoJH("DE95 1234 5679 1234 5678 91", kunde);
        BigInteger betrag = new BigInteger("50");

        konto.setKontostand(new BigInteger("100"));
        konto.auszahlenKonto(betrag);

        assertEquals(new BigInteger("50"), konto.getKontostand());
    }
}
