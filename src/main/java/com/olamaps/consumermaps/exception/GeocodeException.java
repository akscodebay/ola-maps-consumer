package com.olamaps.consumermaps.exception;

public class GeocodeException extends Exception{

    public GeocodeException(String message) {super(message);}
    public GeocodeException(String message, Throwable cause) {super(message, cause);}
    public GeocodeException(Throwable cause) {super(cause);}
}
