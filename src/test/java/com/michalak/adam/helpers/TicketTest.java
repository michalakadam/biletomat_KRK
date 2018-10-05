package com.michalak.adam.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class TicketTest {
    private Ticket ticket;
    @Test
    public void create(){
        ticket = Ticket.TWENTYREDUCEDZONEI;
        assertEquals(20, ticket.getValidation());
        assertEquals(1.40, ticket.getPrice(), 1e-10);
        assertEquals(1, ticket.getZone());
    }
    @Test
    public void printTest() {
        ticket = Ticket.RIDEZONEII;
        System.out.print(ticket.toString());
    }
    @Test
    public void anotherPrintTest(){
        ticket = Ticket.FOURTYREDUCEDZONEI;
        System.out.print("\n"+ticket.toString());
    }
    @Test
    public void isReallyNear(){
        ticket = Ticket.TWENTYZONEI;
        assertTrue(ticket.isNear(2.80000000001));
    }
    @Test
    public void isNotEnoughNear(){
        ticket = Ticket.TWENTYZONEI;
        assertFalse(ticket.isNear(2.8000000009));
    }
    @Test
    public void checkNormal(){
        ticket = Ticket.TWENTYZONEI;
        assertEquals("NORMALNY", ticket.checkReduction());
    }
    @Test
    public void checkReduced(){
        ticket = Ticket.TWENTYREDUCEDZONEI;
        assertEquals("ULGOWY", ticket.checkReduction());
    }

}