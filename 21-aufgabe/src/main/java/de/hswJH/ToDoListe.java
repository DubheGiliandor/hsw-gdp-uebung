package de.hswJH;

public class ToDoListe {
    public static void main(String[] args) {
        System.out.println("To-Do-Liste");
        var a = 8-3*4;
        System.out.println(a);
    }
    // ToDo
    /*
    -Konsolenausgabe vorbereiten
    (Überlegt euch mögliche Systemprintout´s, benutzt passen Farben, Dekorationen und Formatierungen

    -Unittests überlegen
    (Welche Klassen/Methoden sollten getestet werden -> Liste aufstellen, was sind die Edge-Cases?)
    -> Liste aufgestellt, müssen nur noch geschrieben werden
    Anmerkung: Die Test hier sind etwas umständlich bisweilen,
    da man die Werte für Assert erst via der Methoden ermitteln muss

    -Code fertig schreiben
    Ich habe in KontoJH neue Methoden geschrieben, auszahlenKonto() und einzahlenKonto()
    und in BankbetriebJH habe ich in die Methoden eine zweite Version eingefügt
    Da könnt ihr bei Gelegenheit mal drüberschauen und kommentieren/nachfragen
    Bei meiner Version fallen die Attribute Limit weg und müssen nicht extra überprüft werden
    Ich habe auch bereits probeweise ein Bankbetrieb in der Starterklasse erstellt,
    scheint zu funktionieren
    Hab auch mein korrigierten Ibanrechner importiert, wusste nicht wie das über Import geht,
    also einfach ne neue KLasse gemacht und den Code kopiert
    Und ich habe die Methoden in BankbetriebJH auf Public gestellt sonst kann man sie nicht in der
    Starterklasse nutzen
    !!! Bei der Klasse Kunde muss noch irgendwie ein Limit für die Konten sichergestellt werden
    -> Erledigt,beim Konto erstellen wird nachgeschaut wie viele Konten der Kunde hat
    !!! Leere Maps können weder für containsKey() benutzt werden noch für size(),
    resultiert in NullpointerException
    ==> Try/Catch überall reinschmeißen wo auf die Maps zugegriffen wird
    -> Erledigt

    -Jemand muss das Speicher/Lade-System verstehen
     */
}
