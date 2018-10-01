package com.michalak.adam.app;

import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DisplayTest {
    Display display;
    Scanner keyboard = new Scanner(System.in);

    @Before
    public void setUp(){
        display = new Display();
    }
    @Test
    public void screenNoPaper(){
        display.printer.renewPaperRoll(0);
        display.screen(keyboard); //WARNING: INFINITE LOOP!
    }

}