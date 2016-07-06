package com.revotech.nsegen.exceptions;

/**
 * Created by Revotech on 06.07.16.
 */
public class DAOException extends Exception {

    private Exception e;

    public DAOException(Exception e){
        this.e = e;
    }

    public DAOException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DAOException: " + e;
    }

}
