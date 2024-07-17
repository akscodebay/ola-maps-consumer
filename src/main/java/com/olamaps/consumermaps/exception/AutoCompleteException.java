package com.olamaps.consumermaps.exception;

public class AutoCompleteException extends Exception {

    public AutoCompleteException(Throwable t) {super(t);}
    public AutoCompleteException(String message) {super(message);}
    public AutoCompleteException(String message, Throwable cause) {super(message, cause);}

}
