package com.michalak.adam.app;

import com.michalak.adam.helpers.NoPaperException;
import com.michalak.adam.helpers.Ticket;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class DisplayTest {
    Display display;
    Scanner keyboard = new Scanner(System.in);

    @Before
    public void setUp(){
        //given
        display = new Display();
    }
    @Test (expected = NoPaperException.class)
    public void checkForPaper() throws NoPaperException{
        //when
        display.printer.renewPaperRoll(0);
        //then
        display.checkForPaper();
    }
    @Test
    public void emptyListValue(){
        assertEquals(0, display.ticketShoppingListValue(), 1e-10);
    }
    @Test
    public void shoppingListValue(){
        //when
        display.ticketsBought.add(Ticket.SIXTYZONEI);
        //then
        assertEquals(5.0, display.ticketShoppingListValue(), 1e-10);
    }

}