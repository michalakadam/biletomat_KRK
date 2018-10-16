package com.michalak.adam.app;

import com.michalak.adam.helpers.Coin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemporaryMoneyStorageTest {
    private TemporaryMoneyStorage temporaryMoneyStorage;

    @Before
    public void setUp(){
        //given
        temporaryMoneyStorage = new TemporaryMoneyStorage();
    }
    @Test
    public void getValueOfCoinsThrown(){
        //when
        temporaryMoneyStorage.addCoin(Coin.FIVE);
        //then
        assertEquals(5, temporaryMoneyStorage.getValueOfCoinsThrown(), 1E-2);
    }
    @Test
    public void getAmountOfCoinsThrown(){
        //when
        temporaryMoneyStorage.addCoin(Coin.FIVE);
        temporaryMoneyStorage.addCoin(Coin.TWO);
        temporaryMoneyStorage.addCoin(Coin.ONE);
        //then
        assertEquals(3, temporaryMoneyStorage.getAmountOfCoinsThrown());
    }
    @Test
    public void clearTemporaryMoneyStorage(){
        //when
        temporaryMoneyStorage.addCoin(Coin.FIVE);
        temporaryMoneyStorage.addCoin(Coin.TWO);
        temporaryMoneyStorage.addCoin(Coin.ONE);
        temporaryMoneyStorage.clearTemporaryStorage();
        //then
        assertEquals(0, temporaryMoneyStorage.getValueOfCoinsThrown(), 1E-2);
    }

}