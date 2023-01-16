package de.hsw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

public class HexWertRechnerTest {
    Scanner eingabe = new Scanner(System.in);

    @Test
    // Test von einer reinen Konsonantenfolge
    public void testNurKonsonanten() {
        String testString = "QWRT";
        HexWertRechner testRechner = new HexWertRechner();
        assertEquals(4,testRechner.wertBerechnen(testString, eingabe));

    }

    @Test
    // Test von einer reinen Vokalenfolge
    public void testNurVokale() {
        String testString = "AEIOU";
        HexWertRechner testRechner = new HexWertRechner();
        assertEquals(-5, testRechner.wertBerechnen(testString, eingabe));
    }

    @Test
    // Test von einem String mit Sonderzeichen
    public void testSonderzeichen() {
        String testString = "a!b c";
        HexWertRechner testRechner = new HexWertRechner();
        assertEquals(1, testRechner.wertBerechnen(testString, eingabe));
    }

    @Test
    // Test von einem String mit Zahlen
    public void testZahlenEnthalten() {
        String testString = " abcd2e";
        HexWertRechner testRechner = new HexWertRechner();
        assertEquals(3, testRechner.wertBerechnen(testString, eingabe));
    }
}