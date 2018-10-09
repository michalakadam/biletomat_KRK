package com.michalak.adam.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class FloatingPointHandlerTest {

    @Test
    public void isNear() {
        double thisOne = 2.050;
        double thatOne = 2.059;
        assertTrue(FloatingPointHandler.isNear(thisOne, thatOne));
    }
    @Test
    public void isNotNear(){
        double thisOne = 2.050;
        double thatOne = 2.060;
        assertFalse(FloatingPointHandler.isNear(thisOne, thatOne));
    }
}