package com.michalak.adam.app;

import com.michalak.adam.helpers.NoPaperException;
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
    @Test (expected = NoPaperException.class)
    public void checkForPaper() throws NoPaperException{
        display.printer.renewPaperRoll(0);
        display.checkForPaper();
    }

}