package edu.tda367;

import edu.tda367.Model.InputChecker;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputCheckerTest {
    static String text;
    static String num;
    static String empty;

    @BeforeClass
    public static void setup() {
        text = "Foobar";
        num = "123";
        empty = "";
    }

    @Test
    public void numberCheckTest() {
        assertFalse(InputChecker.checkForNumber(text));
        assertTrue(InputChecker.checkForNumber(num));
        assertFalse(InputChecker.checkForNumber(empty));
    }

    @Test
    public void letterCheckTest() {
        assertTrue(InputChecker.checkForLetter(text));
        assertFalse(InputChecker.checkForLetter(num));
        assertFalse(InputChecker.checkForLetter(empty));
    }
}
