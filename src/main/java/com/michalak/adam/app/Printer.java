package com.michalak.adam.app;
import com.michalak.adam.helpers.Ticket;

import java.util.Random;

public class Printer {
    private int paperPieces;

    //creates printer with random amount of paper available. Full paper roll contains 5000 pieces of paper.
    public Printer() {
        Random generator = new Random();
        this.paperPieces = generator.nextInt(5001);
    }

    //method used after the ticket was printed to check if the next ticket can be printed
    public boolean checkPaper() {
        if (paperPieces == 0)
            return false;
        return true;
    }

    public int getPaperPieces() {
        return paperPieces;
    }
    //when the paper roll is finished / running out staff can refill it
    public void renewPaperRoll(int paperPieces){
        this.paperPieces =paperPieces;
    }
    public void printTicket(Ticket ticket){
        System.out.print(ticket); //ticket is printed on paper
        paperPieces--; //one ticket printed - one paper piece gone
    }
}