package com.michalak.adam.helpers;

/**
 * Custom exception class. This exception is going to be used when there is no more paper in the machine.
 */
public class NoPaperException extends Exception {
    private String exceptionMessage;

    public NoPaperException(String exceptionMessage){
        //call constructor of parent class Exception
        super(exceptionMessage);
    }
}
