package com.michalak.adam.app;

import com.michalak.adam.helpers.Ticket;
/**
 * ShoppingCart object is going to store tickets bought by the customer and calculate the total price of all tickets.
 */
import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Ticket> ticketsBought;
    private double ticketsValue;

    public ShoppingCart() {
        ticketsBought = new ArrayList<Ticket>();
        ticketsValue = 0;
    }
   public void addTicketToCart(Ticket ticket){
        ticketsBought.add(ticket);
        //increase cart value at the same time
        addTicketValue(ticket);
   }
   private void addTicketValue(Ticket ticket){
        ticketsValue += ticket.getPrice();
   }
   public double getTicketsValue() {
       return ticketsValue;
   }
   public int getTicketsQuantity() {
        return ticketsBought.size();
   }
   public void clearShoppingCart(){
        ticketsBought.clear();
        ticketsValue = 0;
   }
   public ArrayList<Ticket> getTicketsBought(){
        return ticketsBought;
   }
   public void addBiletyMiastoToCart(int decision, int quantity){
       for (int i = 1; i <= quantity; i++) {
           if (decision == 1) addTicketToCart(Ticket.TWENTYREDUCEDZONEI);
           else if (decision == 2) addTicketToCart(Ticket.TWENTYZONEI);
           else if (decision == 3) addTicketToCart(Ticket.FOURTYREDUCEDZONEI);
           else if (decision == 4) addTicketToCart(Ticket.FOURTYZONEI);
           else if (decision == 5) addTicketToCart(Ticket.SIXTYREDUCEDZONEI);
           else if (decision == 6) addTicketToCart(Ticket.SIXTYZONEI);
           else if (decision == 7) addTicketToCart(Ticket.NINETYREDUCEDZONEI);
           else if (decision == 8) addTicketToCart(Ticket.NINETYZONEI);
       }
   }
   public void addBiletyAglomeracjaToCart(int decision, int quantity){
       for (int i = 1; i <= quantity; i++) {
           if (decision == 1) addTicketToCart(Ticket.RIDEREDUCEDZONEII);
           else if (decision == 2) addTicketToCart(Ticket.RIDEZONEII);
           else if (decision == 3) addTicketToCart(Ticket.SIXTYREDUCEDZONEII);
           else if (decision == 4) addTicketToCart(Ticket.SIXTYZONEII);
           else if (decision == 5) addTicketToCart(Ticket.NINETYREDUCEDZONEII);
           else if (decision == 6) addTicketToCart(Ticket.NINETYZONEII);
       }
   }
}

