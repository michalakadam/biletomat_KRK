package com.michalak.adam.app;

import com.michalak.adam.helpers.Coin;

import java.util.ArrayList;

/**
 * When customer is paying for the ticket they may choose to pay in multiple coins.
 * This is the object that is going to store coins thrown by the customer and calculate
 * how much still has to be paid. Those coins are moved to ChangeStorage when amount is sufficient
 * or returned to the customer when there is not enough money provided to buy selected tickets.
 */
public class TemporaryMoneyStorage {
    private ArrayList<Coin> coinsThrown;

    public TemporaryMoneyStorage(){
        this.coinsThrown = new ArrayList<Coin>();
    }

    public void addCoin(Coin coin){
        coinsThrown.add(coin);
        valueOfMoneyThrown();
    }

    public double valueOfMoneyThrown(){
        double sum = 0;
        for (int i = 0; i < coinsThrown.size(); i++)
            sum += coinsThrown.get(i).getValue();
        return sum;
    }
}
