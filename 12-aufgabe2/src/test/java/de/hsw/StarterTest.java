package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StarterTest {

    FileNameRechner rechner = new FileNameRechner();
    // Test, ob das Ende am korrekten Punkt gelöscht wird
    @Test

    public void testEndeLoeschen() {
        String fileName = "test.pdf";
        String erwarteteFileName = "test";
        assertEquals(erwarteteFileName, rechner.endeLoeschen(fileName));
    }

    @Test
    // Test, ob das Ende am korrekten Punkt gelöscht wird
    public void testEndeLoeschenFalsch() {
        String fileName = "test.pdf";
        String erwarteteFileName = "test.";
        assertNotEquals(erwarteteFileName, rechner.endeLoeschen(fileName));
    }


    @Test
    // Test, ob das neue Ende korrekt angefügt wird
    public void testEndeAnfuegen() {
        String fileName = "test";
        String neuesEnde = "pdf";
        String erwarteteFileName = "test.pdf";
        assertEquals(erwarteteFileName, rechner.endeAnfuegen(fileName, neuesEnde));
    }

    @Test
    // Test, ob das neue Ende korrekt angefügt wird
    public void testEndeAnfuegenFalsch() {
        String fileName = "test";
        String neuesEnde = "pdf";
        String erwarteteFileName = "test";
        assertNotEquals(erwarteteFileName, rechner.endeAnfuegen(fileName, neuesEnde));
    }

    @Test
    // Test, ob das Ende korrekt kontrolliert wird
    public void testEndeKorrekt() {
        String ende = "txt";
        assertTrue(rechner.endeChecken(ende));
    }

    @Test
    // Test, ob das Ende korrekt kontrolliert wird
    public void testEndeInkorrekt() {
        String ende = "txt|";
        assertFalse(rechner.endeChecken(ende));
    }
}
