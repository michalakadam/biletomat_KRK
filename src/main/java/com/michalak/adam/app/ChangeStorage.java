package com.michalak.adam.app;

import com.michalak.adam.helpers.Coin;
import com.michalak.adam.helpers.FloatingPointHandler;

import java.util.ArrayList;
import java.util.Random;

/**
 *  Change storage is an object that stores coins that are available for giving change to the customer.
 *  One of change storage fields is ArrayList of type Integer. Each index represents the amount of coins of a given type.
 *  Amount of coins when change storage object is initialized is picked randomly. Each slot (index of the ArrayList)
 *  has place for hundred coins.
 */

public class ChangeStorage {
    private ArrayList<Integer> coins;
    private Bank bank;
    public ChangeStorage(){
        bank = new Bank();
        coins = new ArrayList<Integer>();
        //when ticket machine is initialized coins are randomly distributed
        Random randomGenerator = new Random();
        for(int i = 0; i < 6; i++){  //there are six types of coins
            coins.add(randomGenerator.nextInt(101));
        }
    }
    public boolean isChangeAvailable(double difference){
        //difference is always < 5.0
        double sum;
        for(int two = 0; two <= 2; two++){ //handles COIN.TWO
            for(int one = 0; one <= 4; one++){ //handles COIN.ONE
                for(int fifty = 0; fifty <= 9; fifty++){ //handles COIN.POINTFIFTY
                    for(int twenty = 0; twenty <= 24; twenty++) { //handles COIN.POINTTWENTY
                        for (int ten = 0; ten <= 49; ten++) { //handles COIN.POINTTEN
                            sum = two*Coin.TWO.getValue()+one*Coin.ONE.getValue()+fifty*Coin.POINTFIFTY.getValue()+
                                    twenty*Coin.POINTTWENTY.getValue()+ten*Coin.POINTTEN.getValue();
                            if(FloatingPointHandler.isNear(sum, difference))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public ArrayList<Coin> giveChange(double difference){
        ArrayList<Coin> change = new ArrayList<Coin>();
        while(difference > 0) {
            //difference will never be greater or equal to five
            if (FloatingPointHandler.isNear(1.0, difference%2)) {
                change.add(Coin.TWO);
                removeCoin(Coin.TWO);
                difference -= Coin.TWO.getValue();
            }
            else if (FloatingPointHandler.isNear(1.0, difference%1)) {
                change.add(Coin.ONE);
                removeCoin(Coin.ONE);
                difference -= Coin.ONE.getValue();
            }
            else if (FloatingPointHandler.isNear(1.0, difference%0.5)) {
                change.add(Coin.POINTFIFTY);
                removeCoin(Coin.POINTFIFTY);
                difference -= Coin.POINTFIFTY.getValue();
            }
            else if (FloatingPointHandler.isNear(1.0, difference%0.2)) {
                change.add(Coin.POINTTWENTY);
                removeCoin(Coin.POINTTWENTY);
                difference -= Coin.POINTTWENTY.getValue();
            }
            else if (FloatingPointHandler.isNear(1.0, difference%0.1)) {
                change.add(Coin.POINTTEN);
                removeCoin(Coin.POINTTEN);
                difference -= Coin.POINTTEN.getValue();
            }
        }
        return change;
    }
    public void addCoin(Coin coin){
        //difference will never be greater or equal to five
        if(coin.equals(Coin.FIVE))
            bank.add(coin);
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
    private void removeCoin(Coin coin){
        //difference will never be greater or equal to five
        if(coin.equals(Coin.TWO))
            coins.set(1, coins.get(1) - 1);
        else if(coin.equals(Coin.ONE))
            coins.set(2, coins.get(2) - 1);
        else if(coin.equals(Coin.POINTFIFTY))
            coins.set(3, coins.get(3) - 1);
        else if(coin.equals(Coin.POINTTWENTY))
            coins.set(4, coins.get(4) - 1);
        else if(coin.equals(Coin.POINTTEN))
            coins.set(5, coins.get(5) - 1);
    }
    private boolean checkPlaceForAnotherCoin(int index){
        return coins.get(index) < 100;
    }
}
