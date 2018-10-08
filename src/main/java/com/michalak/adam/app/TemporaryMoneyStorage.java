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
    private double valueOfCoinsThrown;

    public TemporaryMoneyStorage(){
        this.coinsThrown = new ArrayList<Coin>();
        valueOfCoinsThrown = 0;
    }

    public void addCoinToMoneyStorage(int decision) {
        if(decision == 1) addCoin(Coin.FIVE);
        else if(decision == 2) addCoin(Coin.TWO);
        else if(decision == 3) addCoin(Coin.ONE);
        else if(decision == 4) addCoin(Coin.POINTFIFTY);
        else if(decision == 5) addCoin(Coin.POINTTWENTY);
        else if(decision == 6) addCoin(Coin.POINTTEN);
    }
    private void addCoin(Coin coin){
        coinsThrown.add(coin);
        valueOfCoinsThrown += coin.getValue();
    }
    public double getValueOfCoinsThrown(){
        return valueOfCoinsThrown;
    }
    public ArrayList<Coin> getCoinsThrown(){
        return coinsThrown;
    }
    public int getAmountOfCoinsThrown(){
        return coinsThrown.size();
    }
    public void clearTemporaryStorage(){
        coinsThrown.clear();
    }
}
