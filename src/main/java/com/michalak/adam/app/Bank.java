package com.michalak.adam.app;

import com.michalak.adam.helpers.Coin;

import java.util.ArrayList;

/**
 * Bank is an object that stores surplus coins from change storage. When change storage capacity for a given
 * coin is achieved additional coins provided by the customer are moved from temporary money storage to
 * the bank. Coins from bank are taken only by authorised employee. They cannot automatically go to
 * change storage when there is not enough coins there.
 * Bank has one field: ArrayList of type Integer. Each index represents the amount of coins of a given type
 * in the bank.
 */
public class Bank {
    ArrayList<Integer> coinsInBank;

    public Bank(){
        coinsInBank = new ArrayList<Integer>(6); //there are six types of coins
    }
    public void addCoin(Coin coin){
        if(coin.equals(Coin.FIVE))
            coinsInBank.set(0, coinsInBank.get(0) + 1);
        else if(coin.equals(Coin.TWO))
            coinsInBank.set(1, coinsInBank.get(1) + 1);
        else if(coin.equals(Coin.ONE))
            coinsInBank.set(2, coinsInBank.get(2) + 1);
        else if(coin.equals(Coin.POINTFIFTY))
            coinsInBank.set(3, coinsInBank.get(3) + 1);
        else if(coin.equals(Coin.POINTTWENTY))
            coinsInBank.set(4, coinsInBank.get(4) + 1);
        else if(coin.equals(Coin.POINTTEN))
            coinsInBank.set(5, coinsInBank.get(5) + 1);
    }
}
