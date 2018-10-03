package com.michalak.adam.app;

import com.michalak.adam.helpers.Ticket;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoppingCartTest {
    ShoppingCart shoppingCart;
    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }
    @Test
    public void emptyCartValue(){
        assertEquals(0, shoppingCart.getTicketsValue(), 1e-10);
    }
    @Test
    public void oneTicketCartValue(){
        //when
        shoppingCart.addTicketToCart(Ticket.SIXTYZONEI);
        //then
        assertEquals(5.0, shoppingCart.getTicketsValue(), 1e-10);
    }
    @Test
    public void moreTicketsCartValue(){
        //when
        shoppingCart.addTicketToCart(Ticket.RIDEZONEII);
        shoppingCart.addTicketToCart(Ticket.NINETYZONEII);
        //then
        assertEquals(10.0, shoppingCart.getTicketsValue(), 1e-10);
    }
    @Test
    public void ticketsQuantity(){
        //when
        shoppingCart.addTicketToCart(Ticket.RIDEZONEII);
        shoppingCart.addTicketToCart(Ticket.NINETYZONEII);
        //then
        assertEquals(2, shoppingCart.getTicketsQuantity());
    }
    @Test
    public void clearShoppingCart(){
        //when
        shoppingCart.addTicketToCart(Ticket.SIXTYZONEI);
        shoppingCart.clearShoppingCart();
        //then
        assertEquals(0, shoppingCart.getTicketsValue(), 1e-10);
    }
}