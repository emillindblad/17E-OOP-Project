package edu.tda367;

public class InputChecker {

    public static boolean checkForNumber (String toCheck) {
        //System.out.println(toCheck);
        for(char ch : toCheck.toCharArray()) {
            //System.out.println(ch);
            if(Character.isLetter(ch))
            return false;
        }
        return true;
    }

    public static boolean checkForLetter (String toCheck) {
        //System.out.println(toCheck);
        for(char ch : toCheck.toCharArray()) {
            //System.out.println(ch);
            if(Character.isDigit(ch))
            return false;
        }
        return true;
    }

    public static boolean checkForLength (String toCheck, int length) {
        toCheck.replaceAll("\\s+",""); //removes nonVisible characters
        //System.out.println(toCheck.length());
        if (toCheck.length() == length) {
            return true;
        }
        return false;
    }
}
