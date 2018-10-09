package com.michalak.adam.helpers;
import java.util.Scanner;
public class UserInputProvider {
    /**
     * This method checks if provided input is of appropriate type (int). If not IllegalArgumentException
     * is thrown and user is prompted to try again.
     * @param keyboard is the scanner used by the user
     * @param message is the message that is used to encourage user to provide the input through keyboard
     * @return input provided by the user in the proper form (int)
     */
    public static int checkIntInputFromUser(Scanner keyboard, String message){
        System.out.println(message);
        try {
            return keyboard.nextInt();
        }
        catch(IllegalArgumentException e) {
            System.out.println("Invalid input. Please try again.");
            keyboard.next();
            return checkIntInputFromUser(keyboard, message);
        }
    }
    /**
     * This method checks if the provided input is in the given range.
     * @param input is int variable provided by the user through Scanner
     * @param min is the minimum value of the given range (included)
     * @param max is the maximum value of the given range (included)
     * @return true if provided input is in the range
     */
    public static boolean inputIsInRange(int input, int min, int max){
        if (input >= min && input <= max)
            return true;
        return false;
    }
    /**
     * This method controls the flow of execution of this helper. If input is in range and of proper type
     * then it is returned.
     * @param keyboard is the scanner used by the user
     * @param message is the message that is used to encourage user to provide the input through keyboard
     * @param min is the minimum value of the given range (included)
     * @param max is the maximum value of the given range (included)
     * @return input provided by the user
     */
    public static int getInputFromUser(Scanner keyboard, String message, int min, int max){
        int input = checkIntInputFromUser(keyboard, message);
        if (inputIsInRange(input, min, max)) {
            return input;
        }
        else {
            System.out.println(String.format("Input out of range [%s, %s]", min, max));
            return getInputFromUser(keyboard, message, min, max);
        }
    }
}
