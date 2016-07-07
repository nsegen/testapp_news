package com.revotech.nsegen.exceptions;

/**
 * Created by Revotech on 06.07.16.
 */
public class NewsServiceException extends Exception {

    public NewsServiceException(String message, Exception e) {
        super(message, e);
    }

    public NewsServiceException(String message) {
        super(message);
    }

}
