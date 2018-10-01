package com.michalak.adam.app;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class PrinterTest {
    Printer printer;

    @Before
    public void setUp(){
        printer = new Printer();
    }

    @Test
    public void checkPaper() {
        if (printer.getPaperPieces() == 0)
            System.out.println("Reason for this failure is that printer was randomly generated with 0 paper pieces");
        assertTrue(printer.checkPaper());
        printer.renewPaperRoll(0);
        assertFalse(printer.checkPaper());
    }

    @Test
    public void getPaperPieces() {
        if (printer.getPaperPieces() < 0 || printer.getPaperPieces() > 5000)
            fail("Amount of paper is negative or more than 5000!");
    }

    @Test (expected = IllegalArgumentException.class)
    public void renewLessThanMin() {
        printer.renewPaperRoll(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void renewMoreThanMax() {
        printer.renewPaperRoll(5001);
    }
}