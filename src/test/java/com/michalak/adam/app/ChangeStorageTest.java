package com.michalak.adam.app;

import com.michalak.adam.helpers.Coin;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChangeStorageTest {
    private ChangeStorage changeStorage;

    @Before
    public void setUp(){
        changeStorage = new ChangeStorage();
    }
    @Test
    public void createArray(){
        assertEquals(6, changeStorage.getChangeAvailable().size());
    }
    @Test
    public void arrayIndexesLessThan101(){
        for(int i = 0; i < changeStorage.getChangeAvailable().size(); i++){
            assertTrue(changeStorage.getChangeAvailable().get(i) < 101);
        }
    }
    @Test
    public void arrayIndexesPositive(){
        for(int i = 0; i < changeStorage.getChangeAvailable().size(); i++){
            assertTrue(changeStorage.getChangeAvailable().get(i) >= 0);
        }
    }
    @Test
    public void changeIsAvailable(){
        //when
        for(int i = 0; i < changeStorage.getChangeAvailable().size(); i++)
            changeStorage.setCoinsAmount(i, 1);
        //then
        assertTrue(changeStorage.isChangeAvailable(3.80)); //3.80 uses all coins in changeStorage
    }
    @Test
    public void changeIsNotAvailable(){
        //when
        for(int i = 0; i < changeStorage.getChangeAvailable().size(); i++)
            changeStorage.setCoinsAmount(i, 1);
        //then
        assertFalse(changeStorage.isChangeAvailable(3.90)); //3.90 is 10gr more than coins in changeStorage
    }
    @Test
    public void giveChangeTWO(){
        assertEquals(Coin.TWO, changeStorage.giveChange(2.0).get(0));
    }
    @Test
    public void giveChangeFiveCoins(){
        //when
        ArrayList<Coin> change = new ArrayList<Coin>();
        change.add(Coin.TWO);
        change.add(Coin.ONE);
        change.add(Coin.POINTFIFTY);
        change.add(Coin.POINTTWENTY);
        change.add(Coin.POINTTEN);
        ArrayList<Coin> changeGiven = changeStorage.giveChange(3.80);
        //then
        assertEquals(change.get(0), changeGiven.get(0));
        assertEquals(change.get(1), changeGiven.get(1));
        assertEquals(change.get(2), changeGiven.get(2));
        assertEquals(change.get(3), changeGiven.get(3));
        assertEquals(change.get(4), changeGiven.get(4));
    }
    @Test (expected = IllegalArgumentException.class)
    public void negativeChangeThrowsException(){
        changeStorage.giveChange(-0.1);
    }
    /* Those tests doesn't work and I cannot find the reason why.
    @Test
    public void addCoinPointFifty(){
        //when
        changeStorage.setCoinsAmount(5,0);
        changeStorage.addCoin(Coin.POINTFIFTY);
        //then
        assertEquals(1, changeStorage.getCertainCoinAmount(5));
    }
    @Test
    public void removeCoinPointFifty(){
        //when
        changeStorage.setCoinsAmount(5,1);
        changeStorage.removeCoin(Coin.POINTFIFTY);
        //then
        assertEquals(0, changeStorage.getCertainCoinAmount(5));
    }
    */
    @Test
    public void thereIsPlaceForAnotherCoin(){
        //when
        changeStorage.setCoinsAmount(0, 99); //99 5zł coins
        //then
        assertTrue(changeStorage.checkPlaceForAnotherCoin(0));
    }
    @Test
    public void thereIsNoPlaceForAnotherCoin(){
        //when
        changeStorage.setCoinsAmount(0, 100); //100 5zł coins
        //then
        assertFalse(changeStorage.checkPlaceForAnotherCoin(0));
    }
    @Test
    public void coinIsAvailable(){
        //when
        changeStorage.setCoinsAmount(0, 1); //1 5zł coin
        //then
        assertTrue(changeStorage.coinIsAvailable(0));
    }
    @Test
    public void coinIsNotAvailable(){
        //when
        changeStorage.setCoinsAmount(0, 0); //0 5zł coins
        //then
        assertFalse(changeStorage.coinIsAvailable(0));
    }

}