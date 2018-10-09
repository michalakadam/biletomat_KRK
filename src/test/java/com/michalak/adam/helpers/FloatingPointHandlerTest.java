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
    public void valueIsNear(){
        double theOne = 0.009;
        assertTrue(FloatingPointHandler.isNear(theOne));
    }
    @Test
    public void valueIsNotNear(){
        double theOne = 0.01;
        assertFalse(FloatingPointHandler.isNear(theOne));
    }
}