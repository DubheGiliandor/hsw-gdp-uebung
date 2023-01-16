package de.hswJH;
import java.io.Serializable;
/*
Die Unterklassen dienen nur zur Identifikation der Kontoart via instanceOf und haben daher keine eigenen Attribute oder Methoden
 */
public class GirokontoJH extends KontoJH implements Serializable {
    public GirokontoJH(String iban, KundeJH besitzer) {
        super(iban, besitzer);
    }
}