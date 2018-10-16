package com.michalak.adam.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class FloatingPointHandlerTest {

    @Test
    public void valueIsRoundedUp(){
        assertEquals(1.1, FloatingPointHandler.round(1.05, 1), 1E-3);
    }
    @Test
    public void valueIsRoundedDown(){
        assertEquals(1.0, FloatingPointHandler.round(1.04, 1), 1E-3);
    }
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