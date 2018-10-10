package com.michalak.adam.helpers;

import org.junit.Test;

import static org.junit.Assert.*;
// I don't know how to mock the scanner yet but I can check inputIsInRange method
public class UserInputProviderTest {

    @Test
    public void inputIsInMinRange() {
        assertTrue(UserInputProvider.inputIsInRange(1, 1, 3));
    }
    @Test
    public void inputIsInMaxRange() {
        assertTrue(UserInputProvider.inputIsInRange(3, 1, 3));
    }
    @Test
    public void inputIsNotInRange(){
        assertFalse(UserInputProvider.inputIsInRange(0, 1, 3));
    }
}