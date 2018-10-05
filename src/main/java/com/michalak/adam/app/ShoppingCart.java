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
}

