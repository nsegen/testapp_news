package com.revotech.nsegen.constants;

/**
 * Created by Revotech on 05.07.16.
 */
public final class NewsQueries {

    public static final String SQL_GET_LIST_OF_NEWS =
            "SELECT NIKITASEGEN.NEWS.ID, NICK_NAME, TITLE, CONTENT, RELEASE_DATE, IMG_URL FROM NIKITASEGEN.NEWS " +
            "LEFT JOIN NIKITASEGEN.USERS " +
            "ON NIKITASEGEN.NEWS.AUTHOR_ID = NIKITASEGEN.USERS.id";

    public static final String SQL_ADD_NEWS =
            "INSERT " +
            "INTO NIKITASEGEN.NEWS (ID, TITLE, RELEASE_DATE, CONTENT, AUTHOR_id, IMG_URL) " +
            "VALUES (?, ?, ?, ?, (" + UserQueries.SQL_GET_USER_ID_BY_NICKNAME + ") , ?)";

    public static final String SQL_DELETE_NEWS =
            "DELETE FROM NIKITASEGEN.NEWS " +
            "WHERE ID = ?";

    public static final String SQL_UPDATE_NEWS =
            "UPDATE NIKITASEGEN.NEWS " +
            "SET TITLE = ?, CONTENT = ?, IMG_URL = ?" +
            "WHERE ID = ?";

    public static final String SQL_GET_NEWS_ID =
            "SELECT news_seq.nextval" +
            " FROM NIKITASEGEN.NEWS";

    public static final String SQL_GET_NEWS_BY_ID =
            SQL_GET_LIST_OF_NEWS +
            " WHERE NIKITASEGEN.NEWS.ID = ?";

    private NewsQueries(){}
}
