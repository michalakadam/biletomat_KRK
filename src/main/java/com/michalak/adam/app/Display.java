package com.michalak.adam.app;
import com.michalak.adam.helpers.Ticket;
import com.michalak.adam.helpers.UserInputProvider;

import java.util.Scanner;
import java.util.ArrayList;

public class Display {
    private Printer printer;
    private ArrayList<Ticket> ticketsBought;

    public Display() {
        this.printer = new Printer();
        this.ticketsBought = new ArrayList<Ticket>();
    }
    //method that is going to display information for a client throughout the process
    public void screen(Scanner keyboard){
            while(!printer.checkPaper()) //information displayed until technician will refill the paper
                System.out.println("Biletomat nie działa. Przepraszamy za utrudnienia.");
            initialScreen(keyboard);
            int decision;
            do {
                decision = UserInputProvider.getInputFromUser(keyboard, "Czy chcesz kupić dodatkowe bilety? (1. tak/2. nie)", 1, 2);
                if (decision == 1);
                    initialScreen(keyboard);
            } while(decision == 1);
    }
    private void initialScreen(Scanner keyboard){
        //this is obviously going to be a touch screen in real ticket machine but I will use numbers to make it work on computer
        int decision;
        System.out.println("BILETOMAT KMK Kraków");
        decision = UserInputProvider.getInputFromUser(keyboard, "1. Bilety czasowe I strefa miasto Kraków\n2. Bilety czasowe I + II aglomeracja", 1, 2);
        if (decision == 1)
            biletyMiasto(keyboard);
        else if (decision == 2)
            biletyAglomeracja(keyboard);
    }
    private void biletyMiasto(Scanner keyboard){
        int decision;
        System.out.println("1. "+Ticket.TWENTYREDUCEDZONEI + "2. "+Ticket.TWENTYZONEI);
        System.out.println("3. "+Ticket.FOURTYREDUCEDZONEI + "4. "+Ticket.FOURTYZONEI);
        System.out.println("5. "+Ticket.SIXTYREDUCEDZONEI + "6. "+Ticket.SIXTYZONEI);
        System.out.println("7. "+Ticket.NINETYREDUCEDZONEI + "8. "+Ticket.NINETYZONEI);
        decision = UserInputProvider.getInputFromUser(keyboard, "Wybierz bilet: ", 1, 8);
        for (int i = 1; i <= pickQuantity(keyboard); i++){
            if (decision == 1)
                addTicketToShoppingList(Ticket.TWENTYREDUCEDZONEI);
            else if (decision == 2)
                addTicketToShoppingList(Ticket.TWENTYZONEI);
            else if (decision == 3)
                addTicketToShoppingList(Ticket.FOURTYREDUCEDZONEI);
            else if (decision == 4)
                addTicketToShoppingList(Ticket.FOURTYZONEI);
            else if (decision == 5)
                addTicketToShoppingList(Ticket.SIXTYREDUCEDZONEI);
            else if (decision == 6)
                addTicketToShoppingList(Ticket.SIXTYZONEI);
            else if (decision == 7)
                addTicketToShoppingList(Ticket.NINETYREDUCEDZONEI);
            else if (decision == 8)
                addTicketToShoppingList(Ticket.NINETYZONEI);
        }
    }
    private void biletyAglomeracja(Scanner keyboard){
        int decision;
        System.out.println("1. "+Ticket.RIDEREDUCEDZONEII + "2. "+Ticket.RIDEZONEII);
        System.out.println("3. "+Ticket.SIXTYREDUCEDZONEII + "4. "+Ticket.SIXTYZONEII);
        System.out.println("5. "+Ticket.NINETYREDUCEDZONEII + "6. "+Ticket.NINETYZONEII);
        decision = UserInputProvider.getInputFromUser(keyboard, "Wybierz bilet: ", 1, 6);
        for (int i = 1; i <= pickQuantity(keyboard); i++){
            if (decision == 1)
                addTicketToShoppingList(Ticket.RIDEREDUCEDZONEII);
            else if (decision == 2)
                addTicketToShoppingList(Ticket.RIDEZONEII);
            else if (decision == 3)
                addTicketToShoppingList(Ticket.SIXTYREDUCEDZONEII);
            else if (decision == 4)
                addTicketToShoppingList(Ticket.SIXTYZONEII);
            else if (decision == 5)
                addTicketToShoppingList(Ticket.NINETYREDUCEDZONEII);
            else if (decision == 6)
                addTicketToShoppingList(Ticket.NINETYZONEII);
        }
    }
    private int pickQuantity(Scanner keyboard){
        int quantity;
        System.out.println("Wybierz ilość biletów (1-9)");
        quantity = UserInputProvider.getInputFromUser(keyboard, "Wybierz ilość biletów (1-9)", 1, 9);
        //disables choosing more tickets than the machine is able to print
        if (ticketsBought.size() + quantity > printer.getPaperPieces()) {
            System.err.println("Nie można wydrukować tylu biletów. Spróbuj mniejszą liczbę");
            pickQuantity(keyboard);
        }
        return quantity;
    }
    private void addTicketToShoppingList(Ticket ticket){
        ticketsBought.add(ticket);
    }
}
