package edu.tda367;

public class InputChecker {

    public static boolean checkForNumber (String toCheck) {
        //System.out.println(toCheck);
        for(char ch : toCheck.toCharArray()) {
            //System.out.println(ch);
            if(Character.isLetter(ch))
            return false;
        check = !missingInput(toCheck);
        for(char ch : toCheck.toCharArray()) {
            check = !Character.isLetter(ch);
        }
        return check;
    }

    public static boolean checkForLetter (String toCheck) {
        //System.out.println(toCheck);
        for(char ch : toCheck.toCharArray()) {
            //System.out.println(ch);
            if(Character.isDigit(ch))
            return false;
        boolean check = true;
        check = !missingInput(toCheck);
        for(char ch : toCheck.toCharArray()) {
            check = !Character.isDigit(ch);
        }
        return check;
    }

    public static boolean checkForLength (String toCheck, int length) {
        toCheck.replaceAll("\\s+",""); //removes nonVisible characters
        //System.out.println(toCheck.length());
        if (toCheck.length() == length) {
            return true;
        }
        return false;
    }

    private static boolean missingInput(String toCheck) {
        return toCheck.isBlank();
    }
}
