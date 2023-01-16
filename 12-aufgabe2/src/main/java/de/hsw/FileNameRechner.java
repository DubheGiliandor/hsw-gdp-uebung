package de.hsw;

import java.util.Scanner;

public class FileNameRechner {

    public static void main(String[] args) {

    }

    // Nimmt Eingabe entgegen
    public String eingabeString (Scanner input) {
        return input.nextLine();
    }

    public String endeLoeschen(String file) {
        return file.substring(0, file.lastIndexOf("."));
    }

    public boolean endeChecken(String ende) {
        boolean valid = true;
        String ungueltigeSymbole = "/\\:*?\"<>|";
        for (int i = 0; i < ungueltigeSymbole.length(); i++) {
            if (ende.contains("" + ungueltigeSymbole.charAt(i))) {
                valid = false;
            }
        }
        return valid;
    }

    public String endeAnfuegen(String file, String ende) {
        return file.concat(".").concat(ende);
    }
}
