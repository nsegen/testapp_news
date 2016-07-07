package com.revotech.nsegen.exceptions;

/**
 * Created by Revotech on 06.07.16.
 */
public class DAOException extends Exception {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }

}
