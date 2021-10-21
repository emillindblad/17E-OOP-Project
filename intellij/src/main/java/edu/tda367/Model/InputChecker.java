package edu.tda367.Model;

/**
 * Static methods to check input fields for correct syntax.
 *
 * @author Sebastian Kvaldén
 * @author Emil Lindblad
 */
public class InputChecker {

    static boolean check = true;

    /**
     * Checks if string consists of only numbers
     * @param toCheck String to check
     * @return True: String is only numbers
     *         False: String contains letters or is empty
     */
    public static boolean checkForNumber (String toCheck) {
        //System.out.println(toCheck);
        //for(char ch : toCheck.toCharArray()) {
            ////System.out.println(ch);
            //if (Character.isLetter(ch))
                //return false;
        //}
        check = anyInput(toCheck);
        for(char ch : toCheck.toCharArray()) {
            check = !Character.isLetter(ch);
        }
        return check;
    }

    /**
     * Checks if string consists of only letters
     * @param toCheck String to check
     * @return True: String is only letter
     *         False: String contains numbers or is empty
     */
    public static boolean checkForLetter (String toCheck) {
        //System.out.println(toCheck);
        //for(char ch : toCheck.toCharArray()) {
            ////System.out.println(ch);
            //if (Character.isDigit(ch))
                //return false;
        //}
        check = anyInput(toCheck);
        for(char ch : toCheck.toCharArray()) {
            check = !Character.isDigit(ch);
        }
        return check;
    }

    /**
     * Checks length of string
     * @param toCheck String to check
     * @param length Wanted lenght of string
     * @return True: length is correct
     *         False: length is incorrect
     */
    public static boolean checkForLength (String toCheck, int length) {
        toCheck.replaceAll("\\s+",""); //removes nonVisible characters
        //System.out.println(toCheck.length());
        if (toCheck.length() == length) {
            return true;
        }
        return false;
    }

    /**
     * Checks if input is empty or null
     * @param toCheck String to check
     * @return True: input exists
     *         False: no valid input
     */
    public static boolean anyInput(String toCheck) {
        if (toCheck != null && !toCheck.isBlank()) {
            return true;
        }
        return false;
    }


}
