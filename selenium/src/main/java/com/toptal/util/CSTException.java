package com.toptal.util;

/**
 * @author askeledzija
 */
public class CSTException extends Exception {
    public CSTException(String s) {
        super(s);
    }

    public CSTException(String s, Exception e) {
        super(s, e);
    }
}
