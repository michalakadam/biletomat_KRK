package com.michalak.adam.app;

import com.michalak.adam.helpers.Coin;

import java.util.ArrayList;
import java.util.Random;

/**
 *  Change storage is an object that stores coins that are available for giving change to the customer.
 *  One of change storage fields is ArrayList of type Integer. Each index represents the amount of coins of a given type.
 *  Amount of coins when change storage object is initialized is picked randomly. Each slot (index of the ArrayList)
 *  has place for hundred coins.
 */

public class ChangeStorage {
    ArrayList<Integer> coins;
    Bank bank;
    public ChangeStorage(){
        bank = new Bank();
        coins = new ArrayList<Integer>(6); //there are six types of coins
        Random randomGenerator = new Random();
        for(int i = 0; i < coins.size(); i++){
            coins.set(i, randomGenerator.nextInt(101));
        }
    }

    public void addCoin(Coin coin){
        if(coin.equals(Coin.FIVE) && checkPlaceForAnotherCoin(0))
            coins.set(0, coins.get(0) + 1);
        else if(coin.equals(Coin.TWO) && checkPlaceForAnotherCoin(1))
            coins.set(1, coins.get(1) + 1);
        else if(coin.equals(Coin.ONE) && checkPlaceForAnotherCoin(2))
            coins.set(2, coins.get(2) + 1);
        else if(coin.equals(Coin.POINTFIFTY) && checkPlaceForAnotherCoin(3))
            coins.set(3, coins.get(3) + 1);
        else if(coin.equals(Coin.POINTTWENTY) && checkPlaceForAnotherCoin(4))
            coins.set(4, coins.get(4) + 1);
        else if(coin.equals(Coin.POINTTEN) && checkPlaceForAnotherCoin(5))
            coins.set(5, coins.get(5) + 1);
        else
            bank.add(coin);
    }
    public boolean checkPlaceForAnotherCoin(int index){
        if(coins.get(index) < 100)
            return true;
        return false;
    }
}
