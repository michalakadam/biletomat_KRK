package com.michalak.adam.helpers;

public enum Coin {
    FIVE(5.0), TWO(2.0), ONE(1.0), POINTFIFTY(0.50), POINTTWENTY(0.20), POINTTEN(0.10);

    private double value;

    Coin(double value){
        this.value = value;
    }
    public String toString(){
        return value + value > 0.9 ? "zł" : "gr";
    }
}
