package com.michalak.adam.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class FloatingPointHandlerTest {

    @Test
    public void valuesAreNear() {
        double thisOne = 2.050;
        double thatOne = 2.059;
        assertTrue(FloatingPointHandler.isNear(thisOne, thatOne));
    }
    @Test
    public void valuesAreNotNear(){
        double thisOne = 2.050;
        double thatOne = 2.060;
        assertFalse(FloatingPointHandler.isNear(thisOne, thatOne));
    }
    @Test
    public void valueIsNearZero(){
        double theOne = 0.009;
        assertTrue(FloatingPointHandler.isNearZero(theOne));
    }
    @Test
    public void valueIsNotNearZero(){
        double theOne = 0.01;
        assertFalse(FloatingPointHandler.isNearZero(theOne));
    }
}