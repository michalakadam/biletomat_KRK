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

    /**
     * This method provides an algorithm that tells if giving change to the customer is possible.
     * @param difference is difference between value of coins inserted and price of selected tickets.
     * @return true if giving change is possible.
     */
    public boolean isChangeAvailable(double difference){
        //difference is always < 5.0
        double sum;
        for(int two = 0; two <= (getCertainCoinAmount(1) > 2 ? 2 : getCertainCoinAmount(1)); two++){ //handles COIN.TWO
            for(int one = 0; one <= (getCertainCoinAmount(2) > 4 ? 4 : getCertainCoinAmount(2)); one++){ //handles COIN.ONE
                for(int fifty = 0; fifty <= (getCertainCoinAmount(3) > 9 ? 9 : getCertainCoinAmount(3)); fifty++){ //handles COIN.POINTFIFTY
                    for(int twenty = 0; twenty <= (getCertainCoinAmount(4) > 24 ? 24 : getCertainCoinAmount(4)); twenty++) { //handles COIN.POINTTWENTY
                        for (int ten = 0; ten <= (getCertainCoinAmount(5) > 49 ? 49 : getCertainCoinAmount(5)); ten++) { //handles COIN.POINTTEN
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

    /**
     * This method is responsible for giving change to the customer. It attempts to do it using as little coins
     * as possible - starting with the most valuable coins.
     * @param difference is difference between money paid by the customer and tickets' value - change.
     * @return ArrayList of coins that are then given to the customer as change.
     */
    public ArrayList<Coin> giveChange(double difference){
        ArrayList<Coin> change = new ArrayList<Coin>();

        while(!FloatingPointHandler.isNearZero(difference)) {
            //difference will never be greater or equal to five
            if (coinIsAvailable(1) && difference/2.0 > 1.0 || FloatingPointHandler.isNear(1.0, difference/2.0)) {
                change.add(Coin.TWO);
                removeCoin(Coin.TWO);
                difference -= Coin.TWO.getValue();
            }
            else if (coinIsAvailable(2) && difference/1.0 > 1.0 || FloatingPointHandler.isNear(1.0, difference/1.0)) {
                change.add(Coin.ONE);
                removeCoin(Coin.ONE);
                difference -= Coin.ONE.getValue();
            }
            else if (coinIsAvailable(3) && difference/0.5 > 1.0 || FloatingPointHandler.isNear(1.0, difference/0.5)) {
                change.add(Coin.POINTFIFTY);
                removeCoin(Coin.POINTFIFTY);
                difference -= Coin.POINTFIFTY.getValue();
            }
            else if (coinIsAvailable(4) && difference/0.2 > 1.0 || FloatingPointHandler.isNear(1.0, difference/0.2)) {
                change.add(Coin.POINTTWENTY);
                removeCoin(Coin.POINTTWENTY);
                difference -= Coin.POINTTWENTY.getValue();
            }
            else if (coinIsAvailable(5) && difference/0.1 > 1.0 || FloatingPointHandler.isNear(1.0, difference/0.1)) {
                change.add(Coin.POINTTEN);
                removeCoin(Coin.POINTTEN);
                difference -= Coin.POINTTEN.getValue();
            }
            //BUG EVIDENCE HERE. NO CONDITION IS FULFILLED.
            else {
                difference = 0;
                System.out.println("Somehow I cannot give change");
            }
        }
        return change;
    }

    /**
     * This method is called when the transaction is comfirmed. Then coins are moved from temporary storage.
     * to change storage. If there is no place in change storage for a given coin then it is moved to the bank.
     * @param coin is the specific coin that is added from temporary storage.
     */
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

    /**
     * This method is used when coins are taken from change source and given to the customer.
     * @param coin is the specific coin given to the customer as change.
     */
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

    /**
     * This method checks if there is place for a coin of the given type in the change repository.
     * @param index characterizes the type of coin that is checked.
     * @return true if there is a place to another coin of the given type to the change source.
     */
    protected boolean checkPlaceForAnotherCoin(int index){
        return coins.get(index) < 100;
    }
    protected ArrayList<Integer> getChangeAvailable(){
        return coins;
    }
    private int getCertainCoinAmount(int index){
        return coins.get(index);
    }
    protected void setCoinsAmount(int index, int amount){
        coins.set(index, amount);
    }
    private boolean coinIsAvailable(int index){
        return coins.get(index) != 0;
    }
}
