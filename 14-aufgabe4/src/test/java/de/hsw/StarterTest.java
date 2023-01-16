package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StarterTest {

    @Test
    //tests the method palindromicCheck for a positive result
    public void palindromicCheckPositiveTest() {
        Palindrom palindrom = new Palindrom();
        String input = "otto";
        assertTrue(palindrom.palindromicCheck(input));
    }

    @Test
    //tests the method palindromicCheck for a negative result
    public void palindromicCheckNegativeTest() {
        Palindrom palindrom = new Palindrom();
        String input = "auto";
        assertFalse(palindrom.palindromicCheck(input));
    }

    @Test
    //tests the methode palindromicCheck with an uppercase/lowercase input
    public void palindromicCheckUpperLowerCaseTest() {
        Palindrom palindrom = new Palindrom();
        String input = "HaNnAh";
        assertTrue(palindrom.palindromicCheck(input));
    }

    @Test
    //tests the method palindromicCheck with input with an odd number of letters
    public void palindromicCheckOddNumberTest() {
        Palindrom palindrom = new Palindrom();
        String input = "racecar";
        assertTrue(palindrom.palindromicCheck(input));
    }

    @Test
    //tests the method isAlphaNumeric for a positive result
    public void isAlphaNumericPositiveTest() {
        Palindrom palindrom = new Palindrom();
        String input = "racecar";
        boolean inputIsFine = palindrom.isAlphaNumeric(input);
        assertTrue(palindrom.isAlphaNumeric(input));
    }

    @Test
    //tests the method isAlphaNumeric for a negative result
    public void isAlphaNumericNegativeTest() {
        Palindrom palindrom = new Palindrom();
        String input = "Racecar3";
        boolean inputIsFine = palindrom.isAlphaNumeric(input);
        assertFalse(palindrom.isAlphaNumeric(input));
    }

    @Test
    //tests the method clearString for a positive result
    public void clearStringPositiveTest() {
        Palindrom palindrom = new Palindrom();
        String input = "HAnNAh";
        assertArrayEquals(palindrom.clearString(input), new char[]{'h', 'a', 'n', 'n', 'a', 'h'});
    }

    @Test
    //tests the method clearString for a negative result
    public void clearStringNegativeTest() {
        Palindrom palindrom = new Palindrom();
        String input = "HAnNAh";
        assertNotSame(palindrom.clearString(input), new char[]{'r', 't', 't', 'z', 'u', 'f'});
    }
}




