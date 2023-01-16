package de.hswJH;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 Diese Klasse repräsentiert Kunden, mit ihrem Namen, einer Liste ihrer Konten und einer ID zur Identifikation bei gleichen Namen
 */
public class KundeJH implements Serializable {
    // Attribute
    private final String name;
    private final int id;
    private final Map<String, TagesgeldkontoJH> tagesKonten;
    private final Map<String, GirokontoJH> girokonten;
    // Konstruktor
    public KundeJH(String name, int id){
        this.id = id;
        this.name = name;
        this.tagesKonten = new HashMap<>();
        this.girokonten = new HashMap<>();
    }

    //Getter
    public String getName() {
        return this.name;
    }
    public int getId() {
        return id;
    }
    public Map<String, GirokontoJH> getGirokonten() {
        return this.girokonten;
    }
    // Map duplizieren mit immutable kopie
    public Map<String, TagesgeldkontoJH> getTagesKonten() {
        return this.tagesKonten;
    }
}

