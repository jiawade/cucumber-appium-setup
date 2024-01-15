package com.demo.exception;

public class UnsupportedPlatformException extends RuntimeException{
    public UnsupportedPlatformException() {
        super();
    }

    public UnsupportedPlatformException(String msg) {
        super(msg);
    }
}
