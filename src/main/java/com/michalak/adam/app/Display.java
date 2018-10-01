package com.michalak.adam.app;
import com.michalak.adam.helpers.NoPaperException;
import com.michalak.adam.helpers.Ticket;
import com.michalak.adam.helpers.UserInputProvider;

import java.util.Scanner;
import java.util.ArrayList;

public class Display {
    protected Printer printer;
    private ArrayList<Ticket> ticketsBought;

    public Display() {
        this.printer = new Printer();
        this.ticketsBought = new ArrayList<Ticket>();
    }
    //method that is going to display information for a client throughout the process
    public void screen(Scanner keyboard) {
        try {
            checkForPaper();
        }
        catch (NoPaperException e) {
            System.out.println("Biletomat nie działa.");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        System.out.println("        BILETOMAT KMK Kraków");
        initialScreen(keyboard);
        int decision;
        do {
            decision = UserInputProvider.getInputFromUser(keyboard, "Czy chcesz kupić dodatkowe bilety? (1. tak/2. nie)", 1, 2);
            if (decision == 1)
                initialScreen(keyboard);
        } while(decision == 1);
        screen(keyboard); //
    }
    public void checkForPaper() throws NoPaperException {
        if(!printer.checkPaper()) { //information displayed until technician will refill the paper
            throw new NoPaperException("Brak papieru. Przepraszamy za utrudnienia.");
        }
    }
    private void initialScreen(Scanner keyboard){
        //this is obviously going to be a touch screen in real ticket machine but I will use numbers to make it work on computer
        int decision;
        decision = UserInputProvider.getInputFromUser(keyboard, "1. Bilety czasowe I strefa miasto Kraków\n2. Bilety czasowe I + II aglomeracja", 1, 2);
        if (decision == 1)
            biletyMiasto(keyboard);
        else if (decision == 2)
            biletyAglomeracja(keyboard);
    }
    private void biletyMiasto(Scanner keyboard){
        int decision;
        int quantity;
        System.out.println("1. \n"+Ticket.TWENTYREDUCEDZONEI + "2. \n"+Ticket.TWENTYZONEI);
        System.out.println("3. \n"+Ticket.FOURTYREDUCEDZONEI + "4. \n"+Ticket.FOURTYZONEI);
        System.out.println("5. \n"+Ticket.SIXTYREDUCEDZONEI + "6. \n"+Ticket.SIXTYZONEI);
        System.out.println("7. \n"+Ticket.NINETYREDUCEDZONEI + "8. \n"+Ticket.NINETYZONEI);
        decision = UserInputProvider.getInputFromUser(keyboard, "Wybierz bilet: ", 1, 8);
        quantity = pickQuantity(keyboard);
        for (int i = 1; i <= quantity; i++){
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
        int quantity;
        System.out.println("1. \n"+Ticket.RIDEREDUCEDZONEII + "2. \n"+Ticket.RIDEZONEII);
        System.out.println("3. \n"+Ticket.SIXTYREDUCEDZONEII + "4. \n"+Ticket.SIXTYZONEII);
        System.out.println("5. \n"+Ticket.NINETYREDUCEDZONEII + "6. \n"+Ticket.NINETYZONEII);
        decision = UserInputProvider.getInputFromUser(keyboard, "Wybierz bilet: ", 1, 6);
        quantity = pickQuantity(keyboard);
        for (int i = 1; i <= quantity; i++){
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
