package de.hsw;

 import org.junit.jupiter.api.Test;

 import static org.junit.jupiter.api.Assertions.*;


public class IbanRechnerTest {
    IbanRechner test = new IbanRechner();

    @Test
    // Test bei 8-stelliger BLZ und 10-stelliger KontoNr
    public void testVollstaendigeBLZUndKontonr() {
        assertEquals(86, test.pruefzifferberechner(12345678,1234567890));
    }

    @Test
    // Test bei 8-stelliger BLZ und kurzer KontoNr
    public void testVollstaendigeBlz() {
        assertEquals(20, test.pruefzifferberechner(12345678, 123));
    }

    @Test
    // Test eine korrekte BLZ zu erkennen
    public void testBlzValid() {
        assertTrue(test.blzValid(12345678));
    }

    @Test
    // Test eine falsche BLZ zu erkennen
    public void testBlzInvalid() {
        assertFalse(test.blzValid(123));
    }

    @Test
    // Test eine korrekte KontoNr zu erkennen
    public void testKontonrValid() {
        assertTrue(test.kontonrValid(123));
    }

    @Test
    // Test eine inkorrekte KontoNr zu erkennen
    public void testKontonrInvalid() {
        assertFalse(test.kontonrValid(12345678901L));
    }
}
