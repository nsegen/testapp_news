package com.revotech.nsegen.constants;

/**
 * Created by Revotech on 12.07.16.
 */
public final class UserQueries {

    private UserQueries(){}

    public static final String SQL_GET_LIST_OF_USERS =
            "SELECT *" +
            " FROM NIKITASEGEN.USERS";

    public static final String SQL_ADD_USER =
            "INSERT " +
            "INTO NIKITASEGEN.USERS (ID, FIRST_NAME, LAST_NAME, PASSWORD, NICK_NAME) " +
            "VALUES (?, ?, ?, ?, ?)";

    public static final String SQL_GET_USER_ID_BY_NICKNAME =
            "SELECT ID " +
            "FROM NIKITASEGEN.USERS " +
            "WHERE NICK_NAME = ?";

    public static final String SQL_GET_USER_ID =
            "SELECT users_seq.nextval" +
            " FROM NIKITASEGEN.USERS";

    public static final String SQL_DELETE_USER =
            "DELETE FROM NIKITASEGEN.USERS " +
            "WHERE ID = ?";

    public static final String SQL_GET_USER_BY_ID =
            SQL_GET_LIST_OF_USERS +
            " WHERE NIKITASEGEN.USERS.ID = ?";

    public static final String SQL_UPDATE_USER =
            "UPDATE NIKITASEGEN.USERS " +
            "SET FIRST_NAME = ?, LAST_NAME = ?, PASSWORD = ?" +
            "WHERE ID = ?";

}
