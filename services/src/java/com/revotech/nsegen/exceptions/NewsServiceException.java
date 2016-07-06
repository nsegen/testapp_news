package com.revotech.nsegen.exceptions;

/**
 * Created by Revotech on 06.07.16.
 */
public class NewsServiceException extends Exception {

    private Exception e;

    public NewsServiceException(String message, Exception e) {
        super(message);
        this.e = e;
    }

    @Override
    public String toString() {
        return "NewsServiceException: " + e;
    }

}
