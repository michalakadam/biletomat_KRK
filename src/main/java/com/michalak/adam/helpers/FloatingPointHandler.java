package com.michalak.adam.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Helper that provides methods for working with double variables:
 * 1. round(double value, int places) rounds double values to specified amount of significant digits.
 * 2. isNear(double expected, double compared) solves problem of comparing two floating point numbers.
 * 3. isNear(double value) tells if value is (almost) equal to zero.
 * @author www.baeldung.com
 */
public class FloatingPointHandler {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public static boolean isNear(double expected, double compared){
        return (Math.abs(expected - compared) < 1E-2);
    }
    public static boolean isNearZero(double value){
        return (Math.abs(value) < 1E-2);
    }
}
