package com.michalak.adam.app;

import com.michalak.adam.helpers.Coin;

import java.util.ArrayList;

/**
 * Bank is an object that stores surplus coins from change storage. When change storage capacity for a given
 * coin is achieved additional coins provided by the customer are moved from temporary money storage to
 * the bank. Coins from bank are taken only by authorised employee. They cannot automatically go to
 * change storage when there is not enough coins there. You can imagine Bank as unordered sack of coins.
 *
 */
public class Bank {
    ArrayList<Coin> coins;

    public Bank() {
        coins = new ArrayList<Coin>();
    }

    public void add(Coin coin) {
        coins.add(coin);
    }
}