package com.michalak.adam.app;
import com.michalak.adam.helpers.Coin;
import com.michalak.adam.helpers.Ticket;
import com.michalak.adam.helpers.UserInputProvider;

import java.util.Scanner;


/**
 * Display is a way of communicating with the customer. It shows screens with available tickets, warns about
 * possible limitations (running out of paper, no change) and tells how much customer should pay.
 */

public class Display {
    protected Printer printer;
    private ShoppingCart shoppingCart;
    private TemporaryMoneyStorage temporaryMoneyStorage;

    public Display() {
        this.printer = new Printer();
        this.shoppingCart = new ShoppingCart();
        this.temporaryMoneyStorage = new TemporaryMoneyStorage();
    }

    //method that is going to display information for a client throughout the process
    protected void flowController(Scanner keyboard) {
        //first let's check if tickets can be printed on paper
        printer.checkPaper();
        System.out.println("        BILETOMAT KMK Kraków");
        initialScreen(keyboard);
        int decision;
        do {
            orderSummary();
            decision = UserInputProvider.getInputFromUser(keyboard, "Czy chcesz kupić dodatkowe bilety? (1. tak/2. nie)", 1, 2);
            if (decision == 1)
                initialScreen(keyboard);
        } while (decision == 1);
        orderSummary();
        collectMoney(keyboard);
        transactionConclusion();
        flowController(keyboard); //
    }

    private void initialScreen(Scanner keyboard) {
        //this is obviously going to be a touch screen in real ticket machine but I will use numbers to make it work on computer
        int decision;
        decision = UserInputProvider.getInputFromUser(keyboard, "1. Bilety czasowe I strefa miasto Kraków\n2. Bilety czasowe I + II aglomeracja", 1, 2);
        if (decision == 1)
            biletyMiasto(keyboard);
        else if (decision == 2)
            biletyAglomeracja(keyboard);
    }

    private void biletyMiasto(Scanner keyboard) {
        int decision;
        int quantity;
        System.out.println("1. \n" + Ticket.TWENTYREDUCEDZONEI + "2. \n" + Ticket.TWENTYZONEI);
        System.out.println("3. \n" + Ticket.FOURTYREDUCEDZONEI + "4. \n" + Ticket.FOURTYZONEI);
        System.out.println("5. \n" + Ticket.SIXTYREDUCEDZONEI + "6. \n" + Ticket.SIXTYZONEI);
        System.out.println("7. \n" + Ticket.NINETYREDUCEDZONEI + "8. \n" + Ticket.NINETYZONEI);
        decision = UserInputProvider.getInputFromUser(keyboard, "Wybierz bilet: ", 1, 8);
        quantity = pickQuantity(keyboard);
        for (int i = 1; i <= quantity; i++) {
            if (decision == 1) shoppingCart.addTicketToCart(Ticket.TWENTYREDUCEDZONEI);
            else if (decision == 2) shoppingCart.addTicketToCart(Ticket.TWENTYZONEI);
            else if (decision == 3) shoppingCart.addTicketToCart(Ticket.FOURTYREDUCEDZONEI);
            else if (decision == 4) shoppingCart.addTicketToCart(Ticket.FOURTYZONEI);
            else if (decision == 5) shoppingCart.addTicketToCart(Ticket.SIXTYREDUCEDZONEI);
            else if (decision == 6) shoppingCart.addTicketToCart(Ticket.SIXTYZONEI);
            else if (decision == 7) shoppingCart.addTicketToCart(Ticket.NINETYREDUCEDZONEI);
            else if (decision == 8) shoppingCart.addTicketToCart(Ticket.NINETYZONEI);
        }
    }

    private void biletyAglomeracja(Scanner keyboard) {
        int decision;
        int quantity;
        System.out.println("1. \n" + Ticket.RIDEREDUCEDZONEII + "2. \n" + Ticket.RIDEZONEII);
        System.out.println("3. \n" + Ticket.SIXTYREDUCEDZONEII + "4. \n" + Ticket.SIXTYZONEII);
        System.out.println("5. \n" + Ticket.NINETYREDUCEDZONEII + "6. \n" + Ticket.NINETYZONEII);
        decision = UserInputProvider.getInputFromUser(keyboard, "Wybierz bilet: ", 1, 6);
        quantity = pickQuantity(keyboard);
        for (int i = 1; i <= quantity; i++) {
            if (decision == 1) shoppingCart.addTicketToCart(Ticket.RIDEREDUCEDZONEII);
            else if (decision == 2) shoppingCart.addTicketToCart(Ticket.RIDEZONEII);
            else if (decision == 3) shoppingCart.addTicketToCart(Ticket.SIXTYREDUCEDZONEII);
            else if (decision == 4) shoppingCart.addTicketToCart(Ticket.SIXTYZONEII);
            else if (decision == 5) shoppingCart.addTicketToCart(Ticket.NINETYREDUCEDZONEII);
            else if (decision == 6) shoppingCart.addTicketToCart(Ticket.NINETYZONEII);
        }
    }

    private int pickQuantity(Scanner keyboard) {
        int quantity;
        quantity = UserInputProvider.getInputFromUser(keyboard, "Wybierz ilość biletów (1-9)", 1, 9);
        //disables choosing more tickets than the machine is able to print
        if (shoppingCart.getTicketsQuantity() + quantity > printer.getPaperPieces()) {
            System.err.println("Nie można wydrukować tylu biletów. Spróbuj mniejszą liczbę");
            pickQuantity(keyboard);
        }
        return quantity;
    }

    private void orderSummary(){
        System.out.println("W twoim koszyku "+
                (shoppingCart.getTicketsQuantity()%2 == 1 ? "jest " : "są ")+ shoppingCart.getTicketsQuantity() +
                (shoppingCart.getTicketsQuantity()%2 == 1 ? "bilet" : "bilety")+".");
    }

    private void collectMoney(Scanner keyboard){
        int decision;
        System.out.println("Do zapłaty: "+shoppingCart.getTicketsValue()+"zł");
        //In real life situation customer is just going to throw coins from the wallet
        System.out.println("1. 5zł \t2. 2zł");
        System.out.println("3. 1zł \t4. 50gr");
        System.out.println("5. 20gr\t6. 10gr");
        decision = UserInputProvider.getInputFromUser(keyboard, "Wrzuć monetę", 1, 6);
        if(decision == 1) temporaryMoneyStorage.addCoin(Coin.FIVE);
        else if(decision == 2) temporaryMoneyStorage.addCoin(Coin.TWO);
        else if(decision == 3) temporaryMoneyStorage.addCoin(Coin.ONE);
        else if(decision == 4) temporaryMoneyStorage.addCoin(Coin.POINTFIFTY);
        else if(decision == 5) temporaryMoneyStorage.addCoin(Coin.POINTTWENTY);
        else if(decision == 6) temporaryMoneyStorage.addCoin(Coin.POINTTEN);
    }

    private void transactionConclusion(){
        System.out.println("Dziękujemy za skorzystanie z komunikacji miejskiej.");
        shoppingCart.clearShoppingCart();
    }
}