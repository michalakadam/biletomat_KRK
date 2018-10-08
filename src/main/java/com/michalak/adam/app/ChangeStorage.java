package com.michalak.adam.app;

import java.util.ArrayList;

/** Change storage is an object that stores coins that are available for giving change to the customer.
 *  Each slot has place for hundred coins. Amount of coins when change storage object is initialized is
 *  picked randomly.
 */

public class ChangeStorage extends Bank {
    ArrayList<Integer> coinsInChangeStorage;

    public ChangeStorage(){
        super();

    }
}
