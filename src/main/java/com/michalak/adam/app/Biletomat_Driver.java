package com.michalak.adam.app;
import java.util.Scanner;

public class Biletomat_Driver {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
             Biletomat biletomat = new Biletomat();
              biletomat.screen(keyboard);
    }
}
