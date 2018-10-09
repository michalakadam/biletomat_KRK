package com.michalak.adam.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class FloatingPointHandlerTest {

    @Test
    public void isNear() {
        double thisOne = 2.500000;
        double thatOne = 2.500001;
        assertTrue(FloatingPointHandler.isNear(thisOne, thatOne));
    }
    @Test
    public void isNotNear(){
        double thisOne = 2.50000;
        double thatOne = 2.50001;
        assertFalse(FloatingPointHandler.isNear(thisOne, thatOne));
    }
}