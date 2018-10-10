package com.michalak.adam.app;
import com.michalak.adam.helpers.FloatingPointHandler;
import com.michalak.adam.helpers.Ticket;
import com.michalak.adam.helpers.UserInputProvider;

import java.util.Scanner;
/**
 * Display is a way of communicating with the customer. It shows screens with available tickets, warns about
 * possible limitations (running out of paper, no change) and tells how much customer should pay.
 * It is also a place of initialization of all other objects though I am not sure that it is the best way
 * to do it. Do you have any idea how to make it more elegant? Tell me about it!
 */
public class Display {
    private Printer printer;
    private ShoppingCart shoppingCart;
    private TemporaryMoneyStorage temporaryMoneyStorage;
    private ChangeStorage changeStorage;

    public Display() {
        this.printer = new Printer();
        this.shoppingCart = new ShoppingCart();
        this.temporaryMoneyStorage = new TemporaryMoneyStorage();
        this.changeStorage = new ChangeStorage();
    }

    /**
     * This method displays information for a client throughout the process.
     * It handles the whole transaction from the beginning to the very end.
     * @param keyboard is the scanner used by the user
     */
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
        if(changeStorage.isChangeAvailable(coinsDifference())) {
            while(coinsDifference() > 0) {
                collectMoney(keyboard);
            }
            if(!FloatingPointHandler.isNearZero(coinsDifference())) {
                System.out.println("Twoja reszta:\n" + changeStorage.giveChange(coinsDifference()));
            }
        }
        else {
            System.out.println("ZAPŁATA MOŻLIWA TYLKO ODLICZONĄ KWOTĄ.");
            collectMoney(keyboard);
        }
        System.out.println("Twoje bilety: ");
        for(int i = 0; i < shoppingCart.getTicketsQuantity(); i++)
            printer.printTicket(shoppingCart.getTicketsBought().get(i));
        transactionConclusion();
        flowController(keyboard); //Ticket machine begins another transaction
    }

    /**
     * This method lets customer choose if they want to buy city tickets (I strefa miasto Kraków) or
     * long-range tickets (I + II aglomeracja).
     * @param keyboard is the scanner used by the user
     */
    private void initialScreen(Scanner keyboard) {
        //this is obviously going to be a touch screen in real ticket machine but I will use numbers to make it work on computer
        int decision;
        decision = UserInputProvider.getInputFromUser(keyboard, "1. Bilety czasowe I strefa miasto Kraków\n2. Bilety czasowe I + II aglomeracja", 1, 2);
        if (decision == 1)
            biletyMiasto(keyboard);
        else if (decision == 2)
            biletyAglomeracja(keyboard);
    }

    /**
     * This method shows possible to buy tickets for the city zone, collects decision from the customer
     * and adds ticket(s) to the customer's shopping cart.
     * @param keyboard is the scanner used by the user
     */
    private void biletyMiasto(Scanner keyboard) {
        int decision;
        int quantity;
        System.out.println("1. \n" + Ticket.TWENTYREDUCEDZONEI + "2. \n" + Ticket.TWENTYZONEI);
        System.out.println("3. \n" + Ticket.FOURTYREDUCEDZONEI + "4. \n" + Ticket.FOURTYZONEI);
        System.out.println("5. \n" + Ticket.SIXTYREDUCEDZONEI + "6. \n" + Ticket.SIXTYZONEI);
        System.out.println("7. \n" + Ticket.NINETYREDUCEDZONEI + "8. \n" + Ticket.NINETYZONEI);
        System.out.println("9. Cofnij do ekranu początkowego.");
        System.out.println("10. Zrezygnuj z zakupu biletów.");
        decision = UserInputProvider.getInputFromUser(keyboard, "Wybierz bilet: ", 1, 10);
        if(decision == 9)
            flowController(keyboard);
        else if(decision == 10)
            abandonTransaction(keyboard);
        quantity = pickQuantity(keyboard);
        shoppingCart.addBiletyMiastoToCart(decision, quantity);
    }

    /**
     * This method shows possible to buy long-range tickets for the I+II zone, collects decision from the customer
     * and adds ticket(s) to the customer's shopping cart.
     * @param keyboard is the scanner used by the user
     */
    private void biletyAglomeracja(Scanner keyboard) {
        int decision;
        int quantity;
        System.out.println("1. \n" + Ticket.RIDEREDUCEDZONEII + "2. \n" + Ticket.RIDEZONEII);
        System.out.println("3. \n" + Ticket.SIXTYREDUCEDZONEII + "4. \n" + Ticket.SIXTYZONEII);
        System.out.println("5. \n" + Ticket.NINETYREDUCEDZONEII + "6. \n" + Ticket.NINETYZONEII);
        System.out.println("7. Cofnij do ekranu początkowego.");
        System.out.println("8. Zrezygnuj z zakupu biletów.");
        decision = UserInputProvider.getInputFromUser(keyboard, "Wybierz bilet: ", 1, 8);
        if(decision == 7)
            flowController(keyboard);
        else if(decision == 8)
            abandonTransaction(keyboard);
        quantity = pickQuantity(keyboard);
        shoppingCart.addBiletyAglomeracjaToCart(decision, quantity);
    }

    /**
     * This method allows customer to buy more than one ticket of a given type providing there is enough
     * paper for them.
     * @param keyboard is the scanner used by the user
     * @return chosen quantity of tickets of a given type.
     */
    private int pickQuantity(Scanner keyboard) {
        int quantity;
        quantity = UserInputProvider.getInputFromUser(keyboard, "Wybierz ilość biletów (1-9)", 1, 9);
        //disables choosing more tickets than the machine is able to print
        if (shoppingCart.getTicketsQuantity() + quantity > printer.getPaperPieces()) {
            System.err.println("W tym momencie można wydrukować maksymalnie "+printer.getPaperPieces()+" biletów. Spróbuj mniejszą liczbę");
            pickQuantity(keyboard);
        }
        return quantity;
    }

    /**
     * This methods prepares ticket machine for another transaction when customer decides that they want to
     * not proceed with the transaction. Coins are given back, temporary money storage and shopping cart
     * are cleared.
     * @param keyboard is the scanner used by the user
     */
    private void abandonTransaction(Scanner keyboard){
        giveCoinsBack();
        temporaryMoneyStorage.clearTemporaryStorage();
        shoppingCart.clearShoppingCart();
        flowController(keyboard);
    }

    /**
     * This method returns coins to the customer when the transaction is abandoned.
     */
    private void giveCoinsBack(){
        System.out.println("Twoje zwrócone monety:");
        for(int i = 0; i < temporaryMoneyStorage.getAmountOfCoinsThrown(); i++) {
            //in real life situation real coins are given back to the customer
            System.out.println(temporaryMoneyStorage.getCoinsThrown().get(i));
        }
    }

    /**
     * This method tells the customer how many tickets they've bought so far.
     */
    private void orderSummary(){
        System.out.println("W twoim koszyku "+
                (shoppingCart.getTicketsQuantity() == 1 ? "jest " : "są ")+ shoppingCart.getTicketsQuantity() +
                (shoppingCart.getTicketsQuantity() == 1 ? " bilet" : " bilety")+".");
    }

    /**
     * This method imitates hardware component responsible for collecting coins. Instead of throwing money
     * directly from the wallet, customer tells what type of coin they throw into the ticket machine.
     * This method makes it possible for the customer to abandon transaction.
     * @param keyboard is the scanner used by the user
     */
    private void collectMoney(Scanner keyboard){
        int decision;
        System.out.println("Do zapłaty: "+ FloatingPointHandler.round(coinsDifference(), 2)+"zł");
        //In real life situation customer is just going to throw coins from the wallet
        System.out.println("1. 5zł \t2. 2zł");
        System.out.println("3. 1zł \t4. 50gr");
        System.out.println("5. 20gr\t6. 10gr");
        System.out.println("7. Zrezygnuj z zakupu biletów.");
        decision = UserInputProvider.getInputFromUser(keyboard, "Wrzuć monetę", 1, 7);
        if(decision == 7)
            abandonTransaction(keyboard);
        temporaryMoneyStorage.addCoinToMoneyStorage(decision);
    }

    /**
     * This method says thank you to the customer for using public transportation.
     * It makes sure that coins thrown by the customer are moved from temporary money storage to
     * change storage. It then clears temporary money storage and shopping cart.
     */
    private void transactionConclusion(){
        System.out.println("Dziękujemy za korzystanie z komunikacji miejskiej." +
                "\n\n-----------------------------\n");
        //move coins from temporary money storage to change storage
        for(int i = 0; i < temporaryMoneyStorage.getAmountOfCoinsThrown(); i++) {
            changeStorage.addCoin(temporaryMoneyStorage.getCoinsThrown().get(i));
        }
        temporaryMoneyStorage.clearTemporaryStorage();
        shoppingCart.clearShoppingCart();
    }

    /**
     * This method calculates absolute value of a difference between value of tickets in the shopping cart
     * and value of coins thrown by the customer.
     * @return difference between those two values.
     */
    private double coinsDifference(){
        return (shoppingCart.getTicketsValue() - temporaryMoneyStorage.getValueOfCoinsThrown());
    }
}