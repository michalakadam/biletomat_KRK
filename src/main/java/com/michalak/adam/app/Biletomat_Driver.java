package com.michalak.adam.app;
import java.util.Scanner;

public class Biletomat_Driver {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        Display display = new Display();
        display.screen(keyboard);
    }
}
