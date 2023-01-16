package de.hsw;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Starter {

    private static Scanner konsole;

    public static void main(String[] args){
        BigInteger ausgabe;
        Fakultaetrechner rechner = new Fakultaetrechner();

        int zahl = eingabe();
        ausgabe = rechner.fakultaet(zahl);
        System.out.println("Ihr Ergebnis lautet:\n" + zahl + "! = "+ ausgabe);

        konsole.close();
    }

    // testen der Eingabe, ob eine Exception geworfen wird
    public static int eingabe(){
        konsole = new Scanner(System.in);
        System.out.println("Berechnung der Fakultät: \n........................");
        System.out.println("Bitte geben Sie eine Zahl ein:");
        try{
             return konsole.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Falsche Eingabe: Nur natürliche Zahlen");
            konsole.nextLine();
            return eingabe();
        }
    }
}

