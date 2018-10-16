package com.michalak.adam.helpers;

/**
 * This enum contains all possible coins valid in the ticket machine. Light coins like 1gr, 2gr and 5gr are too
 * light to be detected by coin detector hardware.
 */
public enum Coin {
    FIVE(5.0), TWO(2.0), ONE(1.0), POINTFIFTY(0.50), POINTTWENTY(0.20), POINTTEN(0.10);

    private double value;

    Coin(double value){
        this.value = value;
    }
    public String toString(){
        return value > 0.9 ? value + "zł" : value*100 + "gr";
    }
    public double getValue() {
        return this.value;
    }
    public boolean equals(Coin coin){
        return this.value == coin.getValue();
    }
}
