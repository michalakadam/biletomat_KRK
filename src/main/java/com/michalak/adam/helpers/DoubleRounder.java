package com.michalak.adam.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Helper that rounds double values to specified amount of significant digits.
 * @author www.baeldung.com
 */
public class DoubleRounder {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
