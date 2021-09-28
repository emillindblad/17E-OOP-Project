package edu.tda367;

public class InputChecker {

    public static boolean checkForNumber (String toCheck) {
        for(char ch : toCheck.toCharArray()) {
            if(Character.isLetter(ch));
            return false;
        }
        return true;
    }

    public static boolean checkForLetter (String toCheck) {
        for(char ch : toCheck.toCharArray()) {
            if(Character.isDigit(ch));
            return false;
        }
        return true;
    }

    public static boolean checkForLength (String toCheck, int length) {
        toCheck.replaceAll("\\s+",""); //removes nonVisible characters
        if (toCheck.length() == length) {
            return true;
        }
        return false;
    }
}
