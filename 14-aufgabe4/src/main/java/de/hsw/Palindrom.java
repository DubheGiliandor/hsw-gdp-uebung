package de.hsw;

public class Palindrom {

    // Checks if a string is alphanumeric
    public static boolean isAlphaNumeric(String input) {
        return input.matches("^[a-zA-Z]+$");
    }

    // Converts input to a all lower case char array
    public char[] clearString(String input) {
        return input.toLowerCase().toCharArray();
    }


    // Checks if a string is palindromic
    public boolean palindromicCheck(String inputString) {
        char[] input = clearString(inputString);
        try {
            if (input.length % 2 == 0) {
                for (int i = 0; i < input.length / 2; i++) {
                    if (input[i] != input[input.length - i - 1]) {
                        return false;
                    }
                }
            } else {
                for (int i = 0; i < (input.length - 1) / 2; i++) {
                    if (input[i] != input[input.length - i - 1]) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Fehler aufgetreten: " + e);
            return false;
        }
    }
}
