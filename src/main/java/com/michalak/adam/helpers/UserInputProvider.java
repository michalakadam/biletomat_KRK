package com.michalak.adam.helpers;
import java.util.Scanner;
public class UserInputProvider {
    public static int checkIntInputFromUser(Scanner keyboard, String message){
        System.out.println(message);
        try {
            return keyboard.nextInt();
        }
        catch(Exception e) {
            System.out.println("Invalid input. Please try again.");
            keyboard.next();
            return checkIntInputFromUser(keyboard, message);
        }
    }
    public static boolean inputIsInRange(int input, int min, int max){
        if (input >= min && input <= max)
            return true;
        return false;
    }
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
}
