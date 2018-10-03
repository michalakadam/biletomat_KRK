package com.michalak.adam.app;

import com.michalak.adam.helpers.NoPaperException;
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
    public void checkPaperIsThere() {
        //when
        printer.renewPaperRoll(1);
        //then
        assertTrue(printer.checkPaper());
    }
    @Test (expected = NoPaperException.class)
    public void checkPaperIsGone(){
        //when
        printer.renewPaperRoll(0);
        //then
        printer.checkPaper();
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