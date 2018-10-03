package com.michalak.adam.helpers;

import org.junit.Test;

public class CoinTest {

    @Test
    public void printTest() {
        for(Coin coin : Coin.values())
            System.out.println(coin);
    }
}